package com.system;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

enum Direction { UP, DOWN, NO}
enum Status { MOVING, IDLE}

public class TestElevator {
    public static void main(String[] args) {
        Elevator elevator = new Elevator();
        Runnable task = elevator::startElevator;
        Thread tr = new Thread(task);
        tr.start();

        int[][] input = new int[][]{{4, 1, 3}, {2, 1, 1}, {2, 1, 2}, {7, -1, 8}};

        Arrays.stream(input).forEach(item -> {
            Runnable addReqTask = () -> elevator.addRequest(new Request(item[0], item[1]), item[2]);
            Thread reqThread = new Thread(addReqTask);
            reqThread.start();
        });
    }
}

class Elevator {
    int curFloor = 0;
    Status status;
    Direction direction;

    Comparator<Request> comp = Comparator.comparingInt(req -> req.floor);
    TreeSet<Request> upReq = new TreeSet<>(comp);
    TreeSet<Request> downReq = new TreeSet<>(comp.reversed());

    public void sleep(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void addRequest(Request req, int seconds) {
        sleep(seconds);
        if (req.dir == Direction.UP || (req.dir == Direction.NO && req.floor > curFloor)) {
            if(upReq.add(req)){
                System.out.println("Current floor: " + curFloor + " => UP Request added for floor: " + req.floor);
            }
        } else if (req.dir == Direction.DOWN || (req.dir == Direction.NO && req.floor < curFloor)) {
            if(downReq.add(req)){
                System.out.println("Current floor: " + curFloor + " => DOWN Request added for floor: " + req.floor);
            }
        }

    }

    public void startElevator() {
        direction = Direction.UP;
        status = Status.MOVING;
        while (true) {
            sleep(1);
            if (upReq.isEmpty() && downReq.isEmpty()) {
                continue;
            }
            Request temp = new Request(curFloor);
            if (direction == Direction.UP) {
                System.out.println("At floor: " + curFloor);
                Request nextRequest = upReq.ceiling(temp);
                if (nextRequest != null) {
                    if (nextRequest.floor == curFloor) {
                        upReq.remove(nextRequest);
                        System.out.println("Opening door on floor: " + curFloor);
                    }
                }
                if (upReq.ceiling(temp) == null) {
                    if(!downReq.isEmpty() && downReq.first().floor > curFloor){
                        curFloor++;
                        System.out.println("No more UP requests, Going UP to pick the DOWN request.");
                    }else {
                        direction = Direction.DOWN;
                        System.out.println("No more UP requests, Going DOWN.");
                    }
                } else {
                    curFloor++;
                }
            } else if (direction == Direction.DOWN) {
                System.out.println("At floor: " + curFloor);
                Request nextRequest = downReq.ceiling(temp);
                if (nextRequest != null) {
                    if (nextRequest.floor == curFloor) {
                        downReq.remove(nextRequest);
                        System.out.println("Opening door on floor: " + curFloor);
                    }
                }

                if (downReq.ceiling(temp) == null) {
                    if(!upReq.isEmpty() && upReq.first().floor < curFloor){
                        curFloor--;
                        System.out.println("No more DOWN requests, Going DOWN to pick the UP request.");
                    }else {
                        direction = Direction.UP;
                        System.out.println("No more DOWN requests, Going UP.");
                    }
                } else {
                    curFloor--;
                }
            }
            if (upReq.isEmpty() && downReq.isEmpty()) {
                status = Status.IDLE;
                System.out.println("No more requests, Elevator is IDLE.");
            }
        }
    }
}

class Request {
    int floor;
    Direction dir;
    public Request(int floor) {
        this.floor = floor;
    }
    public Request(int floor, int dirNum) {
        this.floor = floor;
        this.dir = dirNum > 0 ? Direction.UP : (dirNum < 0 ? Direction.DOWN : Direction.NO);
    }
}