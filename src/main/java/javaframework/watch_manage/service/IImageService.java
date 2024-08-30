package javaframework.watch_manage.service;


import javaframework.watch_manage.dto.ImageDTO;

import java.util.List;

public interface IImageService {
    List<ImageDTO> findAll();

    ImageDTO save(ImageDTO imageDTO);

    void delete(Long[] id);

    ImageDTO getImagePagination(int page, int limit, String keyword);

    int totalItem();

    int countSearch(String keySearch);

    ImageDTO findOne(Long id);

    List<ImageDTO> findByProductId(Long id);
}
