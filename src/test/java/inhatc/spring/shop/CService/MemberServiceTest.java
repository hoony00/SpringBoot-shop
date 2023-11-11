package inhatc.spring.shop.CService;

import inhatc.spring.shop.Entity.Item;
import inhatc.spring.shop.Entity.Member;
import inhatc.spring.shop.Entity.Order;
import inhatc.spring.shop.Entity.OrderItem;
import inhatc.spring.shop.constant.ItemSellStatus;
import inhatc.spring.shop.dto.MemberFormDto;
import inhatc.spring.shop.repository.ItemRepository;
import inhatc.spring.shop.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @PersistenceContext
    EntityManager em;

    public Item createItem(){
        Item item = new Item();
        item.setItemNm("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("상세설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        return item;
    }

    @Test
    @DisplayName("영속성 전이 테스트")
    public void cascadeTest() {

        Order order = new Order();

        for(int i=0;i<3;i++){
            Item item = this.createItem();
            itemRepository.save(item);
            OrderItem orderItem = new OrderItem();
            // orderItem에 item을 넣어줌
            orderItem.setItem(item);
            orderItem.setCount(10);
            orderItem.setOrderPrice(1000);
            orderItem.setOrder(order);

            // 자식 엔티티를 부모 엔티티에 추가
            order.getOrderItems().add(orderItem);
        }

        // 부모 엔티티를 저장할 때, 연관된 자식 엔티티도 저장됨 (CascadeType.ALL 설정으로)
        orderRepository.saveAndFlush(order);
        em.clear();

        Order savedOrder = orderRepository.findById(order.getId())
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        assertEquals(3, savedOrder.getOrderItems().size());

        System.out.println("OrderItems size: " + savedOrder.getOrderItems().size());
    }


}

