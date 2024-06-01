package hackerton.backend.dog.controller;

import hackerton.backend.dog.entity.DogEntity;
import hackerton.backend.dog.service.DogService;
import hackerton.backend.dog.service.PythonServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dogs")
public class DogController {

    @Autowired
    private DogService dogService;

    @Autowired
    private PythonServiceClient pythonServiceClient;

    @GetMapping("/healthCheck")
    public String healthCheck() {
        return "hello world";
    }

    //등록하고 비디오를 python input
    @PostMapping("/admin/dog")
    public ResponseEntity<DogEntity> addDog(@RequestBody DogEntity dog) {
        // Python 서비스 호출하여 description 가져오기
        String description = pythonServiceClient.getDescriptionFromVideo(dog.getVideo());

        // description 설정
        dog.setDescription(description);

        // DogEntity 저장
        DogEntity savedDog = dogService.saveDog(dog);

        return ResponseEntity.ok(savedDog);
    }

    private String getVideoFile(String videoUrl) {
        if (videoUrl.endsWith("/video1")) {
            return "path/to/local/video1.mp4";
        } else if (videoUrl.endsWith("/video2")) {
            return "path/to/local/video2.mp4";
        } else if (videoUrl.endsWith("/video3")) {
            return "path/to/local/video3.mp4";
        } else if (videoUrl.endsWith("/video4")) {
            return "path/to/local/video4.mp4";
        } else if (videoUrl.endsWith("/video5")) {
            return "path/to/local/video5.mp4";
        }
        return "default_video_path";
    }

    @PostMapping("/search")
    public Map<String, Integer> searchDogs(@RequestBody DogEntity dog) {
        int requestId = 1; // 요청 ID 생성 로직
        return Collections.singletonMap("requestId", requestId);
    }

    @GetMapping("/search/result/{id}")
    public DogEntity getSearchResult(@PathVariable Long id) {
        return dogService.getDog(id);
    }

    @GetMapping("/{id}")
    public DogEntity getDog(@PathVariable Long id) {
        return dogService.getDog(id);
    }

    @GetMapping
    public List<DogEntity> getAllDogs() {
        return dogService.getAllDogs();
    }
}
