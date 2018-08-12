package org.harsh.arya.easyimage;

import org.harsh.arya.easyimage.operation.NegativeOperation;
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
public class NegativeOperationTest {
    NegativeOperation negativeOperation;

    @Mock
    ImageUtils imageUtils;

    @Test
    public void negativeConstructorApplyTest(){
        //imageUtils = Mockito.mock(ImageUtils.class);
        Mockito.doNothing().when(imageUtils).writeFile(isA(BufferedImage.class),isA(String.class),isA(String.class),isA(String.class));
        //when(imageUtils.returnID()).thenReturn(23);
        negativeOperation = new NegativeOperation();
        negativeOperation.setFilePath("a.jpg");
        negativeOperation.apply(imageUtils,"");
        verify(imageUtils, times(1)).writeFile(isA(BufferedImage.class),isA(String.class),isA(String.class),isA(String.class));

    }
}
