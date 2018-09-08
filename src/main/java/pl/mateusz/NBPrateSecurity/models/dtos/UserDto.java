package pl.mateusz.NBPrateSecurity.models.dtos;

import lombok.*;
import pl.mateusz.NBPrateSecurity.models.LibraryModel;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "password")
public class UserDto {

    private Long id;
    @NotEmpty(message = "pole nie może być puste")
    @Size(min=3,max=20,message = "Imię musi składać się z conajmnej 2 liter")
    private String name;
    @NotEmpty(message = "pole nie moze być puste")
    private String lastName;
    @Size(min = 5, message = "Login mus sie składaćz conajmniej 3 znaków")
    private String login;
    @Size(min = 6,message = "Haslo musi zawierać min 6 znaków")
    private String password;
    @Min(value = 18,message = "Jesteś osobą niepełnoletnią")
    @Max(value = 100, message = "wiek nie może być wiekszy niż 100")
    private int age;
    @Email(message = "podany mail jest niepoprawny")
    private String email;
    private Date dateRegister = new Date();
}
