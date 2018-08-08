package org.harsh.arya.easyimage;

import org.harsh.arya.easyimage.utils.job.Job;
import org.harsh.arya.easyimage.utils.job.JobHandler;

public class EasyImage {

    public static void main (String[] args){
        JobHandler jobParser = new JobHandler();
        Job[] jobs = jobParser.retrieveJob("/var/data/input/jobs.json");
        System.out.println(jobs);
        jobParser.scheduleJobs(jobs);
    }
}
