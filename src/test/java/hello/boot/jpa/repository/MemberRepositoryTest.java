package hello.boot.jpa.repository;

import hello.boot.jpa.domain.Member;
import hello.boot.jpa.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Transactional
class MemberRepositoryTest {


    @Autowired
    private MemberService memberService;
    @Test
    @DisplayName("회원 가입 테스트")
    void memberTest() throws Exception {
        Member m1 = new Member();
        m1.setName("태연");

        Long id =memberService.join(m1);

        Member m2 = memberService.findMember(id);
        assertThat(m1).isEqualTo(m2);

        org.junit.jupiter.api.Assertions.assertEquals(m2,memberService.findMember(id));


    }



    @DisplayName("중복네임 검사")
    @Test()
    void DuplicateName() {



        assertThrows(IllegalStateException.class,()->{
            Member m1 = new Member();
            Member m2 = new Member();
            m1.setName("탱구");
            m2.setName("탱구");

            memberService.join(m1);
            memberService.join(m2);
        });

    }

}