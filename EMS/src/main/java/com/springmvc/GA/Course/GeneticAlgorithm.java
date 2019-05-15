package com.springmvc.GA.Course;



import java.util.Arrays;


/**
 *
 */
public class GeneticAlgorithm {

    private int populationSize;         //种群规模

    private double mutationRate;        //变异率

    private double crossoverRate;       //交叉率

    private int elitismCount;           //精英成员数

    protected int tournamentSize;       //锦标赛

    public GeneticAlgorithm(int populationSize, double mutationRate, double crossoverRate, int elitismCount, int tournamentSize){

        this.populationSize = populationSize;

        this.mutationRate = mutationRate;

        this.crossoverRate = crossoverRate;

        this.elitismCount = elitismCount;

        this.tournamentSize = tournamentSize;
    }

    //Initialize
    public Population initPopulation(Timetable timetable){
        //Initialize population
        return new Population(this.populationSize,timetable);
    }

    //适应度函数
    public double calcFitness(Individual individual,Timetable timetable){
        //Create new timetable object to use -- cloned from an existing timetable
        Timetable threadTimetable = new Timetable(timetable);
        threadTimetable.createClasses(individual);

        //Calculate fitness
        int clashes = threadTimetable.calcClashes();
        double fitness = 1 / (double)(clashes + 1);
        individual.setFitness(fitness);

        return fitness;
    }


    //辅助方法，遍历每个个体并评估它们
    public void evalPopulation(Population population,Timetable timetable){
        double populationFitness = 0;

        //Loop over population evaluating individuals and summing population fitness
        for (Individual individual:population.getIndividuals()){
            populationFitness += this.calcFitness(individual,timetable);
        }

        population.setPopulationFitness(populationFitness);
    }

    //终止检查函数
    public boolean isTerminationConditionMet(int generationsCount,int maxGenerations){
            return (generationsCount > maxGenerations);
    }

    public boolean isTerminationConditionMet(Population population){
        return population.getFittest(0).getFitness() == 1.0;
    }

    //Tournament selection
    public Individual selectParent(Population population){
        //Creat tournament
        Population tournament = new Population(this.tournamentSize);
        //Add random individuals to the tournament
        population.shuffle();
        for (int i = 0;i < this.tournamentSize;i++){
            Individual tournamentIndividual = population.getIndividual(i);
            tournament.setIndividual(i,tournamentIndividual);
        }

        //Return the best
        return tournament.getFittest(0);
    }

    //Crossover
    public Population crossoverPopulation(Population population){
        //Create new population
        Population newPopulation = new Population(population.size());

        //Loop over current population by fitness
        for (int populationIndex = 0;populationIndex < population.size();populationIndex++){
            Individual parent1 = population.getFittest(populationIndex);

            //Apply crossover to this individual?
            if (this.crossoverRate > Math.random() && populationIndex >= this.elitismCount){
                //Initialize offspring
                Individual offspring = new Individual(parent1.getChromosomeLength());

                //Find second parent
                Individual parent2 = selectParent(population);

                //Loop over gnome
                for (int geneIndex = 0;geneIndex < parent1.getChromosomeLength();geneIndex++){
                    //Use half of parent1's genes and half of parent2's genes
                    if (0.5 > Math.random()){
                        offspring.setGene(geneIndex,parent1.getGene(geneIndex));
                    }
                    else {
                        offspring.setGene(geneIndex,parent2.getGene(geneIndex));
                    }
                }

                //Add offspring to new population
                newPopulation.setIndividual(populationIndex,offspring);
            }
            else {
                //Add individual to new population without applying crossover
                newPopulation.setIndividual(populationIndex,parent1);
            }
        }
        return newPopulation;
    }

    //Mutation
    public Population mutatePopulation(Population population,Timetable timetable){
        //Initialize new population
        Population newPopulation = new Population(this.populationSize);

        //Loop over current population by fitness
        for (int populationIndex = 0;populationIndex < population.size();populationIndex++){
            Individual individual = population.getFittest(populationIndex);

            //Create random individual to swap genes with
            Individual randomIndividual = new Individual(timetable);

            //Loop over individual's genes
            for (int geneIndex = 0;geneIndex < individual.getChromosomeLength();geneIndex++){
                //Skip mutation if this is an elite individual
                if (populationIndex > this.elitismCount){
                    //Does this gene need mutation?
                    if (this.mutationRate > Math.random()){
                        //Swap for new gene
                        individual.setGene(geneIndex,randomIndividual.getGene(geneIndex));
                    }
                }
            }
            //Add individual to population
            newPopulation.setIndividual(populationIndex,individual);
        }
        //Return mutated population
        return newPopulation;
    }


}
