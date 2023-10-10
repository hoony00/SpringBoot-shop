package inhatc.spring.shop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j // 로그를 위한 어노테이션
public class testController {



    @GetMapping("/") // http://localhost/
    public String ex4() {

        return "index.html";
    }




}
