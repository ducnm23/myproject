package javaframework.watch_manage.repository;

import javaframework.watch_manage.entities.RoleEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepos extends PagingAndSortingRepository<RoleEntity, Long> {
}

