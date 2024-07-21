package Healthcare.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Healthcare.demo.model.Appointment;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
    // Custom query methods can be added here if needed
}
