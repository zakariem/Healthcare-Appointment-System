package Healthcare.demo.service;

import org.springframework.stereotype.Service;

import Healthcare.demo.model.Appointment;
import Healthcare.demo.repo.AppointmentRepo;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepo repo;

    public AppointmentService(AppointmentRepo repo) {
        this.repo = repo;
    }

    public List<Appointment> getAllAppointments() {
        return repo.findAll();
    }

    public Appointment getAppointmentById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Appointment createAppointment(Appointment appointment) {
        return repo.save(appointment);
    }

    public Appointment updateAppointment(Appointment appointment) {
        return repo.save(appointment);
    }

    public void deleteAppointment(Long id) {
        repo.deleteById(id);
    }

    public boolean appointmentExists(Long id) {
        return repo.existsById(id);
    }
}
