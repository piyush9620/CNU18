package org.harsh.arya.easyimage.utils;

import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtils {

    private static Pair<Integer,Integer> getSizeParams(int oHeight,int oWidth,int nHeight,int nWidth){
        if(nHeight == 0){
            nHeight = (oHeight*nWidth)/oWidth;
        }else if(nWidth ==0){
            nWidth = (nHeight*oWidth)/oHeight;
        }
        return new ImmutablePair<>(nHeight,nWidth);
    }

    public static void resizeImage(String filepath,int height,int width){
        System.out.println(filepath);
        String filename = getLast(filepath,"/");
        System.out.println(filename);
        String fileext = getLast(filename,"\\.");
        System.out.println(fileext);
        ImagePlus imp = IJ.openImage(filepath);
        ImageProcessor ip = imp.getProcessor();
        Pair<Integer,Integer> newParams = getSizeParams(ip.getHeight(),ip.getWidth(),height,width);
        ip = ip.resize(newParams.getValue(), newParams.getKey());
        BufferedImage resizedImage = ip.getBufferedImage();
        try{
            ImageIO.write(resizedImage, fileext, new File("/var/data/output/"+filename));
        }catch(IOException e){

        }
    }

    private static String getLast(String oStr,String divider){
        String [] fileparts = oStr.split(divider);
        String name = fileparts[fileparts.length -1 ];
        return name;
    }


}
