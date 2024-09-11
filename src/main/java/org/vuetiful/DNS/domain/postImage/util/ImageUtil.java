package org.vuetiful.DNS.domain.postImage.util;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ImageUtil {
    public static MultipartFile convertImage(String imageUrl) {
        URL url = null;
        try {
            url = new URL(imageUrl);
            InputStream inputStream = url.openStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            BufferedImage urlImage = ImageIO.read(inputStream);
            ImageIO.write(urlImage, "jpg", bos);
            byte[] byteArray = bos.toByteArray();
            // 2) byte[] -> MultipartFile
            MultipartFile multipartFile = new CustomMultipartFile(byteArray, imageUrl);
            return multipartFile; // image를 storage에 저장하는 메서드 호출
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
