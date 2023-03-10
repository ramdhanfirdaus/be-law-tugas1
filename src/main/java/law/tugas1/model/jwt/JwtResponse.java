package law.tugas1.model.jwt;

import law.tugas1.model.RolesEnum;
import law.tugas1.model.User;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String id;
    private final String nama;
    private final String username;
    private final RolesEnum role;
    private final String jwttoken;

    public JwtResponse(User user, String jwttoken) {
        this.id = user.getId();
        this.nama = user.getNama();
        this.username = user.getUsername();
        this.role = user.getRole();
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public RolesEnum getRole() {
        return role;
    }

    public String getNama() {
        return nama;
    }
}