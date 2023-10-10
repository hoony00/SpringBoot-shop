package inhatc.spring.shop.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import inhatc.spring.shop.constant.ItemSellStatus;
import inhatc.spring.shop.entity.Item;
import inhatc.spring.shop.entity.QItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static inhatc.spring.shop.entity.QItem.*; // QueryDSL - QItem 스태틱 임포트


@SpringBootTest
@Transactional
class testRepository {

    private final ItemRepository itemRepository;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public testRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @BeforeEach
    public void init() {
        for (int i = 1; i < 101; i++) {
            Item item = Item.builder()
                    .itemNm("테스트 상품" + i)
                    .price(10000 + i)
                    .stockNumber(100 + i)
                    .itemDetail("테스트 상품 상세 설명" + i)
                    .itemSellStatus(ItemSellStatus.SELL)
                    .build();

            itemRepository.save(item);

        }
    }

    @Test
    @DisplayName("조건 -> JPA")
    void findByStockNumberGreaterThanEqualAndItemNmContaining() {

        int stockNumber = 140;
        String itemNm = "3";

        itemRepository.findByStockNumberGreaterThanEqualAndItemNmContaining(stockNumber, itemNm)
                .forEach(System.out::println);
        System.out.println("================JPA===============");
        System.out.println("================JPA===============");
        System.out.println("================JPA===============");
        System.out.println("================JPA===============");


    }


    @Test
    @DisplayName("조건 -> JPQL로 위에 조건")
    void findByStockNumberAndItemNmQuery() {

        int stockNumber = 140;
        String itemNm = "3";

        itemRepository.findByStockNumberAndItemNmQuery(stockNumber, itemNm)
                .forEach(System.out::println);
        System.out.println("================JPQL===============");
        System.out.println("================JPQL===============");
        System.out.println("================JPQL===============");
        System.out.println("================JPQL===============");
        System.out.println("================JPQL===============");

    }


    @Test
    @DisplayName("조건 -> Native 로 위에 조건 ")
    void findByStockNumberAndItemNmQueryNative() {

            int stockNumber = 140;
            String itemNm = "3";

            itemRepository.findByStockNumberAndItemNmQueryNative(stockNumber, itemNm)
                    .forEach(System.out::println);
        System.out.println("================Native===============");
        System.out.println("================Native===============");
        System.out.println("================Native===============");
        System.out.println("================Native===============");
        System.out.println("================Native===============");

    }

    @Test
    @DisplayName("조건 -> querydsl로 위에 조건")
    void findByStockNumberAndItemNmQueryDSL() {

            int stockNumber = 140;
            String itemNm = "3";

            JPAQueryFactory queryFactory = new JPAQueryFactory(em);
            QItem qItem = item;

            queryFactory.selectFrom(qItem)
                    .where(qItem.stockNumber.goe(stockNumber)
                            .and(qItem.itemNm.contains(itemNm)))
                    .fetch()
                    .forEach(System.out::println);
        System.out.println("================querydsl로===============");
        System.out.println("================querydsl로===============");
        System.out.println("================querydsl로===============");
        System.out.println("================querydsl로===============");

    }

}