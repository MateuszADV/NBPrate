package pl.mateusz.NBPrateSecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import pl.mateusz.NBPrateSecurity.models.forms.ExchangeRateNBP;
import pl.mateusz.NBPrateSecurity.service.UserSesionService;
import pl.mateusz.NBPrateSecurity.utils.GetEchangeRateNBP;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    UserSesionService userSesionService;

    private List<ExchangeRateNBP> exchangeRate;
    private GetEchangeRateNBP getEchangeRateNBP;

    @GetMapping(value = "/")
    public String getMain(ModelMap modelMap){

        boolean logged = userSesionService.isLogged();

        if(logged){
            modelMap.addAttribute("login",userSesionService.getUserDto().getLogin());
            return "index";
        }

        modelMap.addAttribute("login","BRAK");

        return "index";
    }


    //----------------------------LIGHTBOX----------------------------------------------------------------

    @GetMapping("lightbox")
    public String getLightbox(){

        return "lightbox";
    }

}
