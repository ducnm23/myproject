package javaframework.watch_manage.service.impl;

import javaframework.watch_manage.entities.RoleEntity;
import javaframework.watch_manage.entities.UserEntity;
import javaframework.watch_manage.repository.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepos userRepos;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepos.findByUsername(username);
        if( userEntity.getUsername() == null){
            throw new UsernameNotFoundException("Username not found");
        }
        // object SimpleGrantedAuthority tương ứng với 1 thùng chứa 1 quyền của user
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<RoleEntity> roleEntities = userEntity.getRoleEntities();
        if( roleEntities.size() > 0 ) {
            for (RoleEntity role : roleEntities) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getCode()));
            }
        }
        //grantedAuthorities is object to authorization
        //Vì chỗ này truyền vào là code nên bên phần configSecurity chỗ hasAnyCode sẽ để code của role
        return new org.springframework.security.core
                .userdetails.User(userEntity.getUsername(), userEntity.getPassword(), grantedAuthorities);
    }
}
