package org.harsh.arya.easyimage;

import org.harsh.arya.easyimage.utils.jsonparser.Job;
import org.harsh.arya.easyimage.utils.jsonparser.JobsParser;

import java.util.List;

public class EasyImage {

    public static void main (String[] args){
        JobsParser jobParser = new JobsParser();
        Job[] jobs = jobParser.retrieveJob("jobs.json");

        for(Job job :jobs){
            System.out.println(job);
            JobScheduler.performJob(job);
        }
    }
}
