package hackerton.backend.dog.repository;

import hackerton.backend.dog.entity.DogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<DogEntity, Long> {
}
