package javaframework.watch_manage.dto;

import lombok.Data;

@Data
public class ProductGroupDTO extends AbstractDTO<ProductGroupDTO>{
    private String code;
    private String name;
}
