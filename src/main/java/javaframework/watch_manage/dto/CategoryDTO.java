package javaframework.watch_manage.dto;

import lombok.Data;

@Data
public class CategoryDTO extends AbstractDTO<CategoryDTO>{
    private String code;
    private String name;
}
