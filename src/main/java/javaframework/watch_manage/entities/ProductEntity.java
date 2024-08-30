package javaframework.watch_manage.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="product")
//
public class ProductEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Long price;

    @Column(name = "price_promotion")
    private Long price_promotion;

    @ManyToOne
    @JoinColumn(name = "group_product_id")
    private ProductGroupEntity productGroupEntity;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;

    @OneToMany(mappedBy = "productEntity")
    private Collection<OrderDetailEntity> orderDetailEntities;

    @OneToMany(mappedBy = "productEntity")
    private Collection<ImageEntity> imageEntities;

}
