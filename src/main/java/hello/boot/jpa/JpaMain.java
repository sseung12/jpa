package hello.boot.jpa;

import hello.boot.jpa.domain.Member;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@Component
public class JpaMain {


   public void insert(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            logic(em);
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        }
    }

    public static void logic(EntityManager entityManager) {
        String id ="id1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("승수");
        member.setAge(28);
        entityManager.persist(member);

    }
}
