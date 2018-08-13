package org.harsh.arya.easyimage.utils;


import lombok.extern.slf4j.Slf4j;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


@Slf4j
public class ImageUtils {

    public void writeFile(BufferedImage resizedImage,String fileext,String filename,String outPath){
        try{
            ImageIO.write(resizedImage, fileext, new File(outPath+filename));
        }catch(IOException e){
            log.error(e.toString());
        }
    }


}
