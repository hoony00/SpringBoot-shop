package inhatc.spring.shop.Service;

import inhatc.spring.shop.Entity.Member;
import inhatc.spring.shop.dto.MemberFormDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberService memberService;

    public Member createMember() {
        MemberFormDto dto = MemberFormDto.builder()
                .address("인천시 미추홀구")
                .email("test@test.com")
                .name("홍길동")
                .password("1111")
                .build();

     return Member.createMember(dto, passwordEncoder);


    }

    @Test
    void saveMemberTest() {
        Member member1 = createMember();
        Member member2 = createMember();
        memberService.saveMember(member1);
        System.out.println(member1);

        Throwable e = assertThrows(IllegalStateException.class, () -> {
            memberService.saveMember(member2);
        });
        Assertions.assertEquals("이미 존재하는 회원입니다", e.getMessage());


    }
}