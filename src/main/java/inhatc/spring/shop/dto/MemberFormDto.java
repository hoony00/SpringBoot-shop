package inhatc.spring.shop.dto;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberFormDto {

    private String name;
    private String email;
    private String password;
    private String address;


}
