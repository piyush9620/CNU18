package org.harsh.arya.easyimage.job;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class JobHandler {

    ObjectMapper mapper;
    JobScheduler jobScheduler;

    public JobHandler(){
        mapper  = new ObjectMapper();
        mapper.enableDefaultTyping();
        jobScheduler = new JobScheduler();
    }

    public Job[] retrieveJob(String filepath) throws JsonParseException,JsonMappingException,IOException{
        return mapper.readValue(new File(filepath),Job[].class);
    }

    public void scheduleJobs(Job[] jobs){
        jobScheduler.performJobs(jobs);
    }

}


