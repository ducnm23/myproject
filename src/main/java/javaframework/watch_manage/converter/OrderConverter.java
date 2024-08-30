package javaframework.watch_manage.converter;

import javaframework.watch_manage.dto.OrderDTO;
import javaframework.watch_manage.entities.OrderEntity;
import javaframework.watch_manage.repository.UserRepos;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class OrderConverter {

    @Autowired
    private UserRepos userRepos;

    public OrderDTO toDto(OrderEntity entity) {
        OrderDTO dto = new OrderDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setTotal(entity.getTotal());
        dto.setOrderStatus(entity.getOrderStatus());
        dto.setUser_customer_id(entity.getUserCustomerEntity().getId());
        dto.setUser_customer_name(entity.getUserCustomerEntity().getUsername());
        dto.setUser_manager_id(entity.getUserManagerEntity().getId());
        dto.setUser_manager_name(entity.getUserManagerEntity().getUsername());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setModifiedBy(entity.getModifiedBy());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public OrderEntity toEntity(OrderDTO dto) {
        OrderEntity entity = new OrderEntity();
        return getOrderEntity(entity,dto);
    }

    public OrderEntity toEntity(OrderEntity entity, OrderDTO dto) {
        return getOrderEntity(entity,dto);
    }

    @NotNull
    private OrderEntity getOrderEntity(OrderEntity entity, OrderDTO dto) {
        entity.setCode(dto.getCode());
        entity.setTotal(dto.getTotal());
        entity.setOrderStatus(dto.getOrderStatus());
        entity.setUserCustomerEntity(userRepos.findById(dto.getUser_customer_id()).get());
        entity.setUserManagerEntity(userRepos.findById(dto.getUser_manager_id()).get());
        entity.setStatus(true);
        return entity;
    }
}
