package Healthcare.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Healthcare.demo.model.Patient;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Long> {
    
}
