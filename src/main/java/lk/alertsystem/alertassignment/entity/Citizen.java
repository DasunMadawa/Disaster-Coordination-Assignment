package lk.alertsystem.alertassignment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lk.alertsystem.alertassignment.enums.District;
import lk.alertsystem.alertassignment.enums.Province;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Citizen {
    private String fullName;
    private String contact;

    @Email
    private String email;

    @Column(unique = true)
    @Id
    private String nic;
    private String address;
    private String gramaNiladhariDivision;

    @Enumerated(EnumType.STRING)
    private District district;

    @Enumerated(EnumType.STRING)
    private Province province;

}
