package Healthcare.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Healthcare.demo.model.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {
    // Custom query methods can be added here if needed
    Admin findByEmail(String email);
}
