package Healthcare.demo.service;

import org.springframework.stereotype.Service;

import Healthcare.demo.model.Doctor;
import Healthcare.demo.repo.DoctorRepo;

import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepo repo;

    public DoctorService(DoctorRepo repo) {
        this.repo = repo;
    }

    public List<Doctor> getAllDoctors() {
        return repo.findAll();
    }

    public Doctor getDoctorById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Doctor createDoctor(Doctor doctor) {
        return repo.save(doctor);
    }

    public Doctor updateDoctor(Doctor doctor) {
        return repo.save(doctor);
    }

    public void deleteDoctor(Long id) {
        repo.deleteById(id);
    }

    public boolean doctorExists(Long id) {
        return repo.existsById(id);
    }
}
