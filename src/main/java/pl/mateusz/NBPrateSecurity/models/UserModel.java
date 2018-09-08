package pl.mateusz.NBPrateSecurity.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "personlogin")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String lastName;
    private String login;
    private String password;
    private int age;
    private String email;
    private Date dateRegister = new Date();

    @OneToMany()
    @JoinColumn(name = "book_id")
    private List<LibraryModel> libraryModelList;
}
