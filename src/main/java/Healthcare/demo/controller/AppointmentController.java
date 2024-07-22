package Healthcare.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Healthcare.demo.dto.AppointmentDTO;
import Healthcare.demo.model.ApiResponse;
import Healthcare.demo.service.AppointmentService;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin("http://localhost:5173/")          

public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public List<AppointmentDTO> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAppointmentById(@PathVariable Long id) {
        AppointmentDTO appointmentDTO = appointmentService.getAppointmentById(id);
        if (appointmentDTO == null) {
            return ResponseEntity.status(404).body(new ApiResponse("Appointment not found for id: " + id, false));
        }
        return ResponseEntity.ok(appointmentDTO);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        try {
            appointmentService.createAppointment(appointmentDTO);
            return ResponseEntity.ok(new ApiResponse("Appointment created successfully", true));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(), false));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateAppointment(@PathVariable Long id, @RequestBody AppointmentDTO appointmentDTO) {
        appointmentDTO.setId(id);
        try {
            appointmentService.updateAppointment(appointmentDTO);
            return ResponseEntity.ok(new ApiResponse("Appointment updated successfully", true));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(), false));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAppointment(@PathVariable Long id) {
        try {
            appointmentService.deleteAppointment(id);
            return ResponseEntity.ok(new ApiResponse("Appointment deleted successfully", true));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(), false));
        }
    }
}