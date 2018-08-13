package org.harsh.arya.easyimage.operation;

import com.fasterxml.jackson.annotation.JsonProperty;
import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.harsh.arya.easyimage.utils.ImageUtils;

@Data
@Slf4j
@ToString
public class ScaleOperation extends Operation {

    @JsonProperty("height") public Float height;
    @JsonProperty("width") public Float width;

    public void apply(ImageUtils imageUtils,String outPath){
        String filename = FilenameUtils.getName(filePath);
        String fileext = FilenameUtils.getExtension(filename);
        ImagePlus imp = IJ.openImage(filePath);
        ImageProcessor ip = scaleImage(imp);
        imageUtils.writeFile(ip.getBufferedImage(),fileext,filename,outPath);
    }

    public ImageProcessor scaleImage(ImagePlus imp){
        ImageProcessor ip = imp.getProcessor();
        if((height !=null || width != null)){
            Pair<Float,Float> newParams = getSizeParams(height,width);
            if(newParams.getKey() == 0 || newParams.getValue() ==0){
                return ip;
            }
            int targetHeight = (int)((float)ip.getHeight() * newParams.getKey().floatValue());
            int targetWidth = (int)((float)ip.getWidth() * newParams.getValue().floatValue());
            System.out.println(targetHeight+" "+targetWidth);
            ip = ip.resize(targetWidth, targetHeight);
            return ip;
        }
        else{
            return ip;
        }

    }

    public  Pair<Float,Float> getSizeParams(Float nHeight,Float nWidth){
        if(nHeight == null){
            nHeight = nWidth;
        }else if(nWidth == null){
            nWidth = nHeight;
        }
        log.info(nHeight+"x"+nWidth);
        return new ImmutablePair<>(nHeight,nWidth);
    }



    public ScaleOperation(@JsonProperty("width") Float width, @JsonProperty("height") Float height) {
        this.width = width;
        this.height = height;

        if (this.width == null && this.height == null)
            return;
        if (this.width == null)
            this.width = this.height;
        if (this.height == null)
            this.height = this.width;
    }


}
