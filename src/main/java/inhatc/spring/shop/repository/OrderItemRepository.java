package inhatc.spring.shop.repository;


import inhatc.spring.shop.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {




}
