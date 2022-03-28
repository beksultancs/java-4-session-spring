package peaksoft.driverapp.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Beksultan
 */
@Getter @Setter
public class ClientSaveDto {
    private String name;
    private String email;
    private String phoneNumber;
    private String accountNumber;
}
