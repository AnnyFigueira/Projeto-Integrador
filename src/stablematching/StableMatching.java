/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stablematching;
import com.sun.jmx.remote.internal.ArrayQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.AbstractMap.SimpleEntry;
import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author Anny
 */
public class StableMatching {
    static ArrayList<Applicant> applicants = new ArrayList<Applicant>();
    static ArrayList<University> universities = new ArrayList<University>();
    static ArrayList<SimpleEntry<Applicant, University>> matchings;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        String filename = "input.txt";
        int numberOfApplicants;
        int numberOfUniversities;
        try
        {
            FileReader file = new FileReader(filename);
            BufferedReader reader = new BufferedReader(file);
            numberOfUniversities = Integer.parseInt(reader.readLine());
            //System.out.println(numberOfUniversities);
            numberOfApplicants = Integer.parseInt(reader.readLine());
            //System.out.println(numberOfApplicants);
            int i = 0;
            while(i < numberOfUniversities)
            {
                //System.out.println(i < numberOfUniversities);
                String uniName = reader.readLine();
                int quota = Integer.parseInt(reader.readLine());
                ArrayList<Integer> priorityList = new ArrayList<Integer>();
                String[] list = reader.readLine().split("\\s");
                for(int j = 0; j < list.length; j++) { priorityList.add(Integer.parseInt(list[j])); }
                University uni = new University(uniName, quota, priorityList);
                //System.out.println(uni.getName());
                //System.out.println(uni.getPreferences().size());
                //for(String s: list) System.out.println(s);
                universities.add(uni);
                //System.out.println(i);
                i++;
            }
            //System.out.println(i);
            int k = 0;
            for(k = 0; k < numberOfApplicants; k++)
            {
                String appName = reader.readLine();
                ArrayList<Integer> queue = new ArrayList<Integer>();
                String[] list = reader.readLine().split("\\s");
                for(int j = 0; j < list.length; j++) { queue.add(Integer.parseInt(list[j])); }
                Applicant app = new Applicant(appName, queue);
                //System.out.println(app.getName());
                //System.out.println(app.getQueue().size());
                applicants.add(app);
            }
        }
        catch(Exception e){};
        
        stableMatching();
        for(int j = 0; j < applicants.size(); j++)
        {
            System.out.println(applicants.get(j).getName() + " " + applicants.get(j).getUniversity());
        }
    }
    
    public static void stableMatching()
    {
        boolean existFree = false;
        for(int j = 0; j < applicants.size(); j++)
        {
            Applicant a = applicants.get(j);
            if(a.isFree() && a.queueStatus() != -1)
            {
                int i = (Integer)a.getQueue().remove(0);
                University u = universities.get(i);
                if(u.getQuota() > 0) 
                {
                    u.accept(j);
                    a.setUniversity(i);
                    u.decQuota();
                }
                else
                {
                    int worstPos = 0;
                    for(int p = 0; p < u.getAccepted().size(); p++)
                    {
                        int acc = u.getAccepted().get(p);
                        int current = u.getApplicantPosition(acc);
                        if(u.getApplicantPosition(acc) > worstPos)
                        {
                            worstPos = current;
                        }
                    }
                    if(u.getApplicantPosition(j) < worstPos)
                    {
                        int worstIndex = u.getPreferences().get(worstPos);
                        Applicant worst = applicants.get(worstIndex);
                        worst.setUniversity(-1);
                        existFree = true;
                        u.replaceApplicant(worstIndex, j);
                        a.setUniversity(i);
                    }
                    else { existFree = true; }
                }
            }
        }
        if (existFree) { stableMatching(); }
    }
}
