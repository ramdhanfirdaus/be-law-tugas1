package law.tugas1.controller.api;

import law.tugas1.model.SaveQuestion;
import law.tugas1.service.SaveQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/question/save")
public class SaveQuestionController {

    @Autowired
    SaveQuestionService saveQuestionService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<SaveQuestion>> getSaveQuestion(@PathVariable("id") String id) {
        return ResponseEntity.ok(saveQuestionService.getSaveQuestionById(id));
    }

    @PostMapping(value = "/")
    public ResponseEntity<Object> saveQuestion(@RequestBody Map<String, String> request) {
        saveQuestionService.saveQuestion(request);
        return ResponseEntity.ok("Save Question Berhasil");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteSaveQuestion(@PathVariable("id") String id) {
        saveQuestionService.deleteSaveQuestion(id);
        return ResponseEntity.ok("Delete Save Question Berhasil");
    }
}
