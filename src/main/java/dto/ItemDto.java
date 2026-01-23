package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemDto {
    private String itemCode;
    private String description;
    private String packSize;
    private Double price;
    private Integer qty;
}
