package peaksoft.driverapp.dto.client;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author Beksultan
 */
@Getter @Setter
public class ClientSaveDto {
    @NotBlank
    private String name;
    @Email
    private String email;
    @NotBlank   // TODO: 28/3/22 write own validation annotation
    private String phoneNumber;
    @NotBlank
    private String accountNumber;

}
