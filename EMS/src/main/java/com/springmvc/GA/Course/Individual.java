package com.springmvc.GA.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * 表示单个候选解及其染色体
 */
public class Individual {

    private List<String> chromosome;         //染色体

    private double fitness = -1;    //适应度

    public Individual(List<String> chromosome){
        //Create individual chromosome
        this.chromosome = chromosome;
    }

    public Individual(int chromosomeLength){
        //Create random individual
        List<String> individual;
        individual = new ArrayList<String>();

        for (int gene = 0;gene < chromosomeLength;gene++){
            individual.add(gene,String.valueOf(gene));
        }

        this.chromosome = individual;
    }

    public Individual(Timetable timetable){
        //int numClasses = timetable.getNumClasses();

        //1 gene for room,1 for time,1 for professor
        //int chromosomeLength = numClasses * 3;
        //Create random individual
        List<String> newChromosome = new ArrayList<String>();
        int chromosomeIndex = 0;

        //Loop through groups
        for (Group group:timetable.getGroupsAsArray()){
            //Loop through modules
            for (String moduleId:group.getModuleIds()){
                //Make sure the module that group selected have professor to teach
                if (timetable.getModule(moduleId) != null){
                    //Add random time
                    String timeslotId = timetable.getRandomTimeslot().getTimeslotId();
                    newChromosome.add(chromosomeIndex,timeslotId);
                    chromosomeIndex++;

                    //Add random room
                    String roomId = timetable.getRandomRoom().getRoomId();
                    newChromosome.add(chromosomeIndex,roomId);
                    chromosomeIndex++;

                    //Add random professor
                    Module module = timetable.getModule(moduleId);
                    newChromosome.add(chromosomeIndex,module.getRandomProfessorId());
                    chromosomeIndex++;
                }
            }
        }
        this.chromosome = newChromosome;
    }

    public List<String> getChromosome() {
        return chromosome;
    }

    public void setChromosome(List<String> chromosome) {
        this.chromosome = chromosome;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public int getChromosomeLength(){
        return this.chromosome.size();
    }

    public void setGene(int offset,String gene){
        this.chromosome.add(offset,gene);
    }

    public String getGene(int offset){
        return this.chromosome.get(offset);
    }

    public String toString(){
        String output = "";
        for (int gene = 0;gene < this.chromosome.size();gene++){
            output += this.chromosome.get(gene);
        }
        return output;
    }

    public boolean containsGene(String gene){
        for (int i = 0;i < this.chromosome.size();i++){
            if (this.chromosome.get(i).equals(gene)){
                return true;
            }
        }
        return false;
    }
}
