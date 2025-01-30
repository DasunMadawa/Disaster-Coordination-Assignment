package lk.alertsystem.alertassignment.service.impl;

import lk.alertsystem.alertassignment.dto.CitizenDTO;
import lk.alertsystem.alertassignment.exception.DuplicateException;
import lk.alertsystem.alertassignment.exception.NotFoundException;
import lk.alertsystem.alertassignment.repo.CitizenRepo;
import lk.alertsystem.alertassignment.service.CitizenService;
import lk.alertsystem.alertassignment.util.Mapping;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class CitizenServiceImpl implements CitizenService {
    private final CitizenRepo citizenRepo;
    private final Mapping mapping;

    @Override
    public boolean saveCitizen(CitizenDTO citizenDTO) {
        try {
            citizenRepo.save(mapping.toCitizen(citizenDTO));
            log.info("Citizen Saved");

            return true;
        } catch (Exception e) {
            log.error("Citizen save failed");
            throw new DuplicateException("Citizen Duplicate Details Entered");

        }

    }

    @Override
    public CitizenDTO getCitizen(String nic) {
        if (citizenRepo.existsById(nic)) {
            log.info("Citizen fetch");
            return mapping.toCitizenDTO(citizenRepo.getReferenceById(nic));

        }

        log.error("Citizen fetch abort");
        throw new NotFoundException("Citizen Not Found");

    }

    @Override
    public List<CitizenDTO> getALLCitizen() {
        log.info("customer fetching all");
        return mapping.toCitizenDTOList(citizenRepo.findAll());

    }

    @Override
    public boolean updateCitizen(CitizenDTO citizenDTO) {
        if (citizenRepo.existsById(citizenDTO.getNic())) {
            try {
                citizenRepo.save(mapping.toCitizen(citizenDTO));
                log.info("Citizen updated");
                return true;

            } catch (Exception e) {
                log.error("Citizen update failed");
                throw new DuplicateException("Citizen Duplicate DAta Entered");

            }

        }

        throw new NotFoundException("Citizen Not Found");

    }

    @Override
    public boolean deleteCitizen(String nic) {
        if (citizenRepo.existsById(nic)) {
            citizenRepo.deleteById(nic);
            log.info("Citizen deleted");
            return true;

        }

        log.error("Citizen delete failed");
        throw new NotFoundException("Citizen Not Found");

    }

}
