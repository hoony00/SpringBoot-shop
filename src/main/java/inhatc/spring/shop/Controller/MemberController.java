package inhatc.spring.shop.Controller;

import inhatc.spring.shop.Service.MemberService;
import inhatc.spring.shop.dto.MemberFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    private final MemberService memberService;

    @Autowired
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/member/new")
    public String memberForm(Model model) {

        model.addAttribute("memberFormDto", new MemberFormDto());



        return"member/memberForm";
}

/*    @PostMapping("/member/new")
    public String insertMember(MemberformDto memberformDto, Model model) {

        Member member = Member.createMember(memberformDto, passwordEncoder);
        Member m = memberService.saveMember(member);

        model.addAttribute("member", m);

        return "";
    }*/

}
