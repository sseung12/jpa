package hello.boot.jpa.repository.order.simplequery;

import hello.boot.jpa.domain.Address;
import hello.boot.jpa.domain.Order;
import hello.boot.jpa.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderSimpleQueryDto {

    public OrderSimpleQueryDto(Long orderId,String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
    }

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

}
