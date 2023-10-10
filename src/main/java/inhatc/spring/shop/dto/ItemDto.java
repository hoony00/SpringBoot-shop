package inhatc.spring.shop.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDto {
    private Long id; // 상품 번호

    private String itemNm; // 상품 이름

    private int price; // 상품 가격

    private int stockNumber; // 상품 재고 수량

    private String itemDetail; // 상품 상세 설명

    private String itemSellStatus; // 상품 판매 상태

    private LocalDateTime regTime; // 상품 등록 시간

    private LocalDateTime updateTime; // 상품 수정 시간

}
