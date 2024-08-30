package javaframework.watch_manage.dto;

import lombok.Data;

@Data
public class OrderDetailDTO extends AbstractDTO<OrderDetailDTO>{
    private final ProductDTO productDTO;
    private Long price;
    private Integer quantity;
    private Long subTotal;
    private Long order_id;

    public OrderDetailDTO(ProductDTO productDTO){
        this.productDTO = productDTO;
        this.quantity = 1;
        this.subTotal = productDTO.getPrice();
        if( productDTO.getPricePromotion() != null ){
            this.price = productDTO.getPricePromotion();
        }
        else this.price = productDTO.getPrice();
    }

    public Long getSubTotal() {
        subTotal = productDTO.getPrice()*quantity;
        return subTotal;
    }
}
