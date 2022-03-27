package peaksoft.springframework.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.springframework.repositories.PassportRepo;

/**
 * @author Beksultan
 */
@Service
@AllArgsConstructor
public class PassportService {

    private final PassportRepo passportRepo;

}
