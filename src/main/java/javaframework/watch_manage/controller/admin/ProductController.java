package javaframework.watch_manage.controller.admin;

import javaframework.watch_manage.constant.SystemConstant;
import javaframework.watch_manage.dto.ProductDTO;
import javaframework.watch_manage.service.impl.CategoryService;
import javaframework.watch_manage.service.impl.ProductGroupService;
import javaframework.watch_manage.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin/api")
public class ProductController implements IAdminController<ProductDTO> {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductGroupService productGroupService;

    @GetMapping("/product")
    @Override
    public String showList(Model model, HttpSession session,
                           @RequestParam(defaultValue = "1") int page,
                           @RequestParam(defaultValue = "5") int limit,
                           @RequestParam(defaultValue = "") String search) {
        ProductDTO productDTO = productService.getProductPagination(page, limit, search);
        model.addAttribute("productDTO", productDTO);
        session.setAttribute("active","product");
        session.setAttribute(SystemConstant.SESSION_AUTHORITY, SecurityContextHolder.getContext().getAuthentication());
        return "views/admin/product/product_management";
    }

    @PostMapping(value = "/product")
    @Override
    public String doAdd(Model model, @ModelAttribute ProductDTO productDTO) {
        model.addAttribute("productDTO", productService.save(productDTO));
        model.addAttribute("categoryList",categoryService.findAll());
        model.addAttribute("productGroupList", productGroupService.findAll());
        return "views/admin/product/editProduct";
    }

    @PutMapping(value = "/product")
    @Override
    public String doEdit(Model model, @ModelAttribute ProductDTO productDTO) {
        model.addAttribute("productDTO", productService.save(productDTO));
        model.addAttribute("categoryList",categoryService.findAll());
        model.addAttribute("productGroupList", productGroupService.findAll());
        return "views/admin/product/editProduct";
    }

    @DeleteMapping(value="/product")
    @ResponseBody
    @Override
    public List<ProductDTO> doDelete(@RequestBody ProductDTO productDTO) {
        return productService.delete(productDTO.getIds());
    }

    @GetMapping(value={"/product/create","/product/{id}"})
    @Override
    public String showEditOrAddPage(Model model, @PathVariable(name = "id", required = false) Long id) {
        ProductDTO productDTO = new ProductDTO();
        if( id != null ) {
            productDTO = productService.findOne(id);
        }
        model.addAttribute("productDTO",productDTO);
        model.addAttribute("categoryList",categoryService.findAll());
        model.addAttribute("productGroupList", productGroupService.findAll());
        return "views/admin/product/editProduct";
    }

}
