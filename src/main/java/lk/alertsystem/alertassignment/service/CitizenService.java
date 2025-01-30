package lk.alertsystem.alertassignment.service;

import lk.alertsystem.alertassignment.dto.CitizenDTO;

import java.util.List;

public interface CitizenService {
    boolean saveCitizen(CitizenDTO citizenDTO);
    CitizenDTO getCitizen(String nic);
    List<CitizenDTO> getALLCitizen();
    boolean updateCitizen(CitizenDTO citizenDTO);
    boolean deleteCitizen(String nic);

}
