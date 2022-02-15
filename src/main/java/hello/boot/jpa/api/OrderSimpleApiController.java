package hello.boot.jpa.api;

import hello.boot.jpa.domain.Address;
import hello.boot.jpa.domain.Order;
import hello.boot.jpa.domain.OrderStatus;
import hello.boot.jpa.repository.OrderRepository;
import hello.boot.jpa.repository.OrderSearch;
import hello.boot.jpa.repository.order.simplequery.OrderSimpleQueryDto;
import hello.boot.jpa.repository.order.simplequery.OrderSimpleQueryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// xToOne (ManyToOne, OneToOne)
// Order
// Order -> Member
// Order -> Delivery
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        return all;
    }


    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2() {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        return orders.stream().map(m -> (new SimpleOrderDto(m)))
                .collect(Collectors.toList());
    }


    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> orderV3() {
        List<Order> list = orderRepository.findAllWithMemberDelivery();
        return list.stream().map( m->(new SimpleOrderDto(m)))
                .collect(Collectors.toList());

    }

    @GetMapping("/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> ordersV4() {
        return orderSimpleQueryRepository.findOrderDtos();
    }


    @Data
    static class SimpleOrderDto {

        public SimpleOrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
        }

        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

    }

}
