package law.tugas1.service;

import law.tugas1.model.SaveQuestion;
import law.tugas1.model.User;
import law.tugas1.repository.SaveQuestionRepository;
import law.tugas1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SaveQuestionServiceImpl implements SaveQuestionService {

    @Autowired
    private SaveQuestionRepository saveQuestionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<SaveQuestion> getSaveQuestionById(String id) {
        Optional<User> user = userRepository.findById(id);
        List<SaveQuestion> saveQuestionList = new ArrayList<>();

        if (user.isPresent()) {
            saveQuestionList = saveQuestionRepository.findAllSaveQuestionByUser(user.get());
        }

        return saveQuestionList;
    }

    @Override
    public void deleteSaveQuestion(String id) {
        SaveQuestion saveQuestion = saveQuestionRepository.findSaveQuestionById(id);
        saveQuestionRepository.delete(saveQuestion);
    }

    @Override
    public void saveQuestion(Map<String, String> request) {
        Optional<User> user = userRepository.findById(request.get("userId"));

        if (user.isPresent()) {
            SaveQuestion saveQuestion = new SaveQuestion();
            saveQuestion.setIdQuestion(request.get("questionId"));
            saveQuestion.setUser(user.get());
            saveQuestion.setTitle(request.get("title"));
            saveQuestion.setLink(request.get("link"));
            saveQuestion.setTags(request.get("tags"));
            saveQuestionRepository.save(saveQuestion);
        }
    }
}
