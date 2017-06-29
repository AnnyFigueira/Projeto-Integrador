/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stablematching;

import java.util.ArrayList;
import java.util.Queue;

/**
 *
 * @author Anny
 */
class Applicant {
    private final String name;
    private int university;
    private final Queue<Integer> queue;
    public Applicant(String n, Queue<Integer> q)
    {
        name = n;
        queue = q;
        university = -1;
    }
    public String getName(){ return name; }
    public int getUniversity() { return university; }
    public Queue<Integer> getQueue() { return queue;}
    public void setUniversity (int university) { this.university = university; }
    public boolean isFree() { return (university == -1); }
    public boolean queueStatus() { return !queue.isEmpty(); }
}
