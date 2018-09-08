package pl.mateusz.NBPrateSecurity.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.mateusz.NBPrateSecurity.models.LibraryModel;
import pl.mateusz.NBPrateSecurity.models.UserModel;
import pl.mateusz.NBPrateSecurity.models.dtos.LibraryRestDto;
import pl.mateusz.NBPrateSecurity.models.dtos.UserDto;
import pl.mateusz.NBPrateSecurity.models.dtos.UserRestDto;
import pl.mateusz.NBPrateSecurity.models.repisitories.LibraryRepository;
import pl.mateusz.NBPrateSecurity.models.repisitories.UserRepoisitory;
import pl.mateusz.NBPrateSecurity.service.UserSesionService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiRestControler {

    LibraryRepository libraryRepository;
    UserRepoisitory userRepoisitory;

    @Autowired
    UserSesionService userSesionService;

    @Autowired
    public ApiRestControler(LibraryRepository libraryRepository, UserRepoisitory userRepoisitory) {
        this.libraryRepository = libraryRepository;
        this.userRepoisitory = userRepoisitory;
    }

    @GetMapping("api/login/{log}")
    public ResponseEntity<UserRestDto> apiRestLogin(@PathVariable String log){

        boolean logged = userSesionService.isLogged();

        if(!logged){
            throw new RuntimeException("+++++++++++++++++You don't logged :(:(:(++++++++++++++++");
        }
        if(!log.equals(userSesionService.getUserDto().getLogin())){
            throw new RuntimeException("+++++++++++++++++Wrong login+++++++++++++++++++++");
        }

       UserModel userModel = userRepoisitory.findByLogin(log).get();

        List<LibraryModel> libraryModels = libraryRepository.findByUserId(userModel.getId());

        List<LibraryRestDto> libraryRestDtos = new ArrayList<>();

        for (LibraryModel libraryModel : libraryModels) {
            libraryRestDtos.add(new ModelMapper().map(libraryModel, LibraryRestDto.class));
        }

        UserRestDto userRestDto;
        userRestDto = (new ModelMapper().map(userModel, UserRestDto.class));

        //zmienić jak naprawie
        userRestDto.setLibraryRestDtos(libraryRestDtos);
        return ResponseEntity.ok().body(userRestDto);
    }
}
