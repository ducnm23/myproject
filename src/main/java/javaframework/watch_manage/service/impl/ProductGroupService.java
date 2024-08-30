package javaframework.watch_manage.service.impl;

import javaframework.watch_manage.converter.ProductGroupConverter;
import javaframework.watch_manage.dto.CategoryDTO;
import javaframework.watch_manage.dto.ProductGroupDTO;
import javaframework.watch_manage.entities.ProductGroupEntity;
import javaframework.watch_manage.repository.ProductGroupRepos;
import javaframework.watch_manage.service.IProductGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductGroupService implements IProductGroupService {
    @Autowired
    private ProductGroupRepos productGroupRepos;

    @Autowired
    private ProductGroupConverter productGroupConverter;

    @Autowired
    private ProductService productService;

    @Override
    public List<ProductGroupDTO> findAll() {
        List<ProductGroupDTO> productGroupDTOS = new ArrayList<>();
        List<ProductGroupEntity> categoryEntities = (List<ProductGroupEntity>) productGroupRepos.findAll();
        categoryEntities.forEach(categoryEntity -> {
            productGroupDTOS.add(productGroupConverter.toDto(categoryEntity));
        });
        return productGroupDTOS;
    }

    @Override
    public ProductGroupDTO getProductGroupPagination(int page, int limit, String keyword) {
        ProductGroupDTO productGroupDTO = new ProductGroupDTO();
        List<ProductGroupEntity> productGroupEntities = new ArrayList<>();
        List<ProductGroupDTO> productGroupDTOS = new ArrayList<>();
        if (limit == 1) {
            if( !keyword.equals("") ){
                productGroupEntities = (List<ProductGroupEntity>) productGroupRepos.findAllSearch(keyword);
            }else{
                productGroupEntities = (List<ProductGroupEntity>) productGroupRepos.findAll();
            }
        } else {
            if( !keyword.equals("") ){
                productGroupEntities = productGroupRepos.findAllSearch(keyword, PageRequest.of(page-1, limit));
            }else {
                productGroupEntities = productGroupRepos.findAll(PageRequest.of(page - 1, limit)).getContent();
            }
            productGroupDTO.setTotalPage((int) Math.ceil((double) totalItem() / limit));
        }
        productGroupEntities.forEach(result -> {
            productGroupDTOS.add(productGroupConverter.toDto(result));
        });
        productGroupDTO.setResultList(productGroupDTOS);
        productGroupDTO.setPage(page);
        productGroupDTO.setLimit(limit);
        productGroupDTO.setSearch(keyword);
        return productGroupDTO;
    }

    @Override
    public int totalItem() {
        return (int) productGroupRepos.count();
    }

    @Override
    public ProductGroupDTO findOne(Long id) {
        return productGroupConverter.toDto(productGroupRepos.findById(id).get());
    }

    @Override
    public ProductGroupDTO save(ProductGroupDTO productGroupDTO) {
        ProductGroupEntity productGroupEntity = new ProductGroupEntity();
        ProductGroupDTO productGroupResult = new ProductGroupDTO();
        boolean checkInsert = false;
        if (productGroupDTO.getId() != null) {
            ProductGroupEntity entity_old = productGroupRepos.findById(productGroupDTO.getId()).get();
            productGroupEntity = productGroupConverter.toEntity( entity_old, productGroupDTO);
        } else {
            productGroupEntity = productGroupConverter.toEntity(productGroupDTO);
            checkInsert = true;
        }
        productGroupResult = productGroupConverter.toDto((ProductGroupEntity) productGroupRepos.save(productGroupEntity));
        if( productGroupResult != null ){
            productGroupResult.setAlert("success");
            if( checkInsert ) {
                productGroupResult.setMessage("Insert success");
            }else{
                productGroupResult.setMessage("Update success");
            }
        }else{
            productGroupResult.setAlert("danger");
            productGroupResult.setMessage("No success");
        }
        return productGroupResult;
    }

    @Override
    public List<ProductGroupDTO> delete(Long[] id) {
//        List<Long> listProductGroupId = productService.getProductGroupIdUnduplicated(); //Lấy tất cả CategoryId của product
//        List<ProductGroupDTO> productGroupDTOS = new ArrayList<>();
//        for (Long item : id) {
//            if( listProductGroupId.contains(item) ) {
//                productGroupDTOS.add(productGroupConverter.toDto(productGroupRepos.findById(item).get()));
//                productGroupRepos.deleteById(item);
//            }
            List<ProductGroupDTO> productGroupDTOS = new ArrayList<>();
            for (Long item : id) {
                productGroupRepos.deleteById(item);
        }
        return productGroupDTOS;
    }
}
