package javaframework.watch_manage.controller.client;

import javaframework.watch_manage.dto.ProductDTO;
import javaframework.watch_manage.dto.OrderDTO;
import javaframework.watch_manage.service.impl.ProductService;
import javaframework.watch_manage.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/add")
    @ResponseBody
    public OrderDTO addToCart(HttpSession session, @RequestParam("id") Long id,
                                    @RequestParam(value = "quantity", required = false, defaultValue = "1") Integer quantity){
        ProductDTO productDTO = productService.findOne(id);
        // Create OrderDTO and add into session
        OrderDTO orderDTO = orderService.getOrderDto(session);
        // Order add OrderDetail with ProductDTO and quantity
        orderDTO.addOrderDetailDto(productDTO, quantity);
        orderService.setOrderDto(session,orderDTO);
        return orderDTO;
    }

    @RequestMapping(value = "/remove")
    @ResponseBody
    public OrderDTO removeOrderDetailInOrder(HttpSession session, @RequestParam("id") Long id){
        ProductDTO productDTO = productService.findOne(id);
        // Create OrderDTO and add into session
        OrderDTO orderDTO = orderService.getOrderDto(session);
        // Order remove OrderDetail with ProductDTO
        orderDTO.removeOrderDetail(productDTO);
        orderService.setOrderDto(session,orderDTO);
        return orderDTO;
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public OrderDTO updateQuantityOrder(HttpSession session, @RequestParam("id") Long id,
                             @RequestParam(value = "quantity", required = false, defaultValue = "1") Integer quantity){
        ProductDTO productDTO = productService.findOne(id);
        // Create OrderDTO and add into session
        OrderDTO orderDTO = orderService.getOrderDto(session);
        // Update OrderDetail in Order with ProductDTO and quantity update
        orderDTO.updateQuantityInOrderDetail(productDTO,quantity);
        return orderDTO;
    }
}
