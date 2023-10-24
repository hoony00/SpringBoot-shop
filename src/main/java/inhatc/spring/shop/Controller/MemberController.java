package inhatc.spring.shop.Controller;

import inhatc.spring.shop.Entity.Member;
import inhatc.spring.shop.CService.MemberService;
import inhatc.spring.shop.dto.MemberFormDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
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

    @PostMapping("/member/new")
    public String insertMember(@Valid MemberFormDto memberformDto,
                               BindingResult bindingResult,
                               Model model) {
        if(bindingResult.hasErrors()) {
            return "member/memberForm";
        }

        try {
            // dto 암호화
            Member member = Member.createMember(memberformDto, passwordEncoder);
            // 암호화된 객체를 저장
            Member m = memberService.saveMember(member);
            // 저장된 객체를 뷰에 전달
            model.addAttribute("member", m);
            log.info("member = =========>>>", m);

        }catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }
        return "redirect:/";
    }

    @GetMapping("/member/login")
    public String login() {
        return "member/memberloginForm";
    }

}
