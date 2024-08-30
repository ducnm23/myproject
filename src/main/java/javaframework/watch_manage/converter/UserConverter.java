package javaframework.watch_manage.converter;

import javaframework.watch_manage.dto.UserDTO;
import javaframework.watch_manage.entities.UserEntity;
import javaframework.watch_manage.repository.RoleRepos;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    @Autowired
    private RoleRepos roleRepos;

    public UserDTO toDto(UserEntity entity) {
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setFullName(entity.getFullName());
        dto.setPhone(entity.getPhone());
        dto.setAddress(entity.getAddress());
        entity.getRoleEntities().stream().forEach(e->{
            dto.getRoleId().add(e.getId());
            dto.getRoleName().add(e.getName());
        });
        dto.setStatus(entity.getStatus());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setModifiedBy(entity.getModifiedBy());
        return dto;
    }

    public UserEntity toEntity(UserDTO dto) {
        UserEntity result = new UserEntity();
        return getUserEntity(result, dto);
    }

    public UserEntity toEntity(UserEntity entity, UserDTO dto) {
        return getUserEntity(entity, dto);
    }

    @NotNull
    private UserEntity getUserEntity(UserEntity entity, UserDTO dto) {
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setFullName(dto.getFullName());
        entity.setPhone(dto.getPhone());
        entity.setAddress(dto.getAddress());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setModifiedDate(dto.getModifiedDate());
        entity.setModifiedBy(dto.getModifiedBy());
        entity.setStatus(true);
        return entity;
    }
}
