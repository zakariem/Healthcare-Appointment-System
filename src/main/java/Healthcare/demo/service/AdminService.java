package Healthcare.demo.service;

import org.springframework.stereotype.Service;

import Healthcare.demo.model.Admin;
import Healthcare.demo.repo.AdminRepo;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepo repo;

    public AdminService(AdminRepo repo) {
        this.repo = repo;
    }

    public List<Admin> getAllAdmins() {
        return repo.findAll();
    }

    public Admin getAdminById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Admin createAdmin(Admin admin) {
        return repo.save(admin);
    }

    public Admin updateAdmin(Admin admin) {
        return repo.save(admin);
    }

    public void deleteAdmin(Long id) {
        repo.deleteById(id);
    }

    public boolean adminExists(Long id) {
        return repo.existsById(id);
    }

    public Admin getAdminByEmail(String email) {
        return repo.findByEmail(email);
    }

    public boolean validateAdminCredentials(String email, String password) {
        Admin admin = getAdminByEmail(email);
        return admin != null && admin.getPassword().equals(password);
    }
}
