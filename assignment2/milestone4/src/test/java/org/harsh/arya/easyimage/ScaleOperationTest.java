package org.harsh.arya.easyimage;

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
public class ScaleOperationTest {

    @Mock
    ImageUtils imageUtils;

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


}
