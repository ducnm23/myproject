package javaframework.watch_manage.converter;

import javaframework.watch_manage.dto.ImageDTO;
import javaframework.watch_manage.entities.ImageEntity;
import javaframework.watch_manage.repository.ProductRepos;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImageConverter {

    @Autowired
    private ProductRepos productRepos;

    public ImageDTO toDto(ImageEntity entity) {
        ImageDTO dto = new ImageDTO();
        dto.setId(entity.getId());
        dto.setPath(entity.getPath());
        dto.setIs_preview(entity.getIsPreview());
        dto.setProductId(entity.getProductEntity().getId());
        dto.setProductName(entity.getProductEntity().getName());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setModifiedBy(entity.getModifiedBy());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public ImageEntity toEntity(ImageDTO dto) {
        ImageEntity entity = new ImageEntity();
        return getImageEntity(entity,dto);
    }

    public ImageEntity toEntity(ImageEntity entity, ImageDTO dto) {
        return getImageEntity(entity,dto);
    }

    @NotNull
    private ImageEntity getImageEntity(ImageEntity entity, ImageDTO dto) {
        if( dto.getPath() != null ) entity.setPath(dto.getPath());
        entity.setIsPreview(dto.getIs_preview());
        entity.setProductEntity(productRepos.findById(dto.getProductId()).get());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setModifiedDate(dto.getModifiedDate());
        entity.setModifiedBy(dto.getModifiedBy());
        entity.setStatus(true);
        return entity;
    }
}
