package org.vuetiful.DNS.domain.postImage.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class PostImageResponse {
    private Integer imageId;
    private MultipartFile image;
}
