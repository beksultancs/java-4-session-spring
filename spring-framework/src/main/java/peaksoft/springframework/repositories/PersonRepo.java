package peaksoft.springframework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.springframework.models.Person;

import java.util.Optional;

/**
 * @author Beksultan
 */

@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {

    boolean existsByEmail(String email);

    @Query("select p from Person p where p.passport.passportId = :pNumber")
    Optional<Person> findByPassportNumber(@Param("pNumber") String passportNumber);
}
