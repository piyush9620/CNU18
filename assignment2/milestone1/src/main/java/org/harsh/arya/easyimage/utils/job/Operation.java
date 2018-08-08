package org.harsh.arya.easyimage.utils.job;

import lombok.Data;

@Data
public class Operation {
    private String filePath;
    private imgOpTypes opType;
    private Integer height;
    private Integer width;

}
