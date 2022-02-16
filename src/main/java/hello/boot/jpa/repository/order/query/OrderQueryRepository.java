package hello.boot.jpa.repository.order.query;


import hello.boot.jpa.domain.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

    @PersistenceContext
    EntityManager em;




    public List<OrderQueryDto> findOrderQueryDtos() {
        List<OrderQueryDto> result = findOrders();
        result.forEach(o ->{
            List<OrderItemQueryDto> orderItems = findOrderItems(o.getOrderId());
            o.setOrderItems(orderItems);
        });
        return result;
    }

    private List<OrderItemQueryDto> findOrderItems(Long orderId) {
        return em.createQuery("" +
                "select new hello.boot.jpa.repository.order.query.OrderItemQueryDto(oi.order.id,i.name,i.price,oi.count)"+
                        " from OrderItem oi"+
                " join oi.item i"+
                " where oi.order.id = :orderId",OrderItemQueryDto.class)
                .setParameter("orderId",orderId)
                .getResultList();

    }

    public List<OrderQueryDto> findAllByDto_optimization() {
        List<OrderQueryDto> result = findOrders();

        List<Long> orderIds = result.stream().map(o -> o.getOrderId())
                .collect(Collectors.toList());

        List<OrderItemQueryDto> order= em.createQuery("" +
                                "select new hello.boot.jpa.repository.order.query.OrderItemQueryDto(oi.order.id,i.name,i.price,oi.count)"+
                                " from OrderItem oi"+
                                " join oi.item i"+
                                " where oi.order.id in :orderIds"
        ,OrderItemQueryDto.class)
                .setParameter("orderIds",orderIds)
                .getResultList();

        Map<Long, List<OrderItemQueryDto>> orderItemMap = order.stream()
                .collect(Collectors.groupingBy(orderItemQueryDto -> orderItemQueryDto.getOrderId()));

        result.forEach(o -> o.setOrderItems(orderItemMap.get(o.getOrderId())));

        return result;
    }

    public List<OrderQueryDto> findOrders() {
      return  em.createQuery("" +
                        "select new hello.boot.jpa.repository.order.query.OrderQueryDto(o.id,m.name,o.orderDate,o.status,d.address)"+
                        " from Order o" +
                        " join o.member m"+
                        " join o.delivery d",OrderQueryDto.class)
                .getResultList();
    }

}
