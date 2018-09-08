package pl.mateusz.NBPrateSecurity.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "library")
public class LibraryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    public String title;
    public String author;
    public int pages;
    public int yearOfPublication;
    public String notes;
    private Date addBook = new Date();

    //@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;
}
