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
    String name;
    int quota;
    ArrayList<Applicant> preferences;
    ArrayList<Applicant> accepted;
    public University(String n, int q, ArrayList<Applicant> p)
    {
        name = n;
        quota = q;
        preferences = p;
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
    public ArrayList<Applicant> getPreferences() { return preferences; }
    public ArrayList<Applicant> getAccepted() { return accepted; }
    public void accept (Applicant applicant) {};
}
