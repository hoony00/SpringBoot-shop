package inhatc.spring.shop.repository;

import inhatc.spring.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item> {


    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    List<Item> findByPriceLessThanOrderByPriceDesc(int price);




    List<Item> findByStockNumberGreaterThanEqualAndItemNmContaining(int stockNumber, String itemNm);


    @Query(value = "select * from item where item_detail like %:itemDetail% order by price desc", nativeQuery = true)
    List<Item> findByDetailNative(@Param("itemDetail") String itemDetail);


    @Query("select i from Item i where i.stockNumber >= ?1 and i.itemNm like %?2%")
    List<Item> findByStockNumberAndItemNmQuery(@Param("stockNumber") int stockNumber,
                                               @Param("itemNm") String itemNm);

    @Query(value = "select * from item where number >= :stockNumber and item_nm like %:itemNm%", nativeQuery = true)
    List<Item> findByStockNumberAndItemNmQueryNative(@Param("stockNumber") int stockNumber,
                                                     @Param("itemNm") String itemNm);

}
