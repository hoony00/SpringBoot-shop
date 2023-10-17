package inhatc.spring.shop.repository;

import inhatc.spring.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByItemNm(String itemNm);

    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    List<Item> findByPriceLessThanOrderByPriceDesc(int price);

    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByDetail(@Param("itemDetail") String itemDetail);

    @Query(value = "select * from item where item_detail like %:itemDetail% order by price desc", nativeQuery = true)
    List<Item> findByDetailNative(@Param("itemDetail") String itemDetail);

    /**
        - StockNumber >= ? and ItemNm like %?%
        - JPA
     */
    List<Item> findByStockNumberGreaterThanEqualAndItemNmContaining(int stockNumber, String itemNm);

    /**
        - StockNumber >= ? and ItemNm like %?%
        - JPQL
     */
    @Query("select i from Item i where i.stockNumber >= ?1 and i.itemNm like %?2%")
    List<Item> findByStockNumberAndItemNmQuery(@Param("stockNumber") int stockNumber,
                                               @Param("itemNm") String itemNm);

    /**
        - StockNumber >= ? and ItemNm like %?%
        - JPQL + Native Query
     */
    @Query(value = "select * from item where number >= :stockNumber and item_nm like %:itemNm%", nativeQuery = true)
    List<Item> findByStockNumberAndItemNmQueryNative(@Param("stockNumber") int stockNumber,
                                                     @Param("itemNm") String itemNm);

}
