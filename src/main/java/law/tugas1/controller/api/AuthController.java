package law.tugas1.controller.api;

import law.tugas1.model.User;
import law.tugas1.model.jwt.JwtRequest;
import law.tugas1.model.jwt.JwtResponse;
import law.tugas1.security.JwtTokenUtil;
import law.tugas1.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/signin")
    public ResponseEntity<Object> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final var userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        User user = authService.getUserByUsername(authenticationRequest.getUsername());

        return ResponseEntity.ok(new JwtResponse(user, token));
    }

    private void authenticate(String username, String password) throws DisabledException, BadCredentialsException {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new DisabledException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<Object> register(@RequestBody Map<String, String> request) {
        authService.register(request);
        return ResponseEntity.ok("Register Berhasil");
    }

    @PatchMapping(value = "/update")
    public ResponseEntity<Object> updateProfile(@RequestBody Map<String, String> request) {
        authService.updateProfile(request);
        return ResponseEntity.ok("Update Profile Berhasil");
    }

}
