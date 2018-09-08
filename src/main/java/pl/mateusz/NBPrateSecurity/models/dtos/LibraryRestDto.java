package pl.mateusz.NBPrateSecurity.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibraryRestDto {

    public long id;
    public String title;
    public String author;
    public int pages;
    public int yearOfPublication;
    public String notes;
    private Date addBook = new Date();

}
