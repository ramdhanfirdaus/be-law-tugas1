package law.tugas1.repository;

import law.tugas1.model.SaveQuestion;
import law.tugas1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaveQuestionRepository extends JpaRepository<SaveQuestion, String> {
    List<SaveQuestion> findAllSaveQuestionByUser(User user);
    SaveQuestion findSaveQuestionById(String id);
}
