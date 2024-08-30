package javaframework.watch_manage.controller.admin;

import javaframework.watch_manage.dto.CategoryDTO;
import javaframework.watch_manage.service.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javaframework.watch_manage.constant.SystemConstant;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/api")
public class CategoryController implements IAdminController<CategoryDTO> {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    @Override
    public String showList(Model model, HttpSession session,
                           @RequestParam(defaultValue = "1") int page,
                           @RequestParam(defaultValue = "5")int limit,
                           @RequestParam(defaultValue = "")String search) {
        CategoryDTO categoryDTO = categoryService.getCategoryPagination(page, limit, search);
        model.addAttribute("categoryDTO", categoryDTO);
        session.setAttribute("active","category");
        session.setAttribute(SystemConstant.SESSION_AUTHORITY, SecurityContextHolder.getContext().getAuthentication());
        return "views/admin/category/category_management";
    }

    @PostMapping(value = "/category")
    @Override
    public String doAdd(Model model, @ModelAttribute CategoryDTO categoryDTO) {
        model.addAttribute("categoryDTO", categoryService.save(categoryDTO));
        return "views/admin/category/editCategory";
    }

    @PutMapping(value = "/category")
    @Override
    public String doEdit(Model model, @ModelAttribute CategoryDTO categoryDTO) {
        model.addAttribute("categoryDTO", categoryService.save(categoryDTO));
        return "views/admin/category/editCategory";
    }

    @DeleteMapping(value="/category")
    @ResponseBody
    @Override
    public List<CategoryDTO> doDelete(@RequestBody CategoryDTO categoryDTO) {
        categoryService.delete(categoryDTO.getIds());
        return new ArrayList<>();
    }

    @GetMapping(value={"/category/create","/category/{id}"})
    @Override
    public String showEditOrAddPage(Model model, @PathVariable(name = "id", required = false) Long id) {
        CategoryDTO categoryDTO = new CategoryDTO();
        if( id != null ) {
            categoryDTO = categoryService.findOne(id);
        }
        model.addAttribute("categoryDTO",categoryDTO);
        return "views/admin/category/editCategory";
    }
}
