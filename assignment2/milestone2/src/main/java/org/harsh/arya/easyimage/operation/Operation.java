package org.harsh.arya.easyimage.operation;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.harsh.arya.easyimage.utils.ImageUtils;

@Data
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")

@JsonSubTypes({
        @JsonSubTypes.Type(value = ResizeOperation.class, name = "resize"),
        @JsonSubTypes.Type(value = ScaleOperation.class, name = "scale"),
        @JsonSubTypes.Type(value = RotateOperation.class, name = "rotate"),
        @JsonSubTypes.Type(value = FlipOperation.class, name = "flip"),
        @JsonSubTypes.Type(value = NegativeOperation.class, name = "negative")
})


@ToString
public abstract class Operation {
    @Getter @Setter protected String filePath;
    public abstract void apply(ImageUtils imageUtils,String outPath);

    public void setFilePath(String filePath){
        this.filePath = filePath;
    }
}
