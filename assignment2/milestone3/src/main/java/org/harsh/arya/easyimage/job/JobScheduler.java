package org.harsh.arya.easyimage.job;

import org.harsh.arya.easyimage.operation.Operation;
import org.harsh.arya.easyimage.utils.ImageUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class JobTask implements Runnable {
    private Job job;
    private ImageUtils imgUtils;

    public JobTask(Job job,ImageUtils imgUtils) {
        this.job = job;
        this.imgUtils = imgUtils;
    }

    public void run() { // Start of step 4
        String filePath = job.getImagePath();
        String filename = imgUtils.getLast(filePath,"/");
        Operation[] operations = job.getOperations();
        String outPath = "content/output/";
        for(Operation operation : operations){
            System.out.println(filePath);
            operation.setFilePath(filePath);
            operation.apply(imgUtils,outPath);
            filePath = outPath+"/"+filename;
        }

    } /// End of step 4
}

public class JobScheduler {

    private ImageUtils imgUtils;
    public JobScheduler(){
        imgUtils = new ImageUtils();
    }

    public  void performJobs(Job[] jobs){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (Job job : jobs) {
            // Step No 2
            Runnable worker = new JobTask(job,imgUtils);
            // Step No 3
            executor.execute(worker);
        }
        executor.shutdown();

        // Waiting for all thread to finish
        while (!executor.isTerminated()) ;
        System.out.println("All threads finished");
    }

}
