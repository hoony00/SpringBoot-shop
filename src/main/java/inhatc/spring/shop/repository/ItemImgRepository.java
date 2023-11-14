package inhatc.spring.shop.repository;

import inhatc.spring.shop.Entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {

    List<ItemImg> findByItemIdOrderByIdAsc(Long itemId);  // 상품 이미지 리스트 가져오기


}
