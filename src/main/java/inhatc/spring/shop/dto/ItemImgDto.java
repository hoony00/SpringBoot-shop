package inhatc.spring.shop.dto;

import inhatc.spring.shop.Entity.ItemImg;
import lombok.*;
import org.modelmapper.ModelMapper;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemImgDto {

    private Long id;

    private String imgName;         // 저장된 이미지 파일명

    private String oriImgName;      // 원본 이미지 파일명

    private String imgUrl;          // 이미지 저장 경로

    private String repImgYn;        // 대표 이미지 여부

    private static ModelMapper modelMapper = new ModelMapper(); // ModelMapper 객체 생성

    public static ItemImgDto entityToDto(ItemImg itemImg) {
        return modelMapper.map(itemImg, ItemImgDto.class);
    }

}
