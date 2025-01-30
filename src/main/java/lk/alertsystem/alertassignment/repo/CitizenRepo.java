package lk.alertsystem.alertassignment.repo;

import lk.alertsystem.alertassignment.entity.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitizenRepo extends JpaRepository<Citizen , String> {

}
