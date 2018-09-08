package pl.mateusz.NBPrateSecurity.models.repisitories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mateusz.NBPrateSecurity.models.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserRepoisitory extends JpaRepository<UserModel, Long> {
    List<UserModel> findAllBy();
    Optional<UserModel> findById(Long id);
    Optional<UserModel> findByLogin(String login);
    boolean existsByLogin(String login);

}
