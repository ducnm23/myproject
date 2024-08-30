package javaframework.watch_manage.service;


import javaframework.watch_manage.dto.RoleDTO;

import java.util.List;

public interface IRoleService {
    RoleDTO findOne(Long id);
    List<RoleDTO> findAll();
}
