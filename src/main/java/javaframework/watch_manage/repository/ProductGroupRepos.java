package javaframework.watch_manage.repository;

import javaframework.watch_manage.entities.ProductGroupEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductGroupRepos extends PagingAndSortingRepository<ProductGroupEntity, Long> {
    @Query("select c from ProductGroupEntity c where concat(c.id, c.code, c.name) like %?1%")
    public List<ProductGroupEntity> findAllSearch(String keyword, Pageable pageable);

    @Query("select c from ProductGroupEntity c where concat(c.id, c.code, c.name) like %?1%")
    public List<ProductGroupEntity> findAllSearch(String keyword);
}
