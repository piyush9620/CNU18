package org.harsh.arya.easyimage.operation;

import com.fasterxml.jackson.annotation.JsonProperty;
import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;
import lombok.ToString;
import org.harsh.arya.easyimage.utils.ImageUtils;

@ToString
public class RotateOperation extends Operation{

    @JsonProperty("degrees") public Integer degree;

    public void apply(ImageUtils imageUtils,String outPath){
        String filename = imageUtils.getLast(filePath,"/");
        String fileext = imageUtils.getLast(filename,"\\.");

        ImageProcessor ip = rotateImage(filePath,degree);
        imageUtils.writeFile(ip.getBufferedImage(),fileext,filename,outPath);
    }

    private ImageProcessor rotateImage(String filePath, Integer degree){
        ImagePlus imp = IJ.openImage(filePath);
        ImageProcessor ip = imp.getProcessor();
        ip.rotate(degree);
        return ip;
    }

}
