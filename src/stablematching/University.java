/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stablematching;

import java.util.ArrayList;

/**
 *
 * @author Anny
 */
class University {
    private String name;
    private int quota;
    private ArrayList<Integer> preferences;
    private ArrayList<Integer> accepted;
    public University(String n, int q, ArrayList<Integer> p)
    {
        name = n;
        quota = q;
        preferences = p;
        accepted = new ArrayList<Integer>();
    }
    public String getName() { return name; }
    public int getQuota() { return quota; }
    public int decQuota()
    {
        quota -= 1;
        return quota;
    }
    public int incQuota()
    {
        quota += 1;
        return quota;
    }
    public ArrayList<Integer> getPreferences() { return preferences; }
    public ArrayList<Integer> getAccepted() { return accepted; }
    public void accept (int applicant) { accepted.add(applicant); }
    public int getApplicantPosition(Integer applicant) 
    { 
        for(int i = 0; i < preferences.size(); i++)
        {
            if(preferences.get(i) == applicant) return i;
        }
        return -1;
    }
    public void replaceApplicant(int pos, int elem) { accepted.set(pos, elem);}
}
