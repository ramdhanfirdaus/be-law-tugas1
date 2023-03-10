package law.tugas1.controller.api;

import law.tugas1.service.QuestionService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping(value = "/title/{title}")
    public ResponseEntity<Object> getAllQuestionByTitle(@PathVariable("title") String title) {
        JSONObject mapResponse = questionService.requestQuestionByTitle(title);
        return ResponseEntity.ok(mapResponse.toString());
    }

    @GetMapping(value = "/tags/{id}")
    public ResponseEntity<Object> getAllQuestionByTags(@PathVariable("id") String id) {
        JSONObject mapResponse = questionService.requestQuestionByTags(id);
        return ResponseEntity.ok(mapResponse.toString());
    }

}
