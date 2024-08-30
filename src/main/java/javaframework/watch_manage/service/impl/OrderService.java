package javaframework.watch_manage.service.impl;

import javaframework.watch_manage.constant.SystemConstant;
import javaframework.watch_manage.dto.OrderDTO;
import javaframework.watch_manage.dto.OrderDetailDTO;
import javaframework.watch_manage.service.IOrderService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class OrderService implements IOrderService {

    @Override
    public OrderDTO getOrderDto(HttpSession session) {
        OrderDTO order = (OrderDTO) session.getAttribute(SystemConstant.SESSION_KEY_CART);
        if( order == null ){
            order = new OrderDTO();
            setOrderDto(session,order);
        }
        return order;
    }

    @Override
    public void setOrderDto(HttpSession session, OrderDTO orderDTO) {
        session.setAttribute(SystemConstant.SESSION_KEY_CART,orderDTO);
        Integer quantity = 0;
        for (OrderDetailDTO orderDetailDTO : orderDTO.getListOrderDetail()) {
            quantity += orderDetailDTO.getQuantity();
        }
        session.setAttribute(SystemConstant.SESSION_TOTAL_QUANTITY,quantity);
    }

    @Override
    public void removeOrderDTO(HttpSession session) {
        session.removeAttribute(SystemConstant.SESSION_KEY_CART);
    }


}
