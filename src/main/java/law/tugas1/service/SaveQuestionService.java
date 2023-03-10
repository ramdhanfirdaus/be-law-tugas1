package law.tugas1.service;

import law.tugas1.model.SaveQuestion;

import java.util.List;
import java.util.Map;

public interface SaveQuestionService {
    List<SaveQuestion> getSaveQuestionById(String id);

    void deleteSaveQuestion(String id);

    void saveQuestion(Map<String, String> request);
}
