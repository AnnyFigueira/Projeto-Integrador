/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stablematching;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.io.PrintWriter;
import java.io.FileWriter;

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
                Queue<Integer> queue = new LinkedList<Integer>();
                String[] list = reader.readLine().split("\\s");
                for(int j = 0; j < list.length; j++) { queue.add(Integer.parseInt(list[j])); }
                Applicant app = new Applicant(appName, queue);
                //System.out.println(app.getName());
                //System.out.println(app.getQueue().size());
                applicants.add(app);
            }
        }
        catch(Exception e){};
        long start = System.nanoTime();
        stableMatching();
        long end = System.nanoTime();
        double elapsedTime = (end - start)/1000000000.0;
        try
        {
            PrintWriter writer = new PrintWriter(new FileWriter("output.txt"));
            for(int j = 0; j < applicants.size(); j++) { writer.println(applicants.get(j).getName() + " " + applicants.get(j).getUniversity()); }
            writer.close();
        }        
        catch(Exception e){};
        
        System.out.println(elapsedTime);
    }
    
    public static void stableMatching()
    {
        boolean existFree = false;
        for(int j = 0; j < applicants.size(); j++)
        {
            Applicant a = applicants.get(j);
            if(a.isFree() && a.queueIsNotEmpty())
            {
                int i = (Integer)a.getQueue().poll();
                University u = universities.get(i);
                if(u.getQuota() > 0) 
                {
                    u.accept(j);
                    a.setUniversity(i);
                    u.decQuota();
                }
                else
                {
                    int worstPosInPrefList = 0;
                    int worstPosInAccepted = 0;
                    for(int p = 0; p < u.getAccepted().size(); p++)
                    {
                        int currentAcc = u.getAccepted().get(p);
                        int currentPosInPrefList = u.getApplicantPosition(currentAcc);
                        if(currentPosInPrefList > worstPosInPrefList)
                        {
                            worstPosInPrefList = currentPosInPrefList;
                            worstPosInAccepted = p;
                        }
                    }
                    if(u.getApplicantPosition(j) < worstPosInPrefList)
                    {
                        int worstApplicantIndex = u.getPreferences().get(worstPosInPrefList);
                        Applicant worstApplicant = applicants.get(worstApplicantIndex);
                        worstApplicant.setUniversity(-1);
                        existFree = true;
                        u.replaceApplicant(worstPosInAccepted, j);
                        a.setUniversity(i);
                    }
                    else { existFree = true; }
                }
            }
        }
        if (existFree) { stableMatching(); }
    }
}
