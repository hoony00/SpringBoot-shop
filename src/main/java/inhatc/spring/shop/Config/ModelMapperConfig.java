package inhatc.spring.shop.Config;

import org.modelmapper.ModelMapper; // 실제 ModelMapper 클래스를 가져옵니다.
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
