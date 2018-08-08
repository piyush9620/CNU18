package org.harsh.arya.easyimage.utils.jsonparser;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class JobsParser {

    ObjectMapper mapper;

    public JobsParser(){
        mapper  = new ObjectMapper();
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

}


