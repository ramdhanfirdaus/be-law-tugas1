package law.tugas1.repository;

import law.tugas1.model.FavoriteTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteTagRepository extends JpaRepository<FavoriteTag, String> {
    List<FavoriteTag> findFavoriteTagByUser_Id(String id);
}
