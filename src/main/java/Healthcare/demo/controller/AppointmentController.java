package Healthcare.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Healthcare.demo.model.ApiResponse;
import Healthcare.demo.model.Appointment;
import Healthcare.demo.service.AppointmentService;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = service.getAllAppointments();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAppointmentById(@PathVariable Long id) {
        Appointment appointment = service.getAppointmentById(id);
        if (appointment != null) {
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        } else {
            ApiResponse response = new ApiResponse("Appointment not found", false);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createAppointment(@RequestBody Appointment appointment) {
        service.createAppointment(appointment);
        ApiResponse response = new ApiResponse("Appointment created successfully", true);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
        if (!service.appointmentExists(id) || id != appointment.getId()) {
            ApiResponse response = new ApiResponse("Appointment not found", false);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        appointment.setId(id);
        service.updateAppointment(appointment);
        ApiResponse response = new ApiResponse("Appointment updated successfully", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAppointment(@PathVariable Long id) {
        if (service.appointmentExists(id)) {
            service.deleteAppointment(id);
            ApiResponse response = new ApiResponse("Appointment deleted successfully", true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse response = new ApiResponse("Appointment not found", false);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
