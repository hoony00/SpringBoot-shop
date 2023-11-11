package inhatc.spring.shop.repository;


import inhatc.spring.shop.Entity.Cart;
import inhatc.spring.shop.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {




}