package inhatc.spring.shop.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller()
public class AdminController {

    @GetMapping("/admin/test")
    public String adminTest() {
        return "thymeleaf/admim";
    }

}