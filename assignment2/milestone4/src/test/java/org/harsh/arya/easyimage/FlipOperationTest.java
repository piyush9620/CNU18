package org.harsh.arya.easyimage;

import ij.ImagePlus;
import ij.process.ImageProcessor;
import org.harsh.arya.easyimage.operation.FlipOperation;
import org.harsh.arya.easyimage.operation.ResizeOperation;
import org.harsh.arya.easyimage.operation.RotateOperation;
import org.harsh.arya.easyimage.utils.ImageUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.awt.image.BufferedImage;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.Silent.class)
public class FlipOperationTest {
    FlipOperation flipOperation;

    @Mock
    ImageUtils imageUtils;


    @Mock
    public ImagePlus imagePlus;
    @Mock
    public ImageProcessor imageProcessor;

    @Test
    public void flipConstructorApplyTest(){
        //imageUtils = Mockito.mock(ImageUtils.class);
        Mockito.doNothing().when(imageUtils).writeFile(isA(BufferedImage.class),isA(String.class),isA(String.class),isA(String.class));
        //when(imageUtils.returnID()).thenReturn(23);
        flipOperation = new FlipOperation();
        flipOperation.setOrientation("horizontal");
        flipOperation.setFilePath("a.jpg");
        flipOperation.apply(imageUtils,"");
        verify(imageUtils, times(1)).writeFile(isA(BufferedImage.class),isA(String.class),isA(String.class),isA(String.class));
    }

    @Test
    public void flipImageTest(){
        flipOperation=new FlipOperation();
        Mockito.when(imagePlus.getProcessor()).thenReturn(imageProcessor);
        flipOperation.flipImage(imagePlus,false);
        verify(imageProcessor, times(1)).flipHorizontal();
        flipOperation.flipImage(imagePlus,true);
        verify(imageProcessor, times(1)).flipVertical();
    }

}
