package Healthcare.demo.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Healthcare.demo.model.ApiResponse;
import Healthcare.demo.model.Admin;
import Healthcare.demo.service.AdminService;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173/")
@RequestMapping("/api/admins")
public class AdminController {
    
    private final AdminService service;
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    public AdminController(AdminService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = service.getAllAdmins();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAdminById(@PathVariable Long id) {
        Admin admin = service.getAdminById(id);
        if (admin != null) {
            return new ResponseEntity<>(admin, HttpStatus.OK);
        } else {
            ApiResponse response = new ApiResponse("Admin not found", false);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createAdmin(@RequestBody Admin admin) {
        service.createAdmin(admin);
        ApiResponse response = new ApiResponse("Admin created successfully", true);
        logger.info("Created admin with ID: {}", admin.getId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        if (!service.adminExists(id) || id != admin.getId()) {
            ApiResponse response = new ApiResponse("Admin not found", false);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        admin.setId(id);
        service.updateAdmin(admin);
        ApiResponse response = new ApiResponse("Admin updated successfully", true);
        logger.info("Updated admin with ID: {}", id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAdmin(@PathVariable Long id) {
        if (service.adminExists(id)) {
            service.deleteAdmin(id);
            ApiResponse response = new ApiResponse("Admin deleted successfully", true);
            logger.info("Deleted admin with ID: {}", id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse response = new ApiResponse("Admin not found", false);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody Admin admin) {
        Admin existingAdmin = service.getAdminByEmail(admin.getEmail());
        if (existingAdmin != null && existingAdmin.getPassword().equals(admin.getPassword())) {
            ApiResponse response = new ApiResponse("Login successful", true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse response = new ApiResponse("Wrong email or password", false);
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }
}
