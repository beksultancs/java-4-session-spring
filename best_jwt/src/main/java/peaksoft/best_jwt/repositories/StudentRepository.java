package peaksoft.best_jwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.best_jwt.models.Student;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Beksultan
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
    Optional<Student> findByEmail(String email);
}
