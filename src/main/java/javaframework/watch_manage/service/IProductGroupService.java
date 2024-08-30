package javaframework.watch_manage.service;


import javaframework.watch_manage.dto.ProductGroupDTO;

import java.util.List;

public interface IProductGroupService {
    List<ProductGroupDTO> findAll();

    ProductGroupDTO save(ProductGroupDTO productGroupDTO);

    List<ProductGroupDTO> delete(Long[] id);

    ProductGroupDTO getProductGroupPagination(int page, int limit, String search);

    int totalItem();

    ProductGroupDTO findOne(Long id);
}
