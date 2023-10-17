package inhatc.spring.shop.Service;

import inhatc.spring.shop.Entity.Member;
import inhatc.spring.shop.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {



    private final MemberRepository memberRepository;

    public Member saveMember(Member member) {

        vaildateDuplicateMember(member);

         return memberRepository.save(member);
    }

    private void vaildateDuplicateMember(Member member) {
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());

        if(findMember.isPresent()) {
            System.out.println("이미 존재하는 회원입니다 =>" + member.getName());
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }


    }


}
