package inhatc.spring.shop.dto;


import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberformDto {

    private String name;
    private String email;
    private String password;
    private String address;


}
