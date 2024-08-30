package javaframework.watch_manage.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDTO extends AbstractDTO<ProductDTO> {
    private String name;
    private Long price;
    private Long pricePromotion;
    private String description;
    private Long categoryId;
    private Long productGroupId;
    private String categoryName;
    private String productGroupName;
    private List<ImageDTO> imageDTOS = new ArrayList<>();
}
