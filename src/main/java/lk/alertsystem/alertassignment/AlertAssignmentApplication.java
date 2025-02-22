package lk.alertsystem.alertassignment;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AlertAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlertAssignmentApplication.class, args);
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();

    }

}
