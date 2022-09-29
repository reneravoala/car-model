package com.example.carmodel.Controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class LogoController {

    @RequestMapping(value = "/logo/{marque}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public void getLogo(HttpServletResponse response, @PathVariable String marque) throws IOException {
        ClassPathResource imgFile = new ClassPathResource("static/make-images/"+marque+".png");
        if(!imgFile.exists()){
            imgFile = new ClassPathResource("static/make-images/NoImage.png");
        }
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
    }
}
