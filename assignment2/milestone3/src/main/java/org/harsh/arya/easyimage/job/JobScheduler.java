package org.harsh.arya.easyimage.job;

import org.apache.commons.io.FilenameUtils;
import org.harsh.arya.easyimage.operation.Operation;
import org.harsh.arya.easyimage.utils.Constants;
import org.harsh.arya.easyimage.utils.ImageUtils;

import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class JobTask implements Runnable {
    private Job job;
    private ImageUtils imgUtils;

    public JobTask(Job job,ImageUtils imgUtils) {
        this.job = job;
        this.imgUtils = imgUtils;
    }

    private boolean isPresent(HashSet<String> hSet,String key){
        if(hSet.contains(key)){
            return true;
        }else{
            hSet.add(key);
            return false;
        }

    }

    public void run() {
        String filePath = job.getImagePath();
        String filename = FilenameUtils.getName(filePath);
        Operation[] operations = job.getOperations();
        String outPath = Constants.outPath;
        HashSet<String> operationSet = new HashSet<>();
        for(Operation operation : operations){
            System.out.println(operation.toString());
            if(isPresent(operationSet,operation.toString())){
                continue;
            }
            operation.setFilePath(filePath);
            operation.apply(imgUtils,outPath);
            filePath = outPath+"/"+filename;
        }

    }
}

public class JobScheduler {

    private ImageUtils imgUtils;
    public JobScheduler(){
        imgUtils = new ImageUtils();
    }

    public  void performJobs(Job[] jobs){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (Job job : jobs) {
            Runnable worker = new JobTask(job,imgUtils);
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) ;
    }

}
