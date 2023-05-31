package com.example.onlineshop.controller;


import com.example.onlineshop.entity.User;
import com.example.onlineshop.entity.UserType;
import com.example.onlineshop.repository.ProductRepository;
import com.example.onlineshop.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class MainController {
    @Value("${onlineShop.upload.image.path}")
    private String imageUploadPath;
    private final ProductRepository productRepository;

    @GetMapping("/")
    public String main(ModelMap modelMap) {
        modelMap.addAttribute("products",productRepository.findAll());
        return "index";
    }

    @GetMapping("/customLogin")
    public String customLogin() {
        return "customLoginPage";
    }

    @GetMapping("/customSuccessLogin")
    public String customSuccessLogin(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            User user = currentUser.getUser();
            if (user.getType() == UserType.ADMIN) {
                return "redirect:/user/admin";
            } else if (user.getType() == UserType.USER) {
                return "redirect:/";
            }
        }
        return "redirect:/index";
    }


    @GetMapping(value = "/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("imageName") String imageName) throws IOException {
        File file = new File(imageUploadPath + imageName);
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            return IOUtils.toByteArray(fis);
        }
        return null;
    }
}