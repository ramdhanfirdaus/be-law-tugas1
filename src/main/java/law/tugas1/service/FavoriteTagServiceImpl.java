package law.tugas1.service;

import law.tugas1.model.FavoriteTag;
import law.tugas1.model.User;
import law.tugas1.repository.FavoriteTagRepository;
import law.tugas1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteTagServiceImpl implements FavoriteTagService {

    @Autowired
    private FavoriteTagRepository favoriteTagRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public List<FavoriteTag> getFavoriteTagById(String id) {
        return favoriteTagRepository.findFavoriteTagByUser_Id(id);
    }

    @Override
    public void addFavoriteTagById(String id, String data) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            List<String> list = List.of(data.split(";"));
            for (String tag : list) {
                FavoriteTag favoriteTag = new FavoriteTag();
                favoriteTag.setUser(user.get());
                favoriteTag.setTag(tag);
                favoriteTagRepository.save(favoriteTag);
            }
        }
    }

    @Override
    public void updateFavoriteTag(String id, String data) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            List<FavoriteTag> favoriteTags =
                    favoriteTagRepository.findFavoriteTagByUser_Id(user.get().getId());
            favoriteTagRepository.deleteAll(favoriteTags);
        }

        addFavoriteTagById(id, data);
    }
}
