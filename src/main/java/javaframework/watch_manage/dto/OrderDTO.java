package javaframework.watch_manage.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDTO extends AbstractDTO<OrderDTO>{
    private String code;
    private Long total;
    private List<OrderDetailDTO> listOrderDetail = new ArrayList<>();
    private Integer orderStatus;
    private Long user_manager_id;
    private Long user_customer_id;
    private String user_manager_name;
    private String user_customer_name;

    public void setTotal(){
        Long sum = 0l;
        for (OrderDetailDTO item : listOrderDetail) {
            sum += item.getSubTotal();
        }
        this.total = sum;
    }

    public int getListOrderDetailCount(){
        return listOrderDetail.size();
    }

    public void addOrderDetailDto(OrderDetailDTO orderDetailDTO){
        addOrderDetailDto(orderDetailDTO.getProductDTO(), orderDetailDTO.getQuantity());
    }

    // add OrderDetail into Order
    public void addOrderDetailDto(ProductDTO productDTO, Integer quantity){
        OrderDetailDTO itemOrder = getOrderDetails(productDTO);    //Take 1 item orderDetail in cart
        if( itemOrder != null){             // if item had in cart then set quantity
            itemOrder.setQuantity(itemOrder.getQuantity() + quantity);
        }else{              // if item not exists in cart then create OrderDetailDTO object with productDTO and set quantity
            itemOrder = new OrderDetailDTO(productDTO);
            itemOrder.setQuantity(quantity);
            listOrderDetail.add(itemOrder);
        }
        setTotal();
    }

    // Kiểm tra sản phẩm để có trong cart chưa nếu có thì lấy ra
    public OrderDetailDTO getOrderDetails(ProductDTO productDTO){
        for (OrderDetailDTO itemOrder : listOrderDetail) {
            if( itemOrder.getProductDTO().getId() == productDTO.getId() ){
                return itemOrder;
            }
        }
        return null;
    }

    public void updateQuantityInOrderDetail(ProductDTO productDTO, Integer quantity){
        OrderDetailDTO itemOrder = getOrderDetails(productDTO);
        if( itemOrder != null) {
            itemOrder.setQuantity(quantity);
        }
        setTotal();
    }

    public void removeOrderDetail(ProductDTO productDTO){
        OrderDetailDTO itemOrder = getOrderDetails(productDTO);
        if( itemOrder != null) {
            listOrderDetail.remove(itemOrder);
        }
        setTotal();
    }

    public void clear(){
        listOrderDetail.clear();
        total = 0l;
    }

    public boolean isEmpty(){
        return listOrderDetail.isEmpty();
    }

}
