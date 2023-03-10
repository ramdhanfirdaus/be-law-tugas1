package law.tugas1.service;

import law.tugas1.model.RolesEnum;
import law.tugas1.model.User;
import law.tugas1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Map;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public String encrypt(String password) {
        var passwordEncoder=new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void register(Map<String, String> request) {
        User user = new User();
        user.setUsername(request.get("username"));
        user.setNama(request.get("nama"));
        user.setPassword(encrypt(request.get("password")));
        user.setRole(RolesEnum.valueOf(request.get("role")));
        userRepository.save(user);
    }

    @Override
    public void updateProfile(Map<String, String> request) {
        Optional<User> user = userRepository.findById(request.get("id"));
        if (user.isPresent()) {
            user.get().setNama(request.get("nama"));
            userRepository.save(user.get());
        }
    }

}
