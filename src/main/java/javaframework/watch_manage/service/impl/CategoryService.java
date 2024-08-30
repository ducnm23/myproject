package javaframework.watch_manage.service.impl;

import javaframework.watch_manage.converter.CategoryConverter;
import javaframework.watch_manage.dto.CategoryDTO;
import javaframework.watch_manage.entities.CategoryEntity;
import javaframework.watch_manage.repository.CategoryRepos;
import javaframework.watch_manage.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepos categoryRepos;

    @Autowired
    private CategoryConverter categoryConverter;

    @Autowired
    private ProductService productService;

    @Override
    public List<CategoryDTO> findAll() {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        List<CategoryEntity> categoryEntities = (List<CategoryEntity>) categoryRepos.findAll();
        categoryEntities.forEach(categoryEntity -> {
            categoryDTOS.add(categoryConverter.toDto(categoryEntity));
        });
        return categoryDTOS;
    }

    @Override
    public CategoryDTO getCategoryPagination(int page, int limit, String keyword) {
        CategoryDTO categoryDTO = new CategoryDTO();
        List<CategoryEntity> categoryEntities = new ArrayList<>();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        if (limit == 1) {
            if( !keyword.equals("") ){
                categoryEntities = (List<CategoryEntity>) categoryRepos.findAllSearch(keyword);
            }else{
                categoryEntities = (List<CategoryEntity>) categoryRepos.findAll();
            }

        } else {
            if( !keyword.equals("") ){
                categoryEntities = categoryRepos.findAllSearch(keyword, PageRequest.of(page-1, limit));
            }else {
                categoryEntities = categoryRepos.findAll(PageRequest.of(page - 1, limit)).getContent();
            }
            categoryDTO.setTotalPage((int) Math.ceil((double) totalItem() / limit));
        }
        categoryEntities.forEach(result -> {
            categoryDTOS.add(categoryConverter.toDto(result));
        });
        categoryDTO.setResultList(categoryDTOS);
        categoryDTO.setPage(page);
        categoryDTO.setLimit(limit);
        categoryDTO.setSearch(keyword);
        return categoryDTO;
    }

    @Override
    public int totalItem() {
        return (int) categoryRepos.count();
    }

    @Override
    public CategoryDTO findOne(Long id) {
        return categoryConverter.toDto(categoryRepos.findById(id).get());
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = new CategoryEntity();
        CategoryDTO category = new CategoryDTO();
        boolean checkInsert = false;
        if (categoryDTO.getId() != null) {
            CategoryEntity entity_old = categoryRepos.findById(categoryDTO.getId()).get();
            categoryEntity = categoryConverter.toEntity( entity_old, categoryDTO);
        } else {
            categoryEntity = categoryConverter.toEntity(categoryDTO);
            checkInsert = true;
        }
        category = categoryConverter.toDto((CategoryEntity) categoryRepos.save(categoryEntity));
        if( category != null ){
            category.setAlert("success");
            if( checkInsert ) {
                category.setMessage("Insert success");
            }else{
                category.setMessage("Update success");
            }
        }else{
            category.setAlert("danger");
            category.setMessage("No success");
        }
        return category;
    }

    @Override
    public List<CategoryDTO> delete(Long[] id) {
//        List<Long> listCategoryId = productService.getListCategoryIdUnduplicated(); //Lấy tất cả CategoryId của product
//        List<CategoryDTO> categoryDTOS = new ArrayList<>();
//        for (Long item : id) {
//            if( listCategoryId.contains(item) ) {
//                categoryDTOS.add(categoryConverter.toDto(categoryRepos.findById(item).get()));
//                categoryRepos.deleteById(item);
//            }
//        }
//        return categoryDTOS;
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (Long item : id) {
            categoryRepos.deleteById(item);

    }
        return categoryDTOS;
}}
