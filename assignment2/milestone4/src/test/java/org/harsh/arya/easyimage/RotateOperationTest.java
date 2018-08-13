package org.harsh.arya.easyimage;

import ij.ImagePlus;
import ij.process.ImageProcessor;
import org.harsh.arya.easyimage.operation.ResizeOperation;
import org.harsh.arya.easyimage.operation.RotateOperation;
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
public class RotateOperationTest {
    RotateOperation rotateOperation;

    @Mock
    ImageUtils imageUtils;

    @Mock
    public ImagePlus imagePlus;
    @Mock
    public ImageProcessor imageProcessor;

    @Test
    public void rotateConstructorTest(){
        rotateOperation = new RotateOperation();
        rotateOperation.setDegree(370);
        Integer degree = rotateOperation.getDegree();
        assertEquals(degree,10,0);

    }

    @Test
    public void rotateConstructorTest1(){
        rotateOperation = new RotateOperation();
        rotateOperation.setDegree(-10);
        Integer degree = rotateOperation.getDegree();
        assertEquals(degree,350,0);
    }

    @Test
    public void rotateApplyTest(){
        //imageUtils = Mockito.mock(ImageUtils.class);
        Mockito.doNothing().when(imageUtils).writeFile(isA(BufferedImage.class),isA(String.class),isA(String.class),isA(String.class));
        //when(imageUtils.returnID()).thenReturn(23);
        rotateOperation = new RotateOperation();
        rotateOperation.setFilePath("a.jpg");
        rotateOperation.setDegree(370);
        rotateOperation.apply(imageUtils,"");
        verify(imageUtils, times(1)).writeFile(isA(BufferedImage.class),isA(String.class),isA(String.class),isA(String.class));

    }

    @Test
    public void rotateImageTest(){
        rotateOperation=new RotateOperation();
        Mockito.when(imagePlus.getProcessor()).thenReturn(imageProcessor);
        rotateOperation.setDegree(60);
        rotateOperation.rotateImage(imagePlus);
        verify(imageProcessor, times(1)).rotate(60);
        rotateOperation.setDegree(-10);
        rotateOperation.rotateImage(imagePlus);
        verify(imageProcessor, times(1)).rotate(350);
    }


}
