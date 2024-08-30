package javaframework.watch_manage.service;


import javaframework.watch_manage.dto.CategoryDTO;

import java.util.List;

public interface ICategoryService {
    List<CategoryDTO> findAll();

    CategoryDTO save(CategoryDTO categoryDTO);

    List<CategoryDTO> delete(Long[] ids);

    CategoryDTO getCategoryPagination(int page, int limit, String search);

    int totalItem();

    CategoryDTO findOne(Long id);
}
