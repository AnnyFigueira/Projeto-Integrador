/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stablematching;

import java.util.Queue;



/**
 *
 * @author Anny
 */
class Applicant {
    String name;
    University university;
    Queue<University> queue;
    public Applicant(String n, Queue q)
    {
        name = n;
        queue = q;
    }
    public String getName(){ return name; }
    public University getUniversity() { return university; }
    public Queue getQueue() { return queue;}
    public void setUniversity (University university) { this.university = university; }
}
