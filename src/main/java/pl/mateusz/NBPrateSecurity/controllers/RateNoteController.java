package pl.mateusz.NBPrateSecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import pl.mateusz.NBPrateSecurity.models.forms.ExchangeRateNBP;
import pl.mateusz.NBPrateSecurity.service.UserSesionService;
import pl.mateusz.NBPrateSecurity.utils.GetEchangeRateNBP;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class RateNoteController {

    @Autowired
    UserSesionService userSesionService;

    private List<ExchangeRateNBP> exchangeRate;
    private GetEchangeRateNBP getEchangeRateNBP;

    @GetMapping("kurswalut")
    public String getKurswalut(ModelMap modelMap){

        boolean logged = userSesionService.isLogged();

        if(!logged){
            return "login";
        }



        modelMap.addAttribute("login",userSesionService.getUserDto().getLogin());

        return "kurswalut";
    }

    @GetMapping("kurs_walut_tabela_A")
    public String getTableA(ModelMap modelMap){

        boolean logged = userSesionService.isLogged();

        if(!logged){
            return "login";
        }

        exchangeRate = new ArrayList<>();
        getEchangeRateNBP = new GetEchangeRateNBP();
        exchangeRate.addAll(getEchangeRateNBP.echangeRateTableA(getEchangeRateNBP.getTableRate("http://www.nbp.pl/kursy/xml/LastA.xml")));

        if(exchangeRate.isEmpty()){
            modelMap.addAttribute("numerTabeli","BRAK DANYCH DO WYŚWIETLENIA");
            modelMap.addAttribute("dataPublikacji",new Date().toString());
        }else {
            modelMap.addAttribute("numerTabeli", exchangeRate.get(0).getNumberTableNBP());
            modelMap.addAttribute("dataPublikacji", exchangeRate.get(0).getDatePublication());
            modelMap.addAttribute("kursTable",exchangeRate);
            modelMap.addAttribute("kurs","tabelaA");

        }

        modelMap.addAttribute("login",userSesionService.getUserDto().getLogin());

        return "kurswalut";
    }

    @GetMapping("kurs_walut_tabela_C")
    public String getTableB(ModelMap modelMap){

        boolean logged = userSesionService.isLogged();

        if(!logged){
            return "login";
        }

        exchangeRate = new ArrayList<>();
        getEchangeRateNBP = new GetEchangeRateNBP();
        exchangeRate.addAll(getEchangeRateNBP.echangeRateTableC(getEchangeRateNBP.getTableRate("http://www.nbp.pl/kursy/xml/LastC.xml")));

        if(exchangeRate.isEmpty()){
            modelMap.addAttribute("numerTabeli","BRAK DANYCH DO WYŚWIETLENIA");
            modelMap.addAttribute("dataPublikacji",new Date().toString());
        }else {
            modelMap.addAttribute("numerTabeli", exchangeRate.get(0).getNumberTableNBP());
            modelMap.addAttribute("dataPublikacji", exchangeRate.get(0).getDatePublication());
            modelMap.addAttribute("dataNotowania",exchangeRate.get(0).getDateOfListing());
            modelMap.addAttribute("kursTable",exchangeRate);
            modelMap.addAttribute("kurs","tabelaC");

        }

        modelMap.addAttribute("login",userSesionService.getUserDto().getLogin());

        return "kurswalut";
    }

}
