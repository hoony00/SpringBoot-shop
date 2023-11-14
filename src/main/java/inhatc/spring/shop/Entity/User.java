package inhatc.spring.shop.Entity;

import inhatc.spring.shop.common.BaseEntity;
import jakarta.persistence.*;

@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;




}