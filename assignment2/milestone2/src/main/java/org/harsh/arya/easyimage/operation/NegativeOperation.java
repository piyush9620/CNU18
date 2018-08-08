package org.harsh.arya.easyimage.operation;

import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;
import lombok.ToString;
import org.harsh.arya.easyimage.utils.ImageUtils;

@ToString
public class NegativeOperation extends Operation {

    public void apply(ImageUtils imageUtils,String outPath){
        String filename = imageUtils.getLast(filePath,"/");
        String fileext = imageUtils.getLast(filename,"\\.");

        ImageProcessor ip = rotateImage(filePath);
        imageUtils.writeFile(ip.getBufferedImage(),fileext,filename,outPath);
    }

    private ImageProcessor rotateImage(String filePath){
        ImagePlus imp = IJ.openImage(filePath);
        ImageProcessor ip = imp.getProcessor();
        ip.invert();
        return ip;
    }
}
