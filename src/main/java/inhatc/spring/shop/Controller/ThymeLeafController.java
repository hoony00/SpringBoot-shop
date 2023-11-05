package inhatc.spring.shop.Controller;

import inhatc.spring.shop.dto.ItemDto;
import inhatc.spring.shop.dto.ParamDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class ThymeLeafController {

    @GetMapping("/thymeleaf/ex1")
    public String ex1(Model model) {

        ItemDto itemDto = ItemDto.builder()
                .itemNm("최신 스프링")
                .price(20000)
                .itemDetail("스프링부트 3.1.4")
                .itemSellStatus("SELL")
                .build();

        model.addAttribute("itemDto", itemDto);

        return "thymeleaf/ex1";
    }

    // href 예제
    @GetMapping("/thymeleaf/ex2")
    public String ex2() {

        return "thymeleaf/ex2";
    }


    // param 예제 - http://localhost/thymeleaf/ex3?param1=홍길동&param2=인하공전
    @GetMapping("/thymeleaf/ex3")
    public String ex3(ParamDto paramDto, Model model) {
        log.info(">>>>>>>>>>>> ParamDto : " + paramDto);  // log로 찍으면 따로 파일로 관리 가능
        // System.out.println(">>>>>>>>>>>> ParamDto : " + paramDto);

        model.addAttribute("param1", paramDto.getParam1());
        model.addAttribute("param2", paramDto.getParam2());
        return "thymeleaf/ex3";
    }

    // temp
    @GetMapping("/thymeleaf/temp")
    public String temp() {

        return "thymeleaf/temp";
    }

}