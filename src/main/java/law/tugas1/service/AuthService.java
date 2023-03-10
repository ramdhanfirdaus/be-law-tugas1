package law.tugas1.service;

import law.tugas1.model.User;

import java.util.Map;

public interface AuthService {
    String encrypt(String password);
    User getUserByUsername(String username);
    void register(Map<String, String> request);
    void updateProfile(Map<String, String> request);
}
