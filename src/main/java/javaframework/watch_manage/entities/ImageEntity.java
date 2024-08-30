package javaframework.watch_manage.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "image")
public class ImageEntity extends BaseEntity{

    @Column(name = "path")
    private String path;

    @Column(name = "is_preview")
    private Boolean isPreview;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

}
