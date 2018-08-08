package org.harsh.arya.easyimage.operation;

import com.fasterxml.jackson.annotation.JsonProperty;
import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;
import lombok.Data;
import lombok.ToString;
import org.harsh.arya.easyimage.utils.ImageUtils;

@Data
@ToString
public class FlipOperation extends Operation{

    @JsonProperty("orientation") private String orientation;

    public void apply(ImageUtils imageUtils,String outPath){
        String filename = imageUtils.getLast(filePath,"/");
        String fileext = imageUtils.getLast(filename,"\\.");
        boolean isVertical = true;
        if (orientation.equals("horizontal") ){
            isVertical = false;
        }
        ImageProcessor ip = flipImage(filePath,isVertical);
        imageUtils.writeFile(ip.getBufferedImage(),fileext,filename,outPath);
    }

    private ImageProcessor flipImage(String filePath, boolean isVertical){
        ImagePlus imp = IJ.openImage(filePath);
        ImageProcessor ip = imp.getProcessor();
        if (isVertical){
            ip.flipVertical();
            System.out.println("Flipping Vertical");
        }else{
            ip.flipHorizontal();
            System.out.println("Flipping Horizontal");
        }
        return ip;
    }


}
