package hello.boot.jpa.repository;

import hello.boot.jpa.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Transactional
class MemberRepositoryTest {


    @Autowired
    private MemberRepository memberRepository;
    @Test
    void memberTest() {

        Member member= new Member();
        member.setUsername("ss");
        memberRepository.save(member);
        Member findMember = memberRepository.find(member.getId());

        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
    }


}