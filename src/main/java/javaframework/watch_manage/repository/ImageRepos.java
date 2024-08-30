package javaframework.watch_manage.repository;

import javaframework.watch_manage.entities.ImageEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepos extends PagingAndSortingRepository<ImageEntity, Long> {
    @Query("select f from ImageEntity f where concat(f.productEntity.name, f.isPreview) like %?1%")
    public List<ImageEntity> findAllSearch(String keyword, Pageable pageable);

    @Query("select f from ImageEntity f where concat(f.productEntity.name, f.isPreview) like %?1%")
    public List<ImageEntity> findAllSearch(String keyword);

    @Query("Select Count(f) from ImageEntity f where concat(f.productEntity.name, f.isPreview) like %?1%")
    public int countSearch(String keyword);

    @Query("Select f from ImageEntity f where f.productEntity.id = ?1")
    public List<ImageEntity> findByProductId(Long id);

}
