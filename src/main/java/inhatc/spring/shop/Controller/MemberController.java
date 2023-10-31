package inhatc.spring.shop.Controller;

import inhatc.spring.shop.Entity.Member;
import inhatc.spring.shop.CService.MemberService;
import inhatc.spring.shop.dto.MemberFormDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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

    @GetMapping("/member/login")
    public String loginForm() {
        log.info("member/login");
        return "member/memberloginform";
    }

    @GetMapping("/member/logout")
    public String performLogout(HttpServletRequest request, HttpServletResponse response) {
        // .. perform logout
        log.info("===============> logout");
        // 로그인 정보를 가져온다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 로그인 정보가 있으면 로그아웃 처리
        if (authentication != null) {
            // requst는 요청정보, response는 응답정보, authentication은 로그인 정보
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "/member/memberLoginForm";
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



}
