package peaksoft.springframework.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.springframework.repositories.AddressRepo;

/**
 * @author Beksultan
 */
@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepo addressRepo;

}
