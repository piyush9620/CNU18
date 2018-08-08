package org.harsh.arya.easyimage.utils.jsonparser;

import lombok.Data;

@Data
public class Operation {
    private String filePath;
    private imgOpTypes opType;
    private int height;
    private int width;

}
