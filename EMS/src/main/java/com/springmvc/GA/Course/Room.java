package com.springmvc.GA.Course;

/**
 *Classroom
 *
 */
public class Room {
    private final String roomId;
    private final String roomNumber;
    private final int capacity;

    public Room(String roomId,String roomNumber,int capacity){
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }
}
