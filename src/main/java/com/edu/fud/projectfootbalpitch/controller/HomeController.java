package com.edu.fud.projectfootbalpitch.controller;

import com.edu.fud.projectfootbalpitch.dto.ProductDto;
import com.edu.fud.projectfootbalpitch.dto.UserDto;
import com.edu.fud.projectfootbalpitch.service.CategoryProductService;
import com.edu.fud.projectfootbalpitch.service.ProductService;
import com.edu.fud.projectfootbalpitch.service.UserService;
import com.edu.fud.projectfootbalpitch.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryProductService categoryProductService;

//    @ModelAttribute("categories")
//    public List<CategoryProductDto> getCategories(){
//        return categoryProductService.findAll();
//    }

    @RequestMapping("/index")
    public String openHome(Model model) {
       List<ProductDto> productDtoList=productService.findAll();
        model.addAttribute("products",productDtoList);
        return "index";
    }

    @RequestMapping("/about")
    public String openBbout() {
        return "about";
    }

    @RequestMapping("/sign-in")
    public String openLogin() {
        return "login";
    }

    @RequestMapping("/access-denied")
    public String openError() {
        return "error";
    }

    @RequestMapping("/register")
    public String openRegister(Model model) {
        model.addAttribute("objUser", new UserDto());
        return "register";
    }

    @RequestMapping(value = "/add-register", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("objUser") @Valid UserDto userDto, BindingResult result,
                          @RequestParam("profileImage") MultipartFile file, Model model
            , HttpSession session) {
        try {
            if (result.hasErrors()) {
                System.out.println("ERROR" + result.toString());
                model.addAttribute("objUser", userDto);
                return ("register");
            }
            if (file.isEmpty()) {
                //if file empty then try our message
                userDto.setImage("default.png");
            } else {
                //file the file to folder anh update the name to contact
                userDto.setImage(file.getOriginalFilename());
                File saveFile = new ClassPathResource("static/images/avt-user").getFile();
                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            }
            System.out.println("User :" + userDto);
            userService.save(userDto);
            session.setAttribute("message", new Message("Đăng kí thành công!Bây giờ bạn có thể đăng nhập vào hệ thống!"
                    , "alert-success"));
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("message", new Message("Something Went Wrong!" + e.getMessage(),
                    "alert-danger"));
        }
        return ("register");
    }

    //product
    @RequestMapping("/products")
    public String openProduct() {
        return "products";
    }

    @RequestMapping("/product-detail")
    public String openProductDetail() {
        return "detailProduct";
    }

    //booking home
    @RequestMapping("/booking")
    public String openBookingPitch() {
        return "booking";
    }

    @RequestMapping("/booking-pitch-detail")
    public String openBookingPitchDetail() {
        return "detailPitch";
    }

    @RequestMapping("/booking-sub-pitch-detail")
    public String openBookingSubPitchDetail() {
        return "bookingSubPitch";
    }
}
