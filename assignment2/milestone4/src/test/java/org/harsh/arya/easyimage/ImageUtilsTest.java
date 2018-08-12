package org.harsh.arya.easyimage;


import org.harsh.arya.easyimage.utils.ImageUtils;
import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


public class ImageUtilsTest {

    @Test
    public void osTest(){
        Assume.assumeFalse(System.getProperty("os.name").contains("Linux"));
    }


}
