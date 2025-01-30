package lk.alertsystem.alertassignment.controller;

import jakarta.annotation.security.RolesAllowed;
import lk.alertsystem.alertassignment.dto.CitizenDTO;
import lk.alertsystem.alertassignment.exception.DuplicateException;
import lk.alertsystem.alertassignment.exception.NotFoundException;
import lk.alertsystem.alertassignment.service.CitizenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/citizen")
@CrossOrigin
@RequiredArgsConstructor
public class CitizenController {
    private final CitizenService citizenService;

    @GetMapping("/health")
    public String citizenHealthCheck() {
        return "Citizen in good health";

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CitizenDTO> saveCitizen(@RequestBody CitizenDTO citizenDTO) {
        try {
            citizenService.saveCitizen(citizenDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(citizenDTO);

        } catch (DuplicateException duplicateException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @RolesAllowed("ADMIN")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CitizenDTO>> getAllCitizen() {
        return ResponseEntity.ok(citizenService.getALLCitizen());

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "{nic}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CitizenDTO> getCitizen(@PathVariable("nic") String nic) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(citizenService.getCitizen(nic));

        } catch (NotFoundException notFoundException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(value = {"{nic}"} , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CitizenDTO> updateCitizen(@RequestBody CitizenDTO citizenDTO , @PathVariable("nic") String nic) {
        System.out.println(nic);
        System.out.println(citizenDTO);
        try {
            citizenDTO.setNic(nic);
            citizenService.updateCitizen(citizenDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(citizenDTO);

        } catch (NotFoundException | DuplicateException exception ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @RolesAllowed("ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "{nic}")
    public ResponseEntity<CitizenDTO> deleteCitizen(@PathVariable("nic") String nic) {
        try {
            citizenService.deleteCitizen(nic);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

        } catch (NotFoundException notFoundException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }

    }


}
