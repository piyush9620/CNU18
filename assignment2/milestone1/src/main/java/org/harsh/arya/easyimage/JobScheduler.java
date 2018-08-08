package org.harsh.arya.easyimage;

import org.harsh.arya.easyimage.utils.ImageUtils;
import org.harsh.arya.easyimage.utils.jsonparser.Job;
import org.harsh.arya.easyimage.utils.jsonparser.Operation;
import org.harsh.arya.easyimage.utils.jsonparser.imgOpTypes;


public class JobScheduler {

    public static void applyOperation(Operation operation){
        switch(operation.getOpType()){
            case RESIZE:
                ImageUtils.resizeImage(operation.getFilePath(),operation.getHeight(),operation.getWidth());
                break;
        }
    }

    public static void performJob(Job job){
        Operation op = new Operation();
        op.setOpType(imgOpTypes.RESIZE);
        op.setHeight(job.getHeight());
        op.setWidth(job.getWidth());
        op.setFilePath(job.getImagePath());
        applyOperation(op);
    }
}
