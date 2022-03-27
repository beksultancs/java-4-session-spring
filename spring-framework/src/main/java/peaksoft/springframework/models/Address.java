package peaksoft.springframework.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author Beksultan
 */
@Entity
@Table(name = "addresses")
@Getter @Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Country country;

    private String city;

    private String street;

    private int houseNum;
}
