package javaframework.watch_manage.service;

import javaframework.watch_manage.dto.OrderDTO;

import javax.servlet.http.HttpSession;

public interface IOrderService {
    OrderDTO getOrderDto(HttpSession session);
    void setOrderDto(HttpSession session, OrderDTO orderDTO);
    void removeOrderDTO(HttpSession session);
}
