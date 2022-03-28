package peaksoft.driverapp.models.entities;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author Beksultan
 */
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String address;
}
