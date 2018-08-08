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

    public Job[] retrieveJob(String filepath){
        Job[] imageJob=null;
        try{
            imageJob = mapper.readValue(new File(filepath),Job[].class);
        }
        catch (JsonParseException e){
            System.out.println(e);
        }
        catch(JsonMappingException e){
            System.out.println(e);
        }
        catch(IOException e){
            System.out.println(e);
        }
         return imageJob;
    }

    public void scheduleJobs(Job[] jobs){

            jobScheduler.performJobs(jobs);

    }

}


