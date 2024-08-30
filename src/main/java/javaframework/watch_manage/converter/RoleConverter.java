package javaframework.watch_manage.converter;

import javaframework.watch_manage.dto.RoleDTO;
import javaframework.watch_manage.entities.RoleEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {

    public RoleDTO toDto(RoleEntity entity) {
        RoleDTO dto = new RoleDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setModifiedBy(entity.getModifiedBy());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public RoleEntity toEntity(RoleDTO dto) {
        RoleEntity entity = new RoleEntity();
        return getRoleEntity(entity,dto);
    }

    public RoleEntity toEntity(RoleEntity entity, RoleDTO dto) {
        return getRoleEntity(entity,dto);
    }

    @NotNull
    private RoleEntity getRoleEntity(RoleEntity entity, RoleDTO dto) {
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setStatus(true);
        return entity;
    }

}
