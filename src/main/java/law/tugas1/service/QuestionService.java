package law.tugas1.service;

import org.json.JSONObject;

public interface QuestionService {
    JSONObject requestQuestionByTitle(String title);
    JSONObject requestQuestionByTags(String id);
}
