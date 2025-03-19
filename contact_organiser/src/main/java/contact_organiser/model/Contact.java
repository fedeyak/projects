package contact_organiser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class Contact {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
