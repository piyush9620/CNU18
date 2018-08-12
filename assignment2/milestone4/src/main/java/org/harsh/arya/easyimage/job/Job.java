package org.harsh.arya.easyimage.job;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import org.harsh.arya.easyimage.operation.Operation;


@ToString
@Data
public class Job {
    @JsonProperty("imagePath") private String imagePath;
    @JsonProperty("operations") private  Operation[] operations;

    public Operation[] getOperation(){
        return operations;
    }

}

