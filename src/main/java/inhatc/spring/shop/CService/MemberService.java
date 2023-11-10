package inhatc.spring.shop.CService;

import inhatc.spring.shop.Entity.Member;
import inhatc.spring.shop.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public Member saveMember(Member member) {
        vaildateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void vaildateDuplicateMember(Member member) {
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());

        if (findMember.isPresent()) {
            System.out.println("이미 존재하는 회원입니다 =>" + member.getName());
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }


    // 로그인 시도시 자동으로 이 메소드가 실행 됨
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("해당 사용자가 없습니다."));
        log.info("현재 로그인 시도 사용자 --->" + member);

        return User.builder()
                .username(member.getEmail())
                // 스프링 시큐리티에서 암호를 넣을 땐 꼭 암호화를 넣어야 함 -> 사용자가 입력한 비밀번호를 자동으로 암호화해서 비교함
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }


}
