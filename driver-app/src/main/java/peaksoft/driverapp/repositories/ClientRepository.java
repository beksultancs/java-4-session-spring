package peaksoft.driverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.driverapp.models.entities.Client;

import java.util.UUID;

/**
 * @author Beksultan
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

    boolean existsByEmail(String email);
}
