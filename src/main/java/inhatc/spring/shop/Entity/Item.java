package inhatc.spring.shop.Entity;

import inhatc.spring.shop.constant.ItemSellStatus;
import inhatc.spring.shop.dto.ItemFormDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;



@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;             // 상품 코드

    @Column(nullable = false, length = 50)
    private String itemNm;       // 상품명

    @Column(nullable = false)
    private int price;           // 상품 가격

    @Column(nullable = false, name="number")
    private int stockNumber;     // 상품 재고 수량

    @Lob // Large Object - CLOB, BLOB
    @Column(nullable = false)
    private String itemDetail;   // 상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;  // 상품 판매 상태

    public void updateItem(ItemFormDto itemFormDto) {
        this.itemNm = itemFormDto.getItemNm();
        this.price = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
    }

}
