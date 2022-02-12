package hello.boot.jpa.service;

import hello.boot.jpa.domain.*;
import hello.boot.jpa.domain.item.Book;
import hello.boot.jpa.exception.NoEnoughStockException;
import hello.boot.jpa.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Transactional
class OrderServiceTest {

    @PersistenceContext
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    private Book createBook() {
        Book book = new Book();
        book.setName("제너레이션");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);
        return book;
    }

    public Member createMember() {
        Member member = new Member();
        member.setName("탱구");
        member.setAddress(new Address("서울","한강","123-123"));
        em.persist(member);
        return member;
    }

    @Test
    public void 상품주문() throws Exception{

        //given
        Member member = createMember();
        Book book = createBook();



        int orderCount =10;
        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals( OrderStatus.ORDER,getOrder.getStatus());
        assertEquals(10 ,getOrder.getOrderItems().get(0).getCount());
    }

    @Test
    public void 주문취소() throws Exception{
        Member member = createMember();
        Book book = createBook();

        int orderCount =10;
        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        orderService.cancelOrder(orderId);

        assertEquals( OrderStatus.CANCEL,getOrder.getStatus());
    }

    @Test
    public void 상품주문_재고수량초과() throws Exception{

        Member member = createMember();
        Book book = createBook();

        assertThrows(NoEnoughStockException.class,()->{

                orderService.order(member.getId(), book.getId(), 11);}
                );

    }


}