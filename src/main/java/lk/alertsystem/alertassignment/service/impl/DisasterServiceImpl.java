package lk.alertsystem.alertassignment.service.impl;

import lk.alertsystem.alertassignment.dto.DisasterDTO;
import lk.alertsystem.alertassignment.exception.DuplicateException;
import lk.alertsystem.alertassignment.exception.NotFoundException;
import lk.alertsystem.alertassignment.repo.DisasterRepo;
import lk.alertsystem.alertassignment.service.DisasterService;
import lk.alertsystem.alertassignment.util.Mapping;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class DisasterServiceImpl implements DisasterService {
    private final DisasterRepo disasterRepo;
    private final Mapping mapping;

    @Override
    public boolean saveDisaster(DisasterDTO disasterDTO) {
        try {
            disasterRepo.save(mapping.toDisaster(disasterDTO));
            return true;
        } catch (Exception e) {
            log.error("Disaster save failed");
            throw new DuplicateException("Duplicate disaster details entered");
        }
    }

    @Override
    public DisasterDTO getDisaster(String id) {
        if (disasterRepo.existsById(id)) {
            log.info("Fetching disaster with ID: {}", id);
            return mapping.toDisasterDTO(disasterRepo.getReferenceById(id));
        }
        log.error("Disaster fetch failed for ID: {}", id);
        throw new NotFoundException("Disaster not found");
    }

    @Override
    public List<DisasterDTO> getAllDisasters() {
        log.info("Fetching all disasters");
        return mapping.toDisasterDTOList(disasterRepo.findAll());
    }

    @Override
    public boolean updateDisaster(DisasterDTO disasterDTO) {
        if (disasterRepo.existsById(disasterDTO.getDisasterId())) {
            try {
                disasterRepo.save(mapping.toDisaster(disasterDTO));
                log.info("Disaster updated");
                return true;
            } catch (Exception e) {
                log.error("Disaster update failed");
                throw new DuplicateException("Duplicate disaster details entered");
            }
        }
        throw new NotFoundException("Disaster not found");
    }

    @Override
    public boolean deleteDisaster(String id) {
        if (disasterRepo.existsById(id)) {
            disasterRepo.deleteById(id);
            log.info("Disaster deleted");
            return true;
        }
        log.error("Disaster deletion failed for ID: {}", id);
        throw new NotFoundException("Disaster not found");
    }
}
