package javaframework.watch_manage.repository;

import javaframework.watch_manage.entities.OrderEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepos extends PagingAndSortingRepository<OrderEntity, Long> {
}
