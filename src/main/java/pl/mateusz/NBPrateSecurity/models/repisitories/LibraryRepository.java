package pl.mateusz.NBPrateSecurity.models.repisitories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mateusz.NBPrateSecurity.models.LibraryModel;

import java.util.List;

public interface LibraryRepository extends JpaRepository<LibraryModel, Long> {

    List<LibraryModel> findByUserId(Long id);
    List<LibraryModel> findAllBy();
}
