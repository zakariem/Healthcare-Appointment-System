package Healthcare.demo.dto;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
public class AppointmentDTO {
    private Long id;
    private Long patientId;
    private Long doctorId;
    private String address;
    private LocalDateTime appointmentDate;
    private String status;
}
