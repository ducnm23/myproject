package javaframework.watch_manage.converter;

import javaframework.watch_manage.dto.ProductDTO;
import javaframework.watch_manage.dto.OrderDetailDTO;
import javaframework.watch_manage.entities.OrderDetailEntity;
import javaframework.watch_manage.repository.OrderRepos;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailConverter {

    @Autowired
    private OrderRepos orderRepos;

    @Autowired
    private ProductConverter productConverter;

    public OrderDetailDTO toDto(OrderDetailEntity entity) {
        ProductDTO productDTO = new ProductDTO();
        OrderDetailDTO dto = new OrderDetailDTO(productDTO);
        dto.setId(entity.getId());
        dto.setPrice(entity.getPrice());
        dto.setQuantity(entity.getQuantity());
        dto.setSubTotal(entity.getSubTotal());
        dto.setOrder_id(entity.getOrderEntity().getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setModifiedBy(entity.getModifiedBy());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public OrderDetailEntity toEntity(OrderDetailDTO dto) {
        OrderDetailEntity entity = new OrderDetailEntity();
        return getOrderDetailEntity(entity,dto);
    }

    public OrderDetailEntity toEntity(OrderDetailEntity entity, OrderDetailDTO dto) {
        return getOrderDetailEntity(entity,dto);
    }

    @NotNull
    private OrderDetailEntity getOrderDetailEntity(OrderDetailEntity entity, OrderDetailDTO dto) {
        entity.setPrice(dto.getPrice());
        entity.setQuantity(dto.getQuantity());
        entity.setSubTotal(dto.getSubTotal());
        entity.setOrderEntity(orderRepos.findById(dto.getOrder_id()).get());
        entity.setProductEntity(productConverter.toEntity(dto.getProductDTO()));
        entity.setStatus(true);
        return entity;
    }

}
