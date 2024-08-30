package javaframework.watch_manage.converter;

import javaframework.watch_manage.dto.ProductGroupDTO;
import javaframework.watch_manage.entities.ProductGroupEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class ProductGroupConverter {
    public ProductGroupDTO toDto(ProductGroupEntity entity) {
        ProductGroupDTO dto = new ProductGroupDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setModifiedBy(entity.getModifiedBy());
        return dto;
    }

    public ProductGroupEntity toEntity(ProductGroupDTO dto) {
        ProductGroupEntity entity = new ProductGroupEntity();
        return getProductGroupEntity(entity,dto);
    }

    public ProductGroupEntity toEntity(ProductGroupEntity entity, ProductGroupDTO dto) {
        return getProductGroupEntity(entity,dto);
    }

    @NotNull
    private ProductGroupEntity getProductGroupEntity(ProductGroupEntity entity, ProductGroupDTO dto) {
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setStatus(true);
        return entity;
    }
}
