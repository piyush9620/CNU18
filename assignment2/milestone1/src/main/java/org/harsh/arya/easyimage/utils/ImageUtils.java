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

    private  Pair<Integer,Integer> getSizeParams(int oHeight,int oWidth,Integer nHeight,Integer nWidth){
        if(nHeight == null){
            nHeight = (oHeight*nWidth)/oWidth;
        }else if(nWidth == null){
            nWidth = (nHeight*oWidth)/oHeight;
        }
        System.out.println(nHeight+"x"+nWidth);
        return new ImmutablePair<>(nHeight,nWidth);
    }

    public  void resizeImage(String filepath,Integer height,Integer width){
        String filename = getLast(filepath,"/");
        String fileext = getLast(filename,"\\.");
        ImagePlus imp = IJ.openImage(filepath);
        ImageProcessor ip = imp.getProcessor();
        Pair<Integer,Integer> newParams = getSizeParams(ip.getHeight(),ip.getWidth(),height,width);
        ip = ip.resize(newParams.getValue(), newParams.getKey());
        writeFile(ip.getBufferedImage(),fileext,filename);
    }

    private  String getLast(String oStr,String divider){
        String [] parts = oStr.split(divider);
        String name = parts[parts.length -1 ];
        return name;
    }

    private void writeFile(BufferedImage resizedImage,String fileext,String filename){
        try{
            ImageIO.write(resizedImage, fileext, new File("/var/data/output/"+filename));
        }catch(IOException e){
            System.out.println(e);
        }
    }


}
