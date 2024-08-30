package javaframework.watch_manage.service;

import javaframework.watch_manage.dto.ContactDTO;

import java.util.List;

public interface IContactService {
    List<ContactDTO> findAll();

    ContactDTO save(ContactDTO contactDTO);

    List<ContactDTO> delete(Long[] ids);

    ContactDTO getContactPagination(int page, int limit, String search);

    int totalItem();

    ContactDTO findOne(Long id);
}
