package Healthcare.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Healthcare.demo.model.ApiResponse;
import Healthcare.demo.model.Doctor;
import Healthcare.demo.service.DoctorService;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173/")
@RequestMapping("/api/doctors")
public class DoctorController {
    private final DoctorService service;

    public DoctorController(DoctorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = service.getAllDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDoctorById(@PathVariable Long id) {
        Doctor doctor = service.getDoctorById(id);
        if (doctor != null) {
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } else {
            ApiResponse response = new ApiResponse("Doctor not found", false);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createDoctor(@RequestBody Doctor doctor) {
        service.createDoctor(doctor);
        ApiResponse response = new ApiResponse("Doctor created successfully", true);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        if (!service.doctorExists(id) || id != doctor.getId()) {
            ApiResponse response = new ApiResponse("Doctor not found", false);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        doctor.setId(id);
        service.updateDoctor(doctor);
        ApiResponse response = new ApiResponse("Doctor updated successfully", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteDoctor(@PathVariable Long id) {
        if (service.doctorExists(id)) {
            service.deleteDoctor(id);
            ApiResponse response = new ApiResponse("Doctor deleted successfully", true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse response = new ApiResponse("Doctor not found", false);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
