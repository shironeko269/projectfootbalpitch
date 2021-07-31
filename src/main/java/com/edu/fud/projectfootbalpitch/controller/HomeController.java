package com.edu.fud.projectfootbalpitch.controller;

import com.edu.fud.projectfootbalpitch.config.GooglePojo;
import com.edu.fud.projectfootbalpitch.config.GoogleUtils;
import com.edu.fud.projectfootbalpitch.dto.CategoryProductDto;
import com.edu.fud.projectfootbalpitch.dto.ProductDto;
import com.edu.fud.projectfootbalpitch.dto.UserDto;
import com.edu.fud.projectfootbalpitch.entity.ProductsEntity;
import com.edu.fud.projectfootbalpitch.repository.UserRepository;
import com.edu.fud.projectfootbalpitch.service.CategoryProductService;
import com.edu.fud.projectfootbalpitch.service.ProductService;
import com.edu.fud.projectfootbalpitch.service.UserService;
import com.edu.fud.projectfootbalpitch.service.impl.EmailService;
import com.edu.fud.projectfootbalpitch.util.Message;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private GoogleUtils googleUtils;

    @Autowired
    private CategoryProductService categoryProductService;
    @Autowired
    private Environment env;
//    @ModelAttribute("categories")
//    public List<CategoryProductDto> getCategories(){
//        return categoryProductService.findAll();
//    }

    @RequestMapping("/index")
    public String openHome(Model model) {
        List<ProductDto> productDtoList = productService.findAll();
        model.addAttribute("products", productDtoList);
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

    @RequestMapping("/login-google")
    public String loginGoogle(HttpServletRequest request) throws ClientProtocolException, IOException {
        String code = request.getParameter("code");
        String type = env.getProperty("google.link.get.scope");
        if (code == null || code.isEmpty()) {
            return "redirect:/login?google=error";
        }
        String accessToken = googleUtils.getToken(code, type);

        GooglePojo googlePojo = googleUtils.getUserInfo(accessToken);
        System.out.println(googlePojo.toString());
        UserDetails userDetail = googleUtils.buildUser(googlePojo);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
                userDetail.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/user/profile";
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
    @PostMapping("/send-otp")
    public String sendOTP(@RequestParam("email") String email, HttpSession session) {
        System.out.println("Email: " + email);
        Random random = new Random(System.currentTimeMillis());
        int otp = random.nextInt(99999999);
        System.out.println("OTP: " + otp);
        String subject = "OTP From SCM";
        String message = ""
                + "<div style='border:1px solid #e2e2e2;padding:20px'>"
                + "<h1>"
                + "Mã OTP là :"
                + "<b>" + otp
                + "</n>"
                + "</h1>"
                + "</div>";
        String to = email;
        boolean flag = emailService.sendEmail(subject, message, to);
        if (flag) {
            session.setAttribute("myotp", otp);
            session.setAttribute("email", email);
            return "register";
        } else {
            session.setAttribute("message", "Kiểm tra lại email !!");
            return "register";
        }
    }
    @RequestMapping(value = "/add-register", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("objUser") @Valid UserDto userDto, BindingResult result,
                          @RequestParam("profileImage") MultipartFile file, Model model
            , HttpSession session) {
        try {
            if (!userService.getUserByUsername(userDto.getUserName()).isPresent()
            ) {
                if (!userService.getUserByGmail(userDto.getGmail()).isPresent()) {
                        if (result.hasErrors()) {
                            System.out.println("ERROR" + result.toString());
                            model.addAttribute("objUser", userDto);
                            return ("register");
                        }
                    //int myOtp = (int) session.getAttribute("myotp");
                    //if (myOtp==otp){-----------
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
                    //}else {---------------
                        //session.setAttribute("messageErrorOtp", new Message("OTP sai!",------------
                        //        "alert-danger"));---------------------
                   // }
                } else {
                    session.setAttribute("message", new Message("Gmail đã tồn tại!",
                            "alert-danger"));
                }
            } else {
                session.setAttribute("message", new Message("Tên đăng nhập đã tồn tại!",
                        "alert-danger"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("messageErrorOtp", new Message("OTP sai!",
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
