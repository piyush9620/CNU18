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
import java.nio.file.Files;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;


public class ImageUtils {





    public  String getLast(String oStr,String divider){
        String [] parts = oStr.split(divider);
        String name = parts[parts.length -1 ];
        return name;
    }

    public void writeFile(BufferedImage resizedImage,String fileext,String filename,String outPath){
        System.out.println(outPath);
        try{
            ImageIO.write(resizedImage, fileext, new File(outPath+filename));
        }catch(IOException e){
            System.out.println(e);
        }
    }

    public void copy(String srcPath,String destPath){
        File srcFile = new File(srcPath);
        File destFile =new  File(destPath);
        try{
            Files.copy(srcFile.toPath(),destFile.toPath(),REPLACE_EXISTING);
        }catch(IOException e){
            System.out.println(e);
        }

    }




}
