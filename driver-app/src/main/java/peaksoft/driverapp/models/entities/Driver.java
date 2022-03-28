package peaksoft.driverapp.models.entities;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

/**
 * @author Beksultan
 */
@Entity
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String driverName;

    private int experience;

    private int rating;

    @OneToOne(cascade = ALL,
            fetch = LAZY)
    private DriverLicense driverLicense;

    @OneToOne(cascade = ALL)
    private Car car;

    @OneToOne(cascade = ALL,
            fetch = LAZY)
    private BankAccount bankAccount;
}
