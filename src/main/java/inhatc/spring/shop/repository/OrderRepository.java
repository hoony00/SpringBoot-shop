package inhatc.spring.shop.repository;


import inhatc.spring.shop.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long> {




}
