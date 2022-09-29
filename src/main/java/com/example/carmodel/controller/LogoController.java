package com.example.carmodel.controller;

import com.example.carmodel.entity.Make;
import com.example.carmodel.repository.MakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


@RestController
public class LogoController {

    @Autowired
    private MakeRepository makeRepository;

    private InputStream getResourceAsStream(String resource) {
        final InputStream in
                = getContextClassLoader().getResourceAsStream(resource);

        return in == null ? getClass().getResourceAsStream(resource) : in;
    }

    private ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    @GetMapping("/logo")
    public List<String> getResourceLogo() throws IOException {
        List<String> filenames = new ArrayList<>();
        try (InputStream in = getResourceAsStream("static/make-images/");
             BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String resource;

            while ((resource = br.readLine()) != null) {
                filenames.add(resource);
            }
        }
        return filenames;
    }

    @RequestMapping(value = "/logo/{marque}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public void getLogo(HttpServletResponse response , @PathVariable String marque) throws IOException {
        Make make = makeRepository.findByName(marque.replace('_', ' '));
        ClassPathResource imgFile = new ClassPathResource("static/make-images/"+marque.replace('_', '-').replace(' ', '-')+".png");

        if (!imgFile.exists()) {
            imgFile = new ClassPathResource("static/make-images/NoImage.png");
        }

        if(make != null && !imgFile.exists()){
            imgFile = new ClassPathResource("static/make-images/CommingSoon.png");
        }

        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
    }
}
