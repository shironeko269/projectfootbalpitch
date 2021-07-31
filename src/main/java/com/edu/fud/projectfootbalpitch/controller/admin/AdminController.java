package com.edu.fud.projectfootbalpitch.controller.admin;

import com.edu.fud.projectfootbalpitch.dto.DistrictDto;
import com.edu.fud.projectfootbalpitch.dto.FootBallPitchDto;
import com.edu.fud.projectfootbalpitch.dto.UserDto;
import com.edu.fud.projectfootbalpitch.dto.WardDto;
import com.edu.fud.projectfootbalpitch.service.DistrictService;
import com.edu.fud.projectfootbalpitch.service.FootbalPitchService;
import com.edu.fud.projectfootbalpitch.service.UserService;
import com.edu.fud.projectfootbalpitch.service.WardService;
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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private DistrictService districtService;

    @Autowired
    private FootbalPitchService footbalPitchService;

    @Autowired
    private WardService wardService;

    @Autowired
    private UserService userService;
    //Pitch
    @ModelAttribute("districts")
    public List<DistrictDto> getDistricts(){
        return districtService.findAll();
    }
    @ModelAttribute("wards")
    public List<WardDto> getWards(){
        return wardService.findAll();
    }
//    @ModelAttribute("managers")
//    public List<UserDto> getUserManager(){
//        return userService.findAllByRoleAndNoManagerPitch();
//    }
    @RequestMapping("/index")
    public String home(){
        return "admin/index";
    }
    @RequestMapping("/add-new-pitch")
    public String openAddNewPitch(Model model){
        model.addAttribute("objFootballPitch",new FootBallPitchDto());
        model.addAttribute("managers",userService.findAllByRoleAndNoManagerPitch());
        return "admin/pages/tables/addNewPitch";
    }
    @PostMapping("/add-new-pitch")
    public String addNewFootballPitch(@Valid @ModelAttribute("objFootballPitch") FootBallPitchDto footBallPitchDto,
                                      BindingResult result, @RequestParam("imageParam")MultipartFile file, Model model,
                                      HttpSession session){
        try{
            if (result.hasErrors()){
                model.addAttribute("objFootballPitch",footBallPitchDto);
                return "admin/pages/tables/addNewPitch";
            }
            if (file.isEmpty()){
                footBallPitchDto.setImage("default.png");
            }else {
                //file the file to folder anh update the name to contact
                footBallPitchDto.setImage(file.getOriginalFilename());
                File saveFile = new ClassPathResource("static/images/image-pitch").getFile();
                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            }
            System.out.println("Footbal :" + footBallPitchDto);
            footbalPitchService.save(footBallPitchDto);
            session.setAttribute("message", new Message("Đăng kí thành công!Bây giờ bạn hãy tạo các sân bóng trong khu vực này!"
                    , "alert-success"));
        }catch (Exception e){
            e.printStackTrace();
            session.setAttribute("message", new Message("Something Went Wrong!" + e.getMessage(),
                    "alert-danger"));
        }
        return "redirect:/admin/add-new-sub-pitch";
    }
    @RequestMapping("/add-new-sub-pitch")
    public String openAddNewSubPitch(){
        return "admin/pages/tables/addNewSubPitch";
    }
    @RequestMapping("/add-new-sub-pitch-by-pitch")
    public String openAddNewSubPitchByPitch(){
        return "admin/pages/tables/addNewSubPitchByPitch";
    }
    @RequestMapping("/list-pitch")
    public String openListPitch(){
        return "admin/pages/tables/listPitch";
    }
    @RequestMapping("/list-sub-pitch")
    public String openListSubPitch(){
        return "admin/pages/tables/listSubPitch";
    }
    @RequestMapping("/profile-pitch")
    public String openProfile(){
        return "admin/pages/tables/profilePitch";
    }
    //Admin
    @RequestMapping("/account")
    public String openAccountPage(){
    return "admin/pages/accountAdmin/admin";
}
    //Customer
    @RequestMapping("/list-customer")
    public String openListCustomer(){
        return "admin/pages/customers/listCustomers";
    }
    //Manger
    @RequestMapping("/add-manager")
    public String openAddManager(){
        return "admin/pages/employee/addNewEmployee";
    }
    @RequestMapping("/list-manager")
    public String openListManager(){
        return "admin/pages/employee/listEmployee";
    }
    //Order
    @RequestMapping("/list-order")
    public String openListOrder(){
        return "admin/pages/listOrder/listOrder";
    }
    @RequestMapping("/view-detail-order")
    public String openDetailOrder(){
        return "admin/pages/listOrder/viewDetailOrder";
    }
    //Product
    @RequestMapping("/add-new-category")
    public String openAddNewCategory(){
        return "admin/pages/Product Manager/addNewCategory";
    }
    @RequestMapping("/add-new-product")
    public String openAddNewProduct(){
        return "admin/pages/Product Manager/addNewProduct";
    }
    @RequestMapping("/add-new-supplier")
    public String openAddNewSupplier(){
        return "admin/pages/Product Manager/addNewSupplier";
    }
    @RequestMapping("/list-product")
    public String openListProduct(){
        return "admin/pages/Product Manager/listProduct";
    }
}
