package javaframework.watch_manage.converter;

import javaframework.watch_manage.dto.CategoryDTO;
import javaframework.watch_manage.entities.CategoryEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {
    public CategoryDTO toDto(CategoryEntity entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setModifiedBy(entity.getModifiedBy());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public CategoryEntity toEntity(CategoryDTO dto) {
        CategoryEntity entity = new CategoryEntity();
        return getCategoryEntity(entity,dto);
    }

    public CategoryEntity toEntity(CategoryEntity entity, CategoryDTO dto) {
        return getCategoryEntity(entity,dto);
    }

    @NotNull
    private CategoryEntity getCategoryEntity(CategoryEntity entity, CategoryDTO dto) {
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setStatus(true);
        return entity;
    }
}
