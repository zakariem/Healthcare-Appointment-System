package Healthcare.demo.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
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

    public Optional<Patient> getPatientById(Long id) {
        return repo.findById(id);
    }

    // Create new patient
    public Patient createPatient(Patient patient) {
        return repo.save(patient);
    }

    // Update patient
    public Patient updatePatient(Patient patient) {
        return repo.save(patient);
    }

    // Delete patient
    public void deletePatient(Long id) {
        repo.deleteById(id);
    }

    // Check if a patient exists
    public boolean patientExists(Long id) {
        return repo.existsById(id);
    }
}
