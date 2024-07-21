package Healthcare.demo.service;

import org.springframework.stereotype.Service;
import java.util.List;
import Healthcare.demo.model.Patient;
import Healthcare.demo.repo.PatientRepo;

@Service
public class PatientService {

    private final PatientRepo repo;

    public PatientService(PatientRepo repo) {
        this.repo = repo;
    }

    // Read operations
    public List<Patient> getAllPatients() {
        return repo.findAll();
    }

    public Patient getPatientById(Long id) {
        return repo.findById(id).orElse(null);
    }

    // Create new patient
    public Patient createPatient(Patient patient) {
        // You can add more validation logic here if needed
        return repo.save(patient);
    }

    // Update patient
    public Patient updatePatient(Patient patient) {
        if (repo.existsById(patient.getId())) {
            return repo.save(patient);
        } else {
            throw new RuntimeException("Patient not found for id: " + patient.getId());
        }
    }

    // Delete patient
    public void deletePatient(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        } else {
            throw new RuntimeException("Patient not found for id: " + id);
        }
    }

    // Check if a patient exists by ID
    public boolean patientExists(Long id) {
        return repo.existsById(id);
    }

    // Check if a patient exists by email
    public boolean patientExists(String email) {
        return repo.existsByEmail(email);
    }
}
