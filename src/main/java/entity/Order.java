package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@ToString
public class Order {
    private Long id;
    private int petId;
    private int quantity;
    private String shipDate;
    private String status;
    private boolean complete;
}
