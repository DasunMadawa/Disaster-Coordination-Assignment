package lk.alertsystem.alertassignment.service;

import lk.alertsystem.alertassignment.dto.DisasterDTO;

import java.util.List;

public interface DisasterService {
    boolean saveDisaster(DisasterDTO disasterDTO);
    DisasterDTO getDisaster(String id);
    List<DisasterDTO> getAllDisasters();
    boolean updateDisaster(DisasterDTO disasterDTO);
    boolean deleteDisaster(String id);

}
