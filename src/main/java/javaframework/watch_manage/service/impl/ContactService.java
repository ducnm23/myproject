package javaframework.watch_manage.service.impl;

import javaframework.watch_manage.converter.ContactConverter;
import javaframework.watch_manage.dto.ContactDTO;
import javaframework.watch_manage.entities.ContactEntity;
import javaframework.watch_manage.repository.ContactRepos;
import javaframework.watch_manage.service.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService implements IContactService {

    @Autowired
    private ContactRepos contactRepos;

    @Autowired
    private ContactConverter contactConverter;

    @Override
    public List<ContactDTO> findAll() {
        List<ContactDTO> contactDTOS = new ArrayList<>();
        List<ContactEntity> contactEntities = (List<ContactEntity>) contactRepos.findAll();
        contactEntities.forEach(categoryEntity -> {
            contactDTOS.add(contactConverter.toDto(categoryEntity));
        });
        return contactDTOS;
    }

    @Override
    public ContactDTO save(ContactDTO contactDTO) {
        ContactEntity contactEntity = new ContactEntity();
        ContactDTO contact = new ContactDTO();
        boolean checkInsert = false;
        try {
            if (contactDTO.getId() != null) {
                ContactEntity entity_old = contactRepos.findById(contactDTO.getId()).get();
                contactEntity = contactConverter.toEntity( entity_old, contactDTO);
            } else {
                contactEntity = contactConverter.toEntity(contactDTO);
                checkInsert = true;
            }
            contact = contactConverter.toDto(contactRepos.save(contactEntity));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if( contact .getId() != null ){
                contact.setAlert("success");
                if( checkInsert ) {
                    contact.setMessage("Insert success");
                }else{
                    contact.setMessage("Update success");
                }
            }else{
                contact.setAlert("danger");
                contact.setMessage("No success");
            }
        }
        return contact;
    }

    @Override
    public List<ContactDTO> delete(Long[] id) {
        return null;
    }

    @Override
    public ContactDTO getContactPagination(int page, int limit, String keyword) {
        ContactDTO contactDTO = new ContactDTO();
        List<ContactEntity> contactEntities = new ArrayList<>();
        List<ContactDTO> contactDTOS = new ArrayList<>();
        if (limit == 1) {
            if( !keyword.equals("") ){
                contactEntities = (List<ContactEntity>) contactRepos.findAllSearch(keyword);
            }else{
                contactEntities = (List<ContactEntity>) contactRepos.findAll();
            }

        } else {
            if( !keyword.equals("") ){
                contactEntities = contactRepos.findAllSearch(keyword, PageRequest.of(page-1, limit));
            }else {
                contactEntities = contactRepos.findAll(PageRequest.of(page - 1, limit)).getContent();
            }
            contactDTO.setTotalPage((int) Math.ceil((double) totalItem() / limit));
        }
        contactEntities.forEach(result -> {
            contactDTOS.add(contactConverter.toDto(result));
        });
        contactDTO.setResultList(contactDTOS);
        contactDTO.setPage(page);
        contactDTO.setLimit(limit);
        contactDTO.setSearch(keyword);
        return contactDTO;
    }

    @Override
    public int totalItem() {
        return (int) contactRepos.count();
    }

    @Override
    public ContactDTO findOne(Long id) {
        return contactConverter.toDto(contactRepos.findById(id).get());
    }
}
