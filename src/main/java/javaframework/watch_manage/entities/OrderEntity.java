package javaframework.watch_manage.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table(name = "order_product")
public class OrderEntity extends BaseEntity {

    @Column(name = "code")
    private String code;

    @Column(name = "total")
    private Long total;

    @Column(name = "order_status")
    private Integer orderStatus;

    @OneToMany(mappedBy = "orderEntity")
    private Collection<OrderDetailEntity> orderDetailEntities;

    @ManyToOne
    @JoinColumn(name = "user_manager_id")
    private UserEntity userManagerEntity;

    @ManyToOne
    @JoinColumn(name = "user_customer_id")
    private UserEntity userCustomerEntity;

}
