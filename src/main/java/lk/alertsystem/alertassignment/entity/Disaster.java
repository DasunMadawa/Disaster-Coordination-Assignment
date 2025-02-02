package lk.alertsystem.alertassignment.entity;

import jakarta.persistence.*;
import lk.alertsystem.alertassignment.enums.DisasterType;
import lk.alertsystem.alertassignment.enums.UrgencyLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Disaster {
    @Id
    private String disasterId;

    @Enumerated(EnumType.STRING)
    private DisasterType disasterType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private Time time;
    private String description;
    private int numOfAffected;

    @Enumerated(EnumType.STRING)
    private UrgencyLevel urgencyLevel;

    @ManyToOne(fetch = FetchType.EAGER)
    private Citizen citizen;

}
