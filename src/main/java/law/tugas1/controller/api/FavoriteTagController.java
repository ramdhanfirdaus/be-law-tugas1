package law.tugas1.controller.api;

import law.tugas1.service.FavoriteTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/fav")
public class FavoriteTagController {

    @Autowired
    FavoriteTagService favoriteTagService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getFavoriteTag(@PathVariable("id") String id) {
        return ResponseEntity.ok(favoriteTagService.getFavoriteTagById(id));
    }

    @PostMapping(value = "/")
    public ResponseEntity<Object> postFavoriteTag(@RequestBody Map<String, String> request) {
        favoriteTagService.addFavoriteTagById(request.get("id"), request.get("data"));
        return ResponseEntity.ok("Favorite Tags Berhasil ditambahkan");
    }

    @PatchMapping(value = "")
    public ResponseEntity<Object> updateFavoriteTag(@RequestBody Map<String, String> request) {
        favoriteTagService.updateFavoriteTag(request.get("id"), request.get("data"));
        return ResponseEntity.ok("Favorite Tags Berhasil di-update");
    }

}
