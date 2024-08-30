package javaframework.watch_manage.service;


import javaframework.watch_manage.dto.ProductDTO;

import java.util.List;

public interface IProductService {
    List<ProductDTO> findByCategoryCode(String categoryCode);
    List<ProductDTO> findByProductGroupId(Long productGroupId);
    List<ProductDTO> findAllHavePricePromotion();
    List<ProductDTO> findAll();
    ProductDTO findOne(Long id);
    ProductDTO save(ProductDTO productDTO);
    List<ProductDTO> delete(Long[] id);
    ProductDTO getProductPagination(int page, int limit, String keyword);
    int totalItem();
    int countSearch(String keySearch);
    List<Long> getListCategoryIdUnduplicated();
    List<Long> getProductGroupIdUnduplicated();

}
