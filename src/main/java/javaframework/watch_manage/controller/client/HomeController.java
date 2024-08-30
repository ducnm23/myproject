package javaframework.watch_manage.controller.client;

import javaframework.watch_manage.constant.SystemConstant;
import javaframework.watch_manage.dto.ContactDTO;
import javaframework.watch_manage.dto.UserDTO;
import javaframework.watch_manage.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ContactService contactService;

    @GetMapping(value = {"/index","/"})
    public String showHomePage(HttpSession session, Model model){
        session.setAttribute("categoryList",categoryService.findAll());
        session.setAttribute(SystemConstant.SESSION_ACTIVE,"index");
        session.setAttribute(SystemConstant.SESSION_KEY_CART,orderService.getOrderDto(session));
        session.setAttribute(SystemConstant.SESSION_ACTIVE_MENU_CLIENT, "");
        session.setAttribute(SystemConstant.SESSION_AUTHORITY,SecurityContextHolder.getContext().getAuthentication());
        model.addAttribute("categoryList",session.getAttribute("categoryList"));
        model.addAttribute("listProductByProductGroupId",productService.findByProductGroupId(3l));
        model.addAttribute("listProductHavePricePromotion",productService.findAllHavePricePromotion());
        return "views/client/index";
    }

    @GetMapping("/introduce")
    public String showIntroducePage(HttpSession session){
        session.setAttribute(SystemConstant.SESSION_ACTIVE,"introduce");
        return "views/client/introduce";
    }

    @GetMapping("/contact")
    public String showContactPage(HttpSession session,Model model){
        session.setAttribute(SystemConstant.SESSION_ACTIVE,"contact");
        model.addAttribute("contactDTO",new ContactDTO());
        return "views/client/contact";
    }

    @PostMapping(value = "/contact",consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ContactDTO addContact(@RequestBody ContactDTO contactDTO) {
        return contactService.save(contactDTO);
    }

    @GetMapping("/list/product")
    public String showListProductByCategory(HttpSession session,
                                         Model model, @RequestParam(name = "category_code",required = false) String category_code){
        model.addAttribute("categoryList",session.getAttribute("categoryList"));
        model.addAttribute("listProductByCategoryCode",productService.findByCategoryCode(category_code));
        session.setAttribute(SystemConstant.SESSION_ACTIVE_MENU_CLIENT, category_code);
        session.setAttribute(SystemConstant.SESSION_ACTIVE,"promotion");
        return "views/client/listproductbycategory";
    }



    @GetMapping("/shopping-cart")
    public String showListProductByCategory(HttpSession session){
        session.setAttribute(SystemConstant.SESSION_ACTIVE,"shop_cart");
        return "views/client/shopping_cart";
    }

    @GetMapping("/product/details/{id}")
    public String showProductDetails(HttpSession session, Model model,@PathVariable(name = "id") Long id){
        model.addAttribute("productDto",productService.findOne(id));
        model.addAttribute("categoryList",session.getAttribute("categoryList"));
        session.setAttribute(SystemConstant.SESSION_ACTIVE,"promotion");
        return "views/client/product_details";
    }

    @RequestMapping(value = "/quick_view_info/{id}") //@RequestBody là mapping với dữ liệu json vào trong dto
    public String showQuickView(Model model, @PathVariable(name = "id") Long id){
        model.addAttribute("productDto",productService.findOne(id));
        return "views/client/quick_view";
    }

    @GetMapping("/login")
    public String login(Model model,@RequestParam(name = "username",required = false, defaultValue = "") String username,
                        @RequestParam(name = "alert",required = false, defaultValue = "") String alert,
                        @RequestParam(name = "message",required = false, defaultValue = "") String message){
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setAlert(alert);
        userDTO.setMessage(message);
        model.addAttribute("userDTO",userDTO);
        return "views/login/login";
    }

    @GetMapping("/register")
    public String register(){
        return "views/login/register";
    }

    @PostMapping (value = "/register", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public UserDTO insertUser(@RequestBody UserDTO obj){
        UserDTO userDTO = new UserDTO();
        if( userService.findByUsernameAndStatus(obj.getUsername(),true ) == null ){
            obj.getRoleId().add(2l);
            userDTO = userService.save(obj);
        }
        return userDTO;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse resp){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null) {
            new SecurityContextLogoutHandler().logout(request,resp,auth);
        }
        return "redirect:/index";
    }

    @GetMapping("/pay")
    public String pay(HttpSession session) {
        session.setAttribute(SystemConstant.SESSION_AUTHORITY,SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/shopping-cart";
    }

    @PostMapping("/pay")
    @ResponseBody
    public String informPay() {
        return "Oke";
    }
}
