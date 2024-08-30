package javaframework.watch_manage.controller.admin;

import javaframework.watch_manage.constant.SystemConstant;
import javaframework.watch_manage.dto.ProductGroupDTO;
import javaframework.watch_manage.service.impl.ProductGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin/api")
public class ProductGroupController implements IAdminController<ProductGroupDTO> {
    @Autowired
    private ProductGroupService productgroupService;

    @GetMapping("/group")
    @Override
    public String showList(Model model, HttpSession session,
                           @RequestParam(defaultValue = "1") int page,
                           @RequestParam(defaultValue = "5")int limit,
                           @RequestParam(defaultValue = "")String search) {
        ProductGroupDTO productGroupDTO = productgroupService.getProductGroupPagination(page, limit, search);
        model.addAttribute("productGroupDTO", productGroupDTO);
        session.setAttribute("active","group");
        session.setAttribute(SystemConstant.SESSION_AUTHORITY, SecurityContextHolder.getContext().getAuthentication());
        return "views/admin/group/productgroup_management";
    }

    @PostMapping(value = "/group")
    @Override
    public String doAdd(Model model, @ModelAttribute ProductGroupDTO productGroupDTO) {
        model.addAttribute("productGroupDTO", productgroupService.save(productGroupDTO));
        return "views/admin/group/editProductGroup";
    }

    @PutMapping(value = "/group")
    @Override
    public String doEdit(Model model, @ModelAttribute ProductGroupDTO productGroupDTO) {
        model.addAttribute("productGroupDTO", productgroupService.save(productGroupDTO));
        return "views/admin/group/editProductGroup";
    }

    @DeleteMapping(value="/group")
    @ResponseBody
    @Override
    public List<ProductGroupDTO> doDelete(@RequestBody ProductGroupDTO productGroupDTO) {
        return productgroupService.delete(productGroupDTO.getIds());
    }

    @GetMapping(value={"/group/create","/group/{id}"})
    @Override
    public String showEditOrAddPage(Model model, @PathVariable(name = "id", required = false) Long id) {
        ProductGroupDTO productGroupDTO = new ProductGroupDTO();
        if( id != null ) {
            productGroupDTO = productgroupService.findOne(id);
        }
        model.addAttribute("productGroupDTO",productGroupDTO);
        return "views/admin/group/editProductGroup";
    }
}
