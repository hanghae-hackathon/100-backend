package hackerton.backend.dog.service;

import hackerton.backend.dog.dto.DogRequestDto;
import hackerton.backend.dog.dto.DogResponseDto;
import hackerton.backend.dog.entity.DogEntity;
import hackerton.backend.dog.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DogService {

    @Autowired
    private DogRepository dogRepository;

    public DogEntity saveDog(DogEntity dog) {
        return dogRepository.save(dog);
    }

    public DogEntity getDog(Long id) {
        return dogRepository.findById(id).orElse(null);
    }

    public List<DogEntity> getAllDogs() {
        return dogRepository.findAll();
    }
}