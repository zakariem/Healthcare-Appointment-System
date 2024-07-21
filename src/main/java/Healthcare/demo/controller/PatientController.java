package Healthcare.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Healthcare.demo.model.ApiResponse;
import Healthcare.demo.model.Patient;
import Healthcare.demo.service.PatientService;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173/")
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    // Get all patients
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = service.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    // Get patient by ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getPatientById(@PathVariable Long id) {
        Patient patient = service.getPatientById(id);
        if (patient != null) {
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } else {
            ApiResponse response = new ApiResponse("Patient not found", false);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // Create new patient
    @PostMapping
    public ResponseEntity<ApiResponse> createPatient(@RequestBody Patient patient) {
        // Check if the patient already exists based on other unique fields (like email)
        if (service.patientExists(patient.getEmail())) {
            ApiResponse response = new ApiResponse("Patient already exists", false);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
        service.createPatient(patient);
        ApiResponse response = new ApiResponse("Patient created successfully", true);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Update patient
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        if (!service.patientExists(id) || !id.equals(patient.getId())) {
            ApiResponse response = new ApiResponse("Patient not found", false);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        patient.setId(id);
        service.updatePatient(patient);
        ApiResponse response = new ApiResponse("Patient updated successfully", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Delete patient
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletePatient(@PathVariable Long id) {
        if (service.patientExists(id)) {
            service.deletePatient(id);
            ApiResponse response = new ApiResponse("Patient deleted successfully", true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse response = new ApiResponse("Patient not found", false);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}