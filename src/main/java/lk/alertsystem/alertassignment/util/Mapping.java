package lk.alertsystem.alertassignment.util;

import lk.alertsystem.alertassignment.dto.CitizenDTO;
import lk.alertsystem.alertassignment.dto.DisasterDTO;
import lk.alertsystem.alertassignment.entity.Citizen;
import lk.alertsystem.alertassignment.entity.Disaster;
import lk.alertsystem.alertassignment.enums.DisasterType;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    //    citizen
    public Citizen toCitizen(CitizenDTO citizenDTO) {
        return modelMapper.map(citizenDTO, Citizen.class);

    }

    public CitizenDTO toCitizenDTO(Citizen citizen) {
        return modelMapper.map(citizen, CitizenDTO.class);

    }

    public List<CitizenDTO> toCitizenDTOList(List<Citizen> citizenList) {
        return modelMapper.map(citizenList, new TypeToken<ArrayList<CitizenDTO>>() {}.getType());

    }

    // Disaster mappings
    public Disaster toDisaster(DisasterDTO disasterDTO) {
        return modelMapper.map(disasterDTO, Disaster.class);
    }

    public DisasterDTO toDisasterDTO(Disaster disaster) {
        DisasterDTO disasterDTO = modelMapper.map(disaster, DisasterDTO.class);
        disasterDTO.setCitizenDTO(modelMapper.map(disaster.getCitizen() , CitizenDTO.class));
        return disasterDTO;
    }

    public List<DisasterDTO> toDisasterDTOList(List<Disaster> disasterList) {
        List<DisasterDTO> tempList = new ArrayList<>();

        disasterList.forEach(disaster -> {
            tempList.add(toDisasterDTO(disaster));
        });

        return tempList;
    }




}
