package javaframework.watch_manage.repository;

import javaframework.watch_manage.entities.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepos extends PagingAndSortingRepository<ProductEntity, Long> {
    @Query("Select f from ProductEntity f where f.categoryEntity.code = ?1")
    public List<ProductEntity> findByCategoryCode(String categoryCode);

    @Query("Select f from ProductEntity f where f.productGroupEntity.id = ?1")
    public List<ProductEntity> findByProductGroupId(Long productGroupId);

    @Query("Select f from ProductEntity f where f.price_promotion > 0")
    public List<ProductEntity> findAllHavePricePromotion();

    @Query("select f from ProductEntity f where concat(f.name, f.description, f.price, f.price_promotion, f.categoryEntity.name, f.productGroupEntity.name) like %?1%")
    public List<ProductEntity> findAllSearch(String keyword, Pageable pageable);

    @Query("select f from ProductEntity f where concat(f.name, f.description, f.price, f.price_promotion, f.categoryEntity.name, f.productGroupEntity.name) like %?1%")
    public List<ProductEntity> findAllSearch(String keyword);

    @Query("Select Count(f) from ProductEntity f where concat(f.name, f.description, f.price, f.price_promotion, f.categoryEntity.name, f.productGroupEntity.name) like %?1%")
    public int countSearch(String keyword);

    @Query("select distinct f.categoryEntity.id from ProductEntity f")
    public List<Long> getListCategoryIdUnduplicated();

    @Query("select distinct f.productGroupEntity.id from ProductEntity f")
    public List<Long> getListProductGroupIdUnduplicated();

}
