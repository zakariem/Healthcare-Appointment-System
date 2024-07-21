package Healthcare.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Healthcare.demo.model.Doctor;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long> {
    // Custom query methods can be added here if needed
}
