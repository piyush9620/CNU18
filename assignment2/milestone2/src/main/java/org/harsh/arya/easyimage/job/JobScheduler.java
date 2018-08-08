package org.harsh.arya.easyimage.job;

import org.harsh.arya.easyimage.utils.ImageUtils;



public class JobScheduler {

    private ImageUtils imgUtils;
    public JobScheduler(){
        imgUtils = new ImageUtils();
    }

    public  void performJob(Job job){
        job.getOperation().setFilePath(job.getImagePath());
        job.getOperation().apply(imgUtils,"/var/data/output/");
    }

}
