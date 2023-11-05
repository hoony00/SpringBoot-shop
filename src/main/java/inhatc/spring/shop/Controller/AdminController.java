package inhatc.spring.shop.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class AdminController {

    @GetMapping("/admin/test")
    public String adminTest() {
        log.info("adminTest");
        return "thymeleaf/admim";
    }

}