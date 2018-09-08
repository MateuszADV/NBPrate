package pl.mateusz.NBPrateSecurity.models.dtos;

import lombok.*;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "password")
public class UserRestDto {

    private Long id;

    private String name;
    private String lastName;
    private String login;
    private String password;
    private int age;
    private String email;
    private Date dateRegister;

    private List<LibraryRestDto> libraryRestDtos;
}
