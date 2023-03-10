package law.tugas1.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Table(name="user_model")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @NotNull
    @Size(max = 50)
    @Column (name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 50)
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotNull
    @Column(name= "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private RolesEnum role;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private Set<SaveQuestion> saveQuestionSet = new HashSet<>();

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private Set<FavoriteTag> favoriteTagSet = new HashSet<>();

}

