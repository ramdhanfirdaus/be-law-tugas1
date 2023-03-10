package law.tugas1.service;

import law.tugas1.model.FavoriteTag;

import java.util.List;

public interface FavoriteTagService {
    List<FavoriteTag> getFavoriteTagById(String id);

    void addFavoriteTagById(String id, String data);

    void updateFavoriteTag(String id, String data);
}
