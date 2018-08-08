package org.harsh.arya.easyimage.utils.job;

import org.harsh.arya.easyimage.utils.ImageUtils;



public class JobScheduler {

    private ImageUtils imgUtils;
    public JobScheduler(){
        imgUtils = new ImageUtils();
    }

    private  void applyOperation(Operation operation){
        switch(operation.getOpType()){
            case RESIZE:
                imgUtils.resizeImage(operation.getFilePath(),operation.getHeight(),operation.getWidth());
                break;
        }
    }

    public  void performJob(Job job){
        Operation op = new Operation();
        op.setOpType(imgOpTypes.RESIZE);
        op.setHeight(job.getHeight());
        op.setWidth(job.getWidth());
        op.setFilePath(job.getImagePath());
        applyOperation(op);
    }
}
