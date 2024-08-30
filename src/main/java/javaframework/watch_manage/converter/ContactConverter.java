package javaframework.watch_manage.converter;

import javaframework.watch_manage.dto.ContactDTO;
import javaframework.watch_manage.entities.ContactEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class ContactConverter {
    public ContactDTO toDto(ContactEntity entity) {
        ContactDTO dto = new ContactDTO();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setFullName(entity.getFullname());
        dto.setPhone(entity.getPhone());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setModifiedBy(entity.getModifiedBy());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public ContactEntity toEntity(ContactDTO dto) {
        ContactEntity entity = new ContactEntity();
        return getContactEntity(entity,dto);
    }

    public ContactEntity toEntity(ContactEntity entity, ContactDTO dto) {
        return getContactEntity(entity,dto);
    }

    @NotNull
    private ContactEntity getContactEntity(ContactEntity entity, ContactDTO dto) {
        entity.setEmail(dto.getEmail());
        entity.setFullname(dto.getFullName());
        entity.setPhone(dto.getPhone());
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setModifiedDate(dto.getModifiedDate());
        entity.setModifiedBy(dto.getModifiedBy());
        entity.setStatus(true);
        return entity;
    }
}
