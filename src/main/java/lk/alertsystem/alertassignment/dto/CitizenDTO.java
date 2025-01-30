package lk.alertsystem.alertassignment.dto;

import jakarta.validation.constraints.NotNull;
import lk.alertsystem.alertassignment.enums.District;
import lk.alertsystem.alertassignment.enums.Province;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CitizenDTO implements Serializable {
    private String fullName;
    private String contact;

    @NotNull
    private String email;
    @NotNull
    private String nic;

    private String address;
    private String gramaNiladhariDivision;
    private District district;
    private Province province;


}
