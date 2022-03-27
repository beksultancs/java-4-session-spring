package peaksoft.springframework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.springframework.models.Address;

import java.util.UUID;

/**
 * @author Beksultan
 */
@Repository
public interface AddressRepo extends JpaRepository<Address, UUID> {
}
