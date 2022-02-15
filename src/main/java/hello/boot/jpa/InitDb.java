package hello.boot.jpa;

import hello.boot.jpa.domain.*;
import hello.boot.jpa.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.doInit1();
        initService.doInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{

        @PersistenceContext
        EntityManager em;

        public void doInit1() {
            Member member = createMember("userA", "서울", "1", "1111");
            em.persist(member);


            Book book1 = new Book();
            book1.setName("jpa1");
            book1.setTitle("JPA BOOK");
            book1.setPrice(10000);
            book1.setStockQuantity(100);
            em.persist(book1);

            Book book2 = new Book();
            book2.setName("jpa2");
            book2.setTitle("JPA2 BOOK");
            book2.setPrice(20000);
            book2.setStockQuantity(100);
            em.persist(book2);

            OrderItem orderItem1= OrderItem.createOrderItem(book1,10000,1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2,20000,2);

            Delivery delivery = getDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);

            em.persist(order);
        }



        public void doInit2() {
            Member member = createMember("userB", "부산", "2", "2222");
            em.persist(member);

            Book book1 = createBook("spring BOOK1", 10000);
            em.persist(book1);

            Book book2 = createBook("spring BOOK2", 20000);
            em.persist(book2);

            OrderItem orderItem1= OrderItem.createOrderItem(book1,30000,3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2,40000,4);

            Delivery delivery = getDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);

            em.persist(order);
        }

        private Member createMember(String name, String city , String street, String zipcode) {
            Member member =new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }

        private Delivery getDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }

        private Book createBook(String JPA_BOOK, int price) {
            Book book1 = new Book();
            book1.setName("spring");
            book1.setTitle(JPA_BOOK);
            book1.setPrice(price);
            book1.setStockQuantity(100);
            return book1;
        }
    }
}

