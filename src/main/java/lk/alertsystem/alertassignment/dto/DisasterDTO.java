package lk.alertsystem.alertassignment.dto;

import jakarta.validation.constraints.NotNull;
import lk.alertsystem.alertassignment.enums.DisasterType;
import lk.alertsystem.alertassignment.enums.UrgencyLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DisasterDTO {
    @NotNull
    private String disasterId;
    private DisasterType disasterType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private Time time;
    private String description;
    private int numOfAffected;
    private UrgencyLevel urgencyLevel;

}
