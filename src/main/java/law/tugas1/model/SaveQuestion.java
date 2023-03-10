package law.tugas1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Table(name="save_question")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SaveQuestion {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @NotNull
    @Column(name= "id_question", nullable = false)
    private String idQuestion;

    @NotNull
    @Column(name= "title", nullable = false)
    private String title;

    @NotNull
    @Column(name= "link", nullable = false)
    private String link;

    @NotNull
    @Column(name= "tags", nullable = false)
    private String tags;
}
