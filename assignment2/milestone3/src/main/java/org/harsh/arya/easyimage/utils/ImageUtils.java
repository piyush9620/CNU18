package org.harsh.arya.easyimage.utils;


import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Slf4j
public class ImageUtils {
    public  String getLast(String oStr,String divider){
        String [] parts = oStr.split(divider);
        String name = parts[parts.length -1 ];
        return name;
    }

    public void writeFile(BufferedImage resizedImage,String fileext,String filename,String outPath){
        try{
            ImageIO.write(resizedImage, fileext, new File(outPath+filename));
        }catch(IOException e){
            log.error(e.toString());
        }
    }

    public void copy(String srcPath,String destPath){
        File srcFile = new File(srcPath);
        File destFile =new  File(destPath);
        try{
            Files.copy(srcFile.toPath(),destFile.toPath(),REPLACE_EXISTING);
        }catch(IOException e){
           log.error(e.toString());
        }

    }

}
