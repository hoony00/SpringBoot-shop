package inhatc.spring.shop.repository;

import com.querydsl.core.BooleanBuilder;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;


import java.util.List;

import static inhatc.spring.shop.entity.QItem.*; // QueryDSL - QItem 스태틱 임포트
import static org.junit.jupiter.api.Assertions.*; // JUnit5 - Assertions 스태틱 임포트

@SpringBootTest // 스프링 컨테이너를 이용한 테스트
@Transactional // 테스트가 끝나면 롤백
class ItemRepositoryTest {

    private final ItemRepository itemRepository;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public ItemRepositoryTest(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    @BeforeEach //내가 하는 모든 테스트의 공통인 경우 사용
    public void init() {
        for (int i = 1; i <= 5; i++) {
            Item item = Item.builder()
                    .itemNm("테스트 상품" + i)
                    .price(10000 + i)
                    .stockNumber(100 + i)
                    .itemDetail("테스트 상품 상세 설명" + i)
                    .itemSellStatus(ItemSellStatus.SELL)
                    .build();

            itemRepository.save(item);

        }

        for (int i = 6; i <= 10; i++) {
            Item item = Item.builder()
                    .itemNm("테스트 상품" + i)
                    .price(10000 + i)
                    .stockNumber(100 + i)
                    .itemDetail("테스트 상품 상세 설명" + i)
                    .itemSellStatus(ItemSellStatus.SOLD_OUT)
                    .build();

            itemRepository.save(item);

        }
    }

    /*
    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNmTest() {
            itemRepository.findByItemNm("테스트 상품1").
                    forEach(item1 -> {
                        System.out.println("item1 = " + item1);
                        assertEquals(item1.getItemNm(), "테스트 상품1");
                        System.out.println("테스트 성공");
                    });
        }
    }
     */

/*    @Test
    @DisplayName("상품명, 상품상세설명 or 테스트")
    public void findByItemNmOrItemDetailTest() {
        itemRepository.findByItemNmOrItemDetail("테스트 상품1", "테스트 상품 상세 설명").
                forEach((item -> {
                    System.out.println("item = " + item);
                    assertEquals(item.getItemDetail(), "테스트 상품 상세 설명");
                }));

    }*/


    @Test
    @DisplayName("가격 LessThan 테스트")
    public void findByPriceLessThanTest() {
        itemRepository.findByPriceLessThanOrderByPriceDesc(10005).
                forEach((item -> {
                    System.out.println("sout -> item = " + item);
                }));

    }

    @Test
    @DisplayName("상품 상세 조회 테스트")
    public void findByDetailTest() {
        itemRepository.findByDetailNative("테스트 상품 상세 설명1").
                forEach((item -> {
                    System.out.println("item = " + item);
                }));

    }

    @Test
    @DisplayName("QueryDSL 테스트")
    public void queryDslTest() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QItem qItem = item;

        queryFactory.selectFrom(qItem)
                .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(qItem.itemDetail.like("%" + "1" + "%"))
                .orderBy(qItem.price.desc())
                .fetch()
                .forEach((item -> {
                    System.out.println("item = " + item);
                }));

    }

    @Test
    @DisplayName("QueryDSL 테스트2")
    public void queryDslTest2() {
        // 동적 쿼리의 조건을 조립하기 위한 빌더를 초기화
        BooleanBuilder builder = new BooleanBuilder();

        //Querydsl에서 자동 생성한 엔티티 클래스에 별칭인 QItem에 item엔티티의 Querydsl 메타데이터
        //를 가진 QItem 객체를 할당
        QItem qItem = item;

        int price = 10004;
        String itemDetail = "테스트";
        String itemSellStatus = "SELL";

        // "테스트"라는 str을 가진 itemDetail을 찾음
        builder.and(qItem.itemDetail.like("%" + itemDetail + "%"));
        // 가격이 10004보다 큰 item을 찾음
        builder.and(qItem.price.gt(price));

        // 상품 판매 상태가 "SELL"인 item을 찾음
        if (StringUtils.equals(itemSellStatus, ItemSellStatus.SELL)) {
            builder.and(qItem.itemSellStatus.eq(ItemSellStatus.SELL));
        }

        //페이징 처리 관련 기능은 domain 클래스에서 임폴트 할 것 !
        // Pageable은 인터페이스이므로 PageRequest 객체를 생성하여 사용
        // PageRequest는 Pageable의 구현체이므로 Pageable로 받을 수 있음
        // PageRequest.of(0, 5)는 0페이지부터 5개의 데이터를 가져온다는 의미
        Pageable pageable = PageRequest.of(0, 5);

        // Page는 findAll() 메서드의 반환 타입이므로 Page로 받을 수 있음
        Page<Item> page = itemRepository.findAll(builder, pageable);

        // 페이징 처리된 데이터를 List로 가져옴
       List<Item> content = page.getContent();

       // 페이징 처리된 데이터를 출력
       content.stream().forEach(e-> System.out.println("출력 값들 = " + e));

       // itemRepository.findAll(builder, pageable).getContent().stream().forEach(e-> System.out.println("출력 값들 = " + e));
    }

}