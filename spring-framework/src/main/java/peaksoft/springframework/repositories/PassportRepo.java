package peaksoft.springframework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.springframework.models.Passport;

/**
 * @author Beksultan
 */

@Repository
public interface PassportRepo extends JpaRepository<Passport, Long> {
}
