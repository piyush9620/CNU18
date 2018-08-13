package org.harsh.arya.easyimage;

import ij.ImagePlus;
import ij.process.ImageProcessor;
import org.apache.commons.lang3.tuple.Pair;
import org.harsh.arya.easyimage.operation.ResizeOperation;
import org.harsh.arya.easyimage.operation.ScaleOperation;
import org.harsh.arya.easyimage.utils.ImageUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ScaleOperationTest {

    @Mock
    ImageUtils imageUtils;

    @Mock
    public ImagePlus imagePlus;
    @Mock
    public ImageProcessor imageProcessor;

    ScaleOperation scaleOperation;




    @Test
    public void scaleConstructorTest(){
        scaleOperation = new ScaleOperation(null,(float)0.7);
        float height = scaleOperation.getHeight();
        float width = scaleOperation.getWidth();
        assertEquals(height,0.7,0.01);
        assertEquals(width,0.7,0.01);

    }

    @Test
    public void scaleConstructorTest1(){
        scaleOperation = new ScaleOperation((float)0.7,null);
        float height = scaleOperation.getHeight();
        float width = scaleOperation.getWidth();
        assertEquals(height,0.7,0.01);
        assertEquals(width,0.7,0.01);

    }

    @Test
    public void scaleConstructorTest2(){
        scaleOperation = new ScaleOperation((float)0.7,(float)0.7);
        float height = scaleOperation.getHeight();
        float width = scaleOperation.getWidth();
        assertEquals(height,0.7,0.01);
        assertEquals(width,0.7,0.01);

    }

    @Test
    public void scaleConstructorApplyTest(){
        //imageUtils = Mockito.mock(ImageUtils.class);
        Mockito.doNothing().when(imageUtils).writeFile(isA(BufferedImage.class),isA(String.class),isA(String.class),isA(String.class));
        //when(imageUtils.returnID()).thenReturn(23);
        scaleOperation = new ScaleOperation((float)0.7,(float)0.7);
        scaleOperation.setFilePath("a.jpg");
        scaleOperation.apply(imageUtils,"");
        verify(imageUtils, times(1)).writeFile(isA(BufferedImage.class),isA(String.class),isA(String.class),isA(String.class));

    }


    @Test
    public void scaleImageTest(){
        scaleOperation=new ScaleOperation((Float)1.0f,(Float)1.0f);
        Mockito.when(imagePlus.getProcessor()).thenReturn(imageProcessor);
        Mockito.when(imageProcessor.getWidth()).thenReturn(15);
        Mockito.when(imageProcessor.getHeight()).thenReturn(25);
        scaleOperation.scaleImage(imagePlus);
        verify(imageProcessor, times(1)).resize(15, 25);
    }

    @Test
    public void resizeNullValueTests(){
        scaleOperation = new ScaleOperation(0.7f,null);
        Pair<Float,Float> heightWidth = scaleOperation.getSizeParams(null,0.7f);
        assertEquals(heightWidth.getKey(),0.7f,0.01f);
        assertEquals(heightWidth.getValue(),0.7f,0.01f);
        heightWidth = scaleOperation.getSizeParams(0.7f,null);
        assertEquals(heightWidth.getKey(),0.7f,0.01f);
        assertEquals(heightWidth.getValue(),0.7f,0.01f);
    }


}
