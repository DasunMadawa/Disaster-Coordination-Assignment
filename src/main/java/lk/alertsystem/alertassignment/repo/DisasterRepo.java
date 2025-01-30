package lk.alertsystem.alertassignment.repo;

import lk.alertsystem.alertassignment.entity.Disaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisasterRepo extends JpaRepository<Disaster, String> {

}
