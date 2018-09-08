package pl.mateusz.NBPrateSecurity.service;

import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import pl.mateusz.NBPrateSecurity.models.UserModel;
import pl.mateusz.NBPrateSecurity.models.dtos.UserDto;
import pl.mateusz.NBPrateSecurity.models.repisitories.UserRepoisitory;
import pl.mateusz.NBPrateSecurity.utils.HashUtils;

import java.util.Optional;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSesionService {

    @Getter
    private boolean logged;
    @Getter
    private UserDto userDto;

    @Autowired
    UserRepoisitory userRepoisitory;

    public boolean loginUser(String login, String pass){

        Optional<UserModel> userModelOptional = userRepoisitory.findByLogin(login);

        if(!userModelOptional.isPresent()){
            return false;
        }

        UserModel userModel = userModelOptional.get();

        if(!HashUtils.veryfiPassword(pass,userModel.getPassword())){
            return false;
        }

        userDto = (new ModelMapper().map(userModel, UserDto.class));

        logged = true;
        return logged;
    }

    public boolean userLogout(){
        logged = false;
        userDto = null;
        return logged;
    }
}
