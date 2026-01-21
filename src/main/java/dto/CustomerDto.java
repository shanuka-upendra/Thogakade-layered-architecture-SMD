package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerDto {
    private String id;
    private String title;
    private String name;
    private Date DOB;
    private Double salary;
    private String address;
    private String city;
    private String province;
    private String postalCode;
}

