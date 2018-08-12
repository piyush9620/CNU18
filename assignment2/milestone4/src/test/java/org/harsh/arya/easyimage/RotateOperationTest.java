package org.harsh.arya.easyimage;

import org.harsh.arya.easyimage.operation.RotateOperation;
import org.harsh.arya.easyimage.utils.ImageUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.awt.image.BufferedImage;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class RotateOperationTest {
    RotateOperation rotateOperation;

    @Mock
    ImageUtils imageUtils;

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



}
