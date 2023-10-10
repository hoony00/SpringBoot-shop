package inhatc.spring.shop.controller;

import inhatc.spring.shop.dto.ItemDto;
import inhatc.spring.shop.dto.ParamDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thymeleaf")
@Slf4j // 로그를 위한 어노테이션
public class ThymeleafController {

    @GetMapping("/ex1") // http://localhost/thymeleaf/ex1
    public String ex1(Model model) {

        ItemDto itemDto = ItemDto.builder()
                .itemNm("상품명 1")
                .itemSellStatus("SELL")
                .price(10000)
                .itemDetail("상품 상세 설명")
                .build();

        model.addAttribute("itemDto", itemDto);

        return "thymeleaf/ex1";
    }

    @GetMapping("/ex2") // http://localhost/ex2
    public String ex2() {

        return "thymeleaf/ex2";
    }

    @GetMapping("/ex3") // http://localhost/ex3
    public String ex3(ParamDto paramDto, Model model) {

        log.info("name : " + paramDto.getName());
        log.info("age : " + paramDto.getName());
        log.info("--------------------------");
        log.info("paramDto : " + paramDto); //toString 아노테이션으로 가능 -> 이거 공부하기

        model.addAttribute("dto", paramDto);

        return "thymeleaf/ex3";
    }


    @GetMapping("/ex4") // http://localhost/ex4
    public String ex4() {

        return "thymeleaf/ex4";
    }




}
