package org.harsh.arya.easyimage.operation;

import com.fasterxml.jackson.annotation.JsonProperty;
import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;
import lombok.ToString;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.harsh.arya.easyimage.utils.ImageUtils;

@ToString
public class ScaleOperation extends Operation {

    @JsonProperty("height") public Float height;
    @JsonProperty("width") public Float width;

    public void apply(ImageUtils imageUtils,String outPath){
        String filename = imageUtils.getLast(filePath,"/");
        String fileext = imageUtils.getLast(filename,"\\.");
        ImageProcessor ip = scaleImage(filePath,height,width);
        imageUtils.writeFile(ip.getBufferedImage(),fileext,filename,outPath);
    }

    private ImageProcessor scaleImage(String filePath,Float height,Float width){
        ImagePlus imp = IJ.openImage(filePath);
        ImageProcessor ip = imp.getProcessor();
        if(height !=null || width != null){
            Pair<Float,Float> newParams = getSizeParams(height,width);
            int targetHeight = (int)((float)imp.getHeight() * height.floatValue());
            int targetWidth = (int)((float)imp.getWidth() * width.floatValue());
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
        System.out.println(nHeight+"x"+nWidth);
        return new ImmutablePair<>(nHeight,nWidth);
    }

}
