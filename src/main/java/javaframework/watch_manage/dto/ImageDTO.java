package javaframework.watch_manage.dto;

import lombok.Data;

@Data
public class ImageDTO extends AbstractDTO<ImageDTO>{
    private String path;
    private Boolean is_preview;
    private Long productId;
    private String productName;
}
