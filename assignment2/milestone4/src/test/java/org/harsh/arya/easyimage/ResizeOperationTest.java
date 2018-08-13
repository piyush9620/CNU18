package org.harsh.arya.easyimage;

import ij.ImagePlus;
import ij.process.ImageProcessor;
import org.apache.commons.lang3.tuple.Pair;
import org.harsh.arya.easyimage.operation.ResizeOperation;
import org.harsh.arya.easyimage.operation.ScaleOperation;
import org.harsh.arya.easyimage.utils.ImageUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.awt.image.BufferedImage;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ResizeOperationTest {
    ResizeOperation resizeOperation;

    @Mock
    ImageUtils imageUtils;
    @Mock
    public ImagePlus imagePlus;
    @Mock
    public ImageProcessor imageProcessor;

    @Test
    public void resizeConstructorApplyTest(){
        //imageUtils = Mockito.mock(ImageUtils.class);
        Mockito.doNothing().when(imageUtils).writeFile(isA(BufferedImage.class),isA(String.class),isA(String.class),isA(String.class));
        //when(imageUtils.returnID()).thenReturn(23);
        resizeOperation = new ResizeOperation();
        resizeOperation.setHeight(450);
        resizeOperation.setWidth(450);
        resizeOperation.setFilePath("a.jpg");
        resizeOperation.apply(imageUtils,"");
        verify(imageUtils, times(1)).writeFile(isA(BufferedImage.class),isA(String.class),isA(String.class),isA(String.class));

    }

    @Test
    public void resizeNullValueTests(){
        resizeOperation = new ResizeOperation();
        Pair<Integer,Integer> heightWidth = resizeOperation.getSizeParams(10,20,null,60);
        assertEquals(heightWidth.getKey(),30,0);
        assertEquals(heightWidth.getValue(),60,0);
        heightWidth = resizeOperation.getSizeParams(10,20,30,null);
        assertEquals(heightWidth.getKey(),30,0);
        assertEquals(heightWidth.getValue(),60,0);
    }

    @Test
    public void resizeImageTest(){
        resizeOperation=new ResizeOperation();
        Mockito.when(imagePlus.getProcessor()).thenReturn(imageProcessor);
        Mockito.when(imageProcessor.getWidth()).thenReturn(20);
        Mockito.when(imageProcessor.getHeight()).thenReturn(10);
        resizeOperation.setWidth(60);
        resizeOperation.setHeight(30);
        resizeOperation.resizeImage(imagePlus);
        verify(imageProcessor, times(1)).resize(60, 30);
    }


}
