package inhatc.spring.shop.Config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Slf4j
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        //시큐리티에서 로구인된 사용자 가져오는 법ㄲㄱ
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = " ";
        if (authentication != null && authentication.isAuthenticated()) {
            userId = authentication.getName();

            System.out.println("=====================>시큐리티에서 userId = " + userId);
        }


      //  return Optional.empty();
        return Optional.of(userId);
    }
}
