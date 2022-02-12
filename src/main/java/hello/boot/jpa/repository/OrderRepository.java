package hello.boot.jpa.repository;


import hello.boot.jpa.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;


    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
      return  em.find(Order.class, id);
    }



    public List<Order> findAllByString(OrderSearch orderSearch) {
        return em.createQuery("select o from Order o join o.member m"
                        +"where o.member =:status"+
                        "and m.name like :name",Order.class)
                .setParameter("status",orderSearch.getOrderStatus())
                .setParameter("name",orderSearch.getMemberName())
                .setMaxResults(1000)
        .getResultList();
    }

    //JPA Criteria
    public List<Order> findAllByCriteria(OrderSearch orderSearch) {

    }

}
