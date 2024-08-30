package javaframework.watch_manage.repository;

import javaframework.watch_manage.entities.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepos extends PagingAndSortingRepository<UserEntity, Long> {
    @Query("select u from UserEntity u where concat(u.username, u.password, u.fullName, u.address, u.phone) like %?1%")
    public List<UserEntity> findAllSearch(String keyword, Pageable pageable);

    @Query("select u from UserEntity u where concat(u.username, u.password, u.fullName, u.address, u.phone) like %?1%")
    public List<UserEntity> findAllSearch(String keyword);

    @Query("Select Count(u) from UserEntity u where concat(u.username, u.password, u.fullName, u.address, u.phone) like %?1%")
    public int countSearch(String keyword);

    @Query("Select u from UserEntity u where u.username = ?1")
    public UserEntity findByUsername(String username);

    @Query("Select u from UserEntity u where u.username = ?1 and u.status = ?2")
    public UserEntity findByUsernameAndStatus(String userName, boolean status);

}
