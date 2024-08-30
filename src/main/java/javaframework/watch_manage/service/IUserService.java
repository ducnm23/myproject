package javaframework.watch_manage.service;


import javaframework.watch_manage.dto.UserDTO;

public interface IUserService {
    UserDTO findOne(Long id);
    UserDTO findByUserName(String username);
    UserDTO save(UserDTO userDTO);
    void delete(Long[] ids);
    UserDTO getUserPagination(int page, int limit, String keyword);
    int totalItem();
    int countSearch(String keySearch);
    UserDTO findByUsernameAndStatus(String username, boolean status);
}
