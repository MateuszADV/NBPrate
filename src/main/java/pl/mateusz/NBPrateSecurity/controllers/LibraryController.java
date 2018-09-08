package pl.mateusz.NBPrateSecurity.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.mateusz.NBPrateSecurity.models.LibraryModel;
import pl.mateusz.NBPrateSecurity.models.UserModel;
import pl.mateusz.NBPrateSecurity.models.dtos.LibraryDto;
import pl.mateusz.NBPrateSecurity.models.dtos.UserDto;
import pl.mateusz.NBPrateSecurity.models.repisitories.LibraryRepository;
import pl.mateusz.NBPrateSecurity.service.UserSesionService;

import java.util.List;

@Controller
public class LibraryController {

    @Autowired
    UserSesionService userSesionService;
    @Autowired
    LibraryRepository libraryRepository;


    private List<LibraryModel> libraryModelList;

    @GetMapping("/library")
    public String getLibrary(ModelMap modelMap){

        boolean logged = userSesionService.isLogged();

        if(!logged){
            return "login";
        }

        modelMap.addAttribute("login",userSesionService.getUserDto().getLogin());
        modelMap.addAttribute("libraryDto", new LibraryDto());

        libraryModelList = libraryRepository.findByUserId(userSesionService.getUserDto().getId());

        modelMap.addAttribute("library",libraryModelList);


        return "library";
    }

    private LibraryModel libraryModel;
    private UserDto userDto;
    private UserModel userModel;

    @PostMapping("/library")
    public String postLibrary(@ModelAttribute("libraryDto") LibraryDto library, ModelMap modelMap){

        LibraryDto libraryDto;

        userDto = userSesionService.getUserDto();
        userModel = (new ModelMapper().map(userDto, UserModel.class));

        libraryDto = (new ModelMapper().map(library, LibraryDto.class));
        libraryDto.setUser(userDto);
        System.out.println(libraryDto);

        libraryModel = (new ModelMapper().map(libraryDto, LibraryModel.class));
        System.out.println(libraryModel);
        libraryRepository.save(libraryModel);


        modelMap.addAttribute("login",userSesionService.getUserDto().getLogin());
        modelMap.addAttribute("libraryDto", new LibraryDto());

        libraryModelList = libraryRepository.findByUserId(userSesionService.getUserDto().getId());

        modelMap.addAttribute("library",libraryModelList);


        return "library";
    }
}
