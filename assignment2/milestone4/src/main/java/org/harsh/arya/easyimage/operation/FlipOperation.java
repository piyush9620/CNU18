package org.harsh.arya.easyimage.operation;

import com.fasterxml.jackson.annotation.JsonProperty;
import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.harsh.arya.easyimage.utils.ImageUtils;

@Slf4j
@Data
@ToString
public class FlipOperation extends Operation{

    @JsonProperty("orientation") private String orientation;

    public void apply(ImageUtils imageUtils,String outPath){
        String filename = FilenameUtils.getName(filePath);
        String fileext = FilenameUtils.getExtension(filename);
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
            log.info("Flipping Vertical");
        }else{
            ip.flipHorizontal();
            log.info("Flipping Horizontal");
        }
        return ip;
    }


}
