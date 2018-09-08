package pl.mateusz.NBPrateSecurity.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.mateusz.NBPrateSecurity.models.UserModel;
import pl.mateusz.NBPrateSecurity.models.dtos.UserDto;
import pl.mateusz.NBPrateSecurity.models.repisitories.UserRepoisitory;
import pl.mateusz.NBPrateSecurity.utils.HashUtils;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
public class RegisterController {

    @Autowired
    UserRepoisitory userRepoisitory;

    @GetMapping("/register")
    public String getRegister(ModelMap modelMap){
        modelMap.addAttribute("userDto", new UserDto());

        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute("userDto")@Valid UserDto userDto, BindingResult result, ModelMap modelMap){
        System.out.println(userDto);

        boolean loginExist = userRepoisitory.existsByLogin(userDto.getLogin());

        if(result.hasErrors()){
            System.out.println("MAMY bŁąd");

            if(loginExist){
                modelMap.addAttribute("loginExist","podany login jest już zajety");
            }
            return "register";
        }

        UserModel userModel;
        userDto.setPassword(HashUtils.hashPassword(userDto.getPassword()));
        userModel = (new ModelMapper().map(userDto, UserModel.class));

        userRepoisitory.save(userModel);
        System.out.println(userModel);
        System.out.println(userDto.getPassword());


        return "redirect:/";
    }


    @GetMapping("/lista")
    public String getLista(ModelMap modelMap){

        List<UserModel> userModelList = new ArrayList<>();

        userModelList = userRepoisitory.findAllBy();

        modelMap.addAttribute("lista",userModelList);

        return "lista";
    }

}
