package org.harsh.arya.easyimage;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.extern.slf4j.Slf4j;
import org.harsh.arya.easyimage.job.Job;
import org.harsh.arya.easyimage.job.JobHandler;
import org.harsh.arya.easyimage.utils.Constants;

import java.io.File;
import java.io.IOException;

@Slf4j
public class EasyImage {

    public static void main (String[] args){
        JobHandler jobParser = new JobHandler();
        Job jobs[];
        try{
             jobs = jobParser.retrieveJob(Constants.jobPath);
            jobParser.scheduleJobs(jobs);
        }
        catch (JsonParseException e){
            log.error(e.toString());
        }
        catch(JsonMappingException e){
            log.error(e.toString());
        }
        catch(IOException e){
            log.error(e.toString());
        }


    }
}
