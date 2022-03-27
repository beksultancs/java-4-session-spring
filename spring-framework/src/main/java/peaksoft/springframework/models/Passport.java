package peaksoft.springframework.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

/**
 * @author Beksultan
 */

@Entity
@Table(name = "passports")
@Getter
@Setter
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passportId;

    @Enumerated(EnumType.STRING)
    private Country country;

    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    private LocalDate dateOfBirth;

    @OneToOne(cascade = ALL, fetch = EAGER)
    private Address address;
}
