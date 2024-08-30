package javaframework.watch_manage.repository;

import javaframework.watch_manage.entities.OrderDetailEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepos extends PagingAndSortingRepository<OrderDetailEntity, Long> {
}
