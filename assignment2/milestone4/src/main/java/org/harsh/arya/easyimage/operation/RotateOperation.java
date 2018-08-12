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

@Data
@Slf4j
@ToString
public class RotateOperation extends Operation{

    @JsonProperty("degrees") public Integer degree;

    public void apply(ImageUtils imageUtils,String outPath){
        String filename = FilenameUtils.getName(filePath);
        String fileext = FilenameUtils.getExtension(filename);

        ImageProcessor ip = rotateImage(filePath,degree);
        imageUtils.writeFile(ip.getBufferedImage(),fileext,filename,outPath);
    }

    private ImageProcessor rotateImage(String filePath, Integer degree){
        ImagePlus imp = IJ.openImage(filePath);
        ImageProcessor ip = imp.getProcessor();
        log.info(ip.getHeight()+"x"+ip.getWidth());
        if(degree!=null){
            ip.rotate(degree);
        }
        return ip;
    }

    public void setDegree(Integer degree) {
        if(degree == null){
            return ;
        }
        this.degree = (degree%360 +360)%360;
    }
}
