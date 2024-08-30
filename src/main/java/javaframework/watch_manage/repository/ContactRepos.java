package javaframework.watch_manage.repository;

import javaframework.watch_manage.entities.ContactEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepos extends PagingAndSortingRepository<ContactEntity, Long> {
    @Query("select c from ContactEntity c where concat(c.id, c.email, c.fullname, c.phone, c.title, c.content) like %?1%")
    public List<ContactEntity> findAllSearch(String keyword, Pageable pageable);

    @Query("select c from ContactEntity c where concat(c.id, c.email, c.fullname, c.phone, c.title, c.content) like %?1%")
    public List<ContactEntity> findAllSearch(String keyword);
}
