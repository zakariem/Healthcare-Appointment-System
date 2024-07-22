package Healthcare.demo.service;

import org.springframework.stereotype.Service;

import Healthcare.demo.dto.AppointmentDTO;
import Healthcare.demo.model.Appointment;
import Healthcare.demo.repo.AppointmentRepo;
import Healthcare.demo.repo.PatientRepo;
import Healthcare.demo.repo.DoctorRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final AppointmentRepo appointmentRepo;
    private final PatientRepo patientRepo;
    private final DoctorRepo doctorRepo;

    public AppointmentService(AppointmentRepo appointmentRepo, PatientRepo patientRepo, DoctorRepo doctorRepo) {
        this.appointmentRepo = appointmentRepo;
        this.patientRepo = patientRepo;
        this.doctorRepo = doctorRepo;
    }

    public List<AppointmentDTO> getAllAppointments() {
        return appointmentRepo.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public AppointmentDTO getAppointmentById(Long id) {
        Appointment appointment = appointmentRepo.findById(id).orElse(null);
        return appointment != null ? convertToDTO(appointment) : null;
    }

    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) {
        if (!patientRepo.existsById(appointmentDTO.getPatientId())) {
            throw new RuntimeException("Patient not found for id: " + appointmentDTO.getPatientId());
        }
        if (!doctorRepo.existsById(appointmentDTO.getDoctorId())) {
            throw new RuntimeException("Doctor not found for id: " + appointmentDTO.getDoctorId());
        }
        Appointment appointment = convertToEntity(appointmentDTO);
        return convertToDTO(appointmentRepo.save(appointment));
    }

    public AppointmentDTO updateAppointment(AppointmentDTO appointmentDTO) {
        if (!appointmentRepo.existsById(appointmentDTO.getId())) {
            throw new RuntimeException("Appointment not found for id: " + appointmentDTO.getId());
        }
        if (!patientRepo.existsById(appointmentDTO.getPatientId())) {
            throw new RuntimeException("Patient not found for id: " + appointmentDTO.getPatientId());
        }
        if (!doctorRepo.existsById(appointmentDTO.getDoctorId())) {
            throw new RuntimeException("Doctor not found for id: " + appointmentDTO.getDoctorId());
        }
        Appointment appointment = convertToEntity(appointmentDTO);
        return convertToDTO(appointmentRepo.save(appointment));
    }

    public void deleteAppointment(Long id) {
        if (!appointmentRepo.existsById(id)) {
            throw new RuntimeException("Appointment not found for id: " + id);
        }
        appointmentRepo.deleteById(id);
    }

    public boolean appointmentExists(Long id) {
        return appointmentRepo.existsById(id);
    }

    private AppointmentDTO convertToDTO(Appointment appointment) {
        AppointmentDTO dto = new AppointmentDTO();
        dto.setId(appointment.getId());
        dto.setPatientId(appointment.getPatient().getId());
        dto.setDoctorId(appointment.getDoctor().getId());
        dto.setAddress(appointment.getAddress());
        dto.setAppointmentDate(appointment.getAppointmentDate());
        dto.setStatus(appointment.getStatus());
        return dto;
    }

    private Appointment convertToEntity(AppointmentDTO dto) {
        Appointment appointment = new Appointment();
        appointment.setId(dto.getId());
        appointment.setPatient(patientRepo.findById(dto.getPatientId()).orElse(null));
        appointment.setDoctor(doctorRepo.findById(dto.getDoctorId()).orElse(null));
        appointment.setAddress(dto.getAddress());
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setStatus(dto.getStatus());
        return appointment;
    }
}
