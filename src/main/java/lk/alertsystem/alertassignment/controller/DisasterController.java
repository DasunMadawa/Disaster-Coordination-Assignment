package lk.alertsystem.alertassignment.controller;

import jakarta.annotation.security.RolesAllowed;
import lk.alertsystem.alertassignment.dto.DisasterDTO;
import lk.alertsystem.alertassignment.exception.DuplicateException;
import lk.alertsystem.alertassignment.exception.NotFoundException;
import lk.alertsystem.alertassignment.service.DisasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/disaster")
@CrossOrigin
@RequiredArgsConstructor
public class DisasterController {
    private final DisasterService disasterService;

    @GetMapping("/health")
    public String disasterHealthCheck() {
        return "Disaster service is running";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DisasterDTO> saveDisaster(@RequestBody DisasterDTO disasterDTO) {
        System.out.println(disasterDTO.getDate());
        try {
            disasterService.saveDisaster(disasterDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(disasterDTO);
        } catch (DuplicateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @RolesAllowed("ADMIN")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DisasterDTO>> getAllDisasters() {
        return ResponseEntity.ok(disasterService.getAllDisasters());
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DisasterDTO> getDisaster(@PathVariable("id") String id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(disasterService.getDisaster(id));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DisasterDTO> updateDisaster(@RequestBody DisasterDTO disasterDTO, @PathVariable("id") String id) {
        try {
            disasterDTO.setDisasterId(id);
            disasterService.updateDisaster(disasterDTO);
            return ResponseEntity.status(HttpStatus.OK).body(disasterDTO);
        } catch (NotFoundException | DuplicateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @RolesAllowed("ADMIN")
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteDisaster(@PathVariable("id") String id) {
        try {
            disasterService.deleteDisaster(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
