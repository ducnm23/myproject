package javaframework.watch_manage.converter;

import javaframework.watch_manage.dto.ProductDTO;
import javaframework.watch_manage.entities.ProductEntity;
import javaframework.watch_manage.repository.CategoryRepos;
import javaframework.watch_manage.repository.ProductGroupRepos;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    @Autowired
    private ImageConverter imageConverter;

    @Autowired
    private CategoryRepos categoryRepos;

    @Autowired
    private ProductGroupRepos productGroupRepos;

    public ProductDTO toDto(ProductEntity entity) {
        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setPricePromotion(entity.getPrice_promotion());
        dto.setDescription(entity.getDescription());
        dto.setCategoryId(entity.getCategoryEntity().getId());
        dto.setCategoryName(entity.getCategoryEntity().getName());
        dto.setProductGroupId(entity.getProductGroupEntity().getId());
        dto.setProductGroupName(entity.getProductGroupEntity().getName());
        if( entity.getImageEntities() != null ) {
            entity.getImageEntities().stream().forEach(i -> {
                dto.getImageDTOS().add(imageConverter.toDto(i));
            });
        }
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setModifiedBy(entity.getModifiedBy());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public ProductEntity toEntity(ProductDTO dto) {
        ProductEntity entity = new ProductEntity();
        return getProductEntity(entity,dto);
    }

    public ProductEntity toEntity(ProductEntity entity, ProductDTO dto) {
        return getProductEntity(entity,dto);
    }

    @NotNull
    private ProductEntity getProductEntity(ProductEntity entity, ProductDTO dto) {
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        if( dto.getPricePromotion() != null ) entity.setPrice_promotion(dto.getPricePromotion());
        else entity.setPrice_promotion(0l);
        entity.setDescription(dto.getDescription());
        entity.setCategoryEntity(categoryRepos.findById(dto.getCategoryId()).get());
        entity.setProductGroupEntity(productGroupRepos.findById(dto.getProductGroupId()).get());
        entity.setStatus(true);
        return entity;
    }
}
