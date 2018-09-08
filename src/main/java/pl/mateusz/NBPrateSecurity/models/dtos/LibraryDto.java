package pl.mateusz.NBPrateSecurity.models.dtos;

import lombok.*;
import pl.mateusz.NBPrateSecurity.models.UserModel;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LibraryDto {

    public long id;
    public String title;
    public String author;
    public int pages;
    public int yearOfPublication;
    public String notes;
    private Date addBook = new Date();

   private UserDto user;
}
