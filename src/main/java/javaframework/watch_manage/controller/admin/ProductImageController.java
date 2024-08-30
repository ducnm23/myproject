package javaframework.watch_manage.controller.admin;

import javaframework.watch_manage.constant.SystemConstant;
import javaframework.watch_manage.dto.ImageDTO;
import javaframework.watch_manage.service.impl.ProductService;
import javaframework.watch_manage.service.impl.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/api")
public class ProductImageController implements IAdminController<ImageDTO> {

    @Autowired
    private ImageService imageService;

    @Autowired
    private ProductService productService;

    @Value("${UPLOAD_FOLDER}")      // value cho phép map dữ liệu từ file properties vào trong biến trong java
    String upload_folder;

    @GetMapping("/image")
    @Override
    public String showList(Model model, HttpSession session,
                           @RequestParam(defaultValue = "1") int page,
                           @RequestParam(defaultValue = "5") int limit,
                           @RequestParam(defaultValue = "") String search) {
        ImageDTO imageDTO = imageService.getImagePagination(page, limit, search);
        model.addAttribute("imageDTO", imageDTO);
        session.setAttribute("active","image");
        session.setAttribute(SystemConstant.SESSION_AUTHORITY, SecurityContextHolder.getContext().getAuthentication());
        return "views/admin/image/image_management";
    }

    @PostMapping("/image")
    public String insertImage(Model model, @ModelAttribute ImageDTO obj,
                              @RequestParam(name = "image") MultipartFile file) {
        Path path = Paths.get(upload_folder+file.getOriginalFilename());
        try{
            byte[] bytes = file.getBytes();
            Files.write(path,bytes);
        }catch (Exception e){
            e.printStackTrace();
        }
        if( !file.getOriginalFilename().equals("")) {
            obj.setPath(file.getOriginalFilename());
        }
        model.addAttribute("imageDTO", imageService.save(obj));
        model.addAttribute("productList",productService.findAll());
        return "views/admin/image/editImage";
    }

    @Override
    public String doAdd(Model model, @ModelAttribute ImageDTO obj) {
        return "";
    }

    @Override
    public String doEdit(Model model, @ModelAttribute ImageDTO obj) {
        return "";
    }

    @DeleteMapping("/image")
    @ResponseBody
    @Override
    public List<ImageDTO> doDelete(@RequestBody ImageDTO obj) {
        imageService.delete(obj.getIds());
        return new ArrayList<>();
    }

    @GetMapping(value={"/image/create","/image/{id}"})
    @Override
    public String showEditOrAddPage(Model model, @PathVariable(name = "id", required = false) Long id) {
        ImageDTO imageDTO = new ImageDTO();
        if( id != null ) {
            imageDTO = imageService.findOne(id);
        }
        model.addAttribute("imageDTO",imageDTO);
        model.addAttribute("productList",productService.findAll());
        return "views/admin/image/editImage";
    }
}
