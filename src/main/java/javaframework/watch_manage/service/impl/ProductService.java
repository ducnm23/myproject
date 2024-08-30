package javaframework.watch_manage.service.impl;

import javaframework.watch_manage.converter.ProductConverter;
import javaframework.watch_manage.dto.ProductDTO;
import javaframework.watch_manage.entities.ProductEntity;
import javaframework.watch_manage.entities.ImageEntity;
import javaframework.watch_manage.repository.ProductRepos;
import javaframework.watch_manage.repository.ImageRepos;
import javaframework.watch_manage.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepos productRepos;

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private ImageRepos imageRepos;

    @Override
    public ProductDTO findOne(Long id) {
        return productConverter.toDto(productRepos.findById(id).get());
    }

    @Override
    public List<ProductDTO> findByCategoryCode(String categoryCode) {
        List<ProductDTO> productDTOS = new ArrayList<>();
        List<ProductEntity> productEntities = productRepos.findByCategoryCode(categoryCode);
        productEntities.forEach(f->{
            productDTOS.add(productConverter.toDto(f));
        });
        return productDTOS;
    }

    @Override
    public List<ProductDTO> findByProductGroupId(Long productGroupId) {
        List<ProductDTO> productDTOS = new ArrayList<>();
        List<ProductEntity> productEntities = productRepos.findByProductGroupId(productGroupId);
        productEntities.forEach(f->{
            productDTOS.add(productConverter.toDto(f));
        });
        return productDTOS;
    }

    @Override
    public List<ProductDTO> findAllHavePricePromotion() {
        List<ProductDTO> productDTOS = new ArrayList<>();
        List<ProductEntity> productEntities = productRepos.findAllHavePricePromotion();
        productEntities.forEach(f->{
            productDTOS.add(productConverter.toDto(f));
        });
        return productDTOS;
    }

    @Override
    public List<ProductDTO> findAll() {
        List<ProductDTO> productDTOS = new ArrayList<>();
        List<ProductEntity> productEntities = (List<ProductEntity>) productRepos.findAll();
        productEntities.forEach(f->{
            productDTOS.add(productConverter.toDto(f));
        });
        return productDTOS;
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        ProductEntity productEntity = new ProductEntity();
        ProductDTO result = new ProductDTO();
        boolean checkInsert = false;
        if (productDTO.getId() != null) {
            ProductEntity entity_old = productRepos.findById(productDTO.getId()).get();
            productEntity = productConverter.toEntity(entity_old, productDTO);
        } else {
            productEntity = productConverter.toEntity(productDTO);
            checkInsert = true;
        }
        try {
            result = productConverter.toDto(productRepos.save(productEntity));
        }catch ( Exception e){
            e.printStackTrace();
        }finally {
            if (result.getId() != null) {
                result.setAlert("success");
                if (checkInsert) {
                    result.setMessage("Insert success");
                } else {
                    result.setMessage("Update success");
                }
            } else {
                result.setAlert("danger");
                result.setMessage("No success");
            }
        }
        return result;
    }

//    @Override
//    public void delete(Long[] ids) {
//        for (Long item : ids) {
//            ProductEntity entity = productRepos.findById(item).get();
//            entity.setStatus(false);
//            entity = productRepos.save(entity);
//        }
//    }
    @Override
    public List<ProductDTO> delete(Long[] ids) {
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Long item : ids) {
            List<ImageEntity> imageEntities =  imageRepos.findByProductId(item);
            if( imageEntities.size() > 0 ){
                productDTOS.add(productConverter.toDto(productRepos.findById(item).get()));
            }else{
                productRepos.deleteById(item);
            }
        }
        return productDTOS;
    }

    @Override
    public ProductDTO getProductPagination(int page, int limit, String keyword) {
        ProductDTO productDTO = new ProductDTO();
        List<ProductEntity> productEntities = new ArrayList<>();
        int total = 0;
        if (limit == 1) {
            if( !keyword.equals("") ){
                productEntities = (List<ProductEntity>) productRepos.findAllSearch(keyword);
            }else {
                productEntities = (List<ProductEntity>) productRepos.findAll();
            }
        } else {
            if( !keyword.equals("")){
                productEntities = productRepos.findAllSearch(keyword, PageRequest.of(page-1, limit));
                total = (int) Math.ceil((double) countSearch(keyword) / limit);
            }else {
                productEntities = productRepos.findAll(PageRequest.of(page - 1, limit)).getContent();
                total = (int) Math.ceil((double) totalItem() / limit);
            }
        }
        productEntities.forEach(result -> {
            productDTO.getResultList().add(productConverter.toDto(result));
        });
        productDTO.setTotalPage(total);
        productDTO.setPage(page);
        productDTO.setLimit(limit);
        productDTO.setSearch(keyword);
        return productDTO;
    }

    @Override
    public int totalItem() {
        return (int) productRepos.count();
    }

    @Override
    public int countSearch(String keySearch) {
        return productRepos.countSearch(keySearch);
    }

    @Override
    public List<Long> getListCategoryIdUnduplicated() {
        List<Long> listCategoryId = productRepos.getListCategoryIdUnduplicated();
        return listCategoryId;
    }

    @Override
    public List<Long> getProductGroupIdUnduplicated() {
        List<Long> listProductGroupId = productRepos.getListProductGroupIdUnduplicated();
        return listProductGroupId;
    }
}
