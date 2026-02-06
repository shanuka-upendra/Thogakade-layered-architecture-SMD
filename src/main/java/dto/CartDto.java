package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartDto {
    private String itemCode;
    private String description;
    private Integer qty;
    private Double unitPrice;
    private Integer discount;
    private Double total;
}
