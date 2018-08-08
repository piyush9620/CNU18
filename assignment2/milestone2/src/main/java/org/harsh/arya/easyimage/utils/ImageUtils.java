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



    public  void resizeImage(String filepath,Integer height,Integer width){

    }

    public  String getLast(String oStr,String divider){
        String [] parts = oStr.split(divider);
        String name = parts[parts.length -1 ];
        return name;
    }

    public void writeFile(BufferedImage resizedImage,String fileext,String filename,String outPath){
        try{
            ImageIO.write(resizedImage, fileext, new File(outPath+filename));
        }catch(IOException e){
            System.out.println(e);
        }
    }




}
