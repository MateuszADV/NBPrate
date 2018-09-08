package pl.mateusz.NBPrateSecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mateusz.NBPrateSecurity.models.repisitories.UserRepoisitory;
import pl.mateusz.NBPrateSecurity.service.UserSesionService;

@Controller
public class LoginConrtoller {

    @Autowired
    UserRepoisitory userRepoisitory;
    @Autowired
    UserSesionService userSesionService;

    @GetMapping("login")
    public String getLogin(ModelMap modelMap){

        return "login";
    }

    @PostMapping("login")
    public String postLogin(@RequestParam String login,
                            @RequestParam String pass,
                            ModelMap modelMap){

        boolean logged = userSesionService.loginUser(login, pass);

        if(logged) {
            return "redirect:/";
        }

        modelMap.addAttribute("login", "błędne dane logowania");
        return "login";
    }

    @GetMapping("logout")
    public String getLogOut(){
        userSesionService.userLogout();
        return "redirect:/login";
    }
}
