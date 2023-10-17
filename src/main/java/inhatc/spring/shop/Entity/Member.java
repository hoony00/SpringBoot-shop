package inhatc.spring.shop.Entity;

import inhatc.spring.shop.Content.Role;
import inhatc.spring.shop.dto.MemberFormDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long mid;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberFormDto memberformDto, PasswordEncoder passwordEncoder) {

        return Member.builder()
                .role(Role.USER)
                .name(memberformDto.getName())
                .email(memberformDto.getEmail())
                .address(memberformDto.getAddress())
                .name(memberformDto.getName())
                .password(passwordEncoder.encode(memberformDto.getPassword()))
                .build();
    }
}
