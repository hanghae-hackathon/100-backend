package hackerton.backend.dog.controller;

import hackerton.backend.dog.entity.DogEntity;
import hackerton.backend.dog.service.DogService;
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

    @GetMapping("/healthCheck")
    public String healthCheck() {
        return "hello world";
    }

    @PostMapping("/admin/dog")
    public ResponseEntity<Resource> addDog(@RequestBody DogEntity dog) {
        dogService.saveDog(dog);

        String videoUrl = dog.getVideo();
        String videoFilePath = getVideoFile(videoUrl);
        Path path = Paths.get(videoFilePath);
        Resource resource;
        try {
            resource = new UrlResource(path.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                throw new RuntimeException("Could not read file: " + videoFilePath);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not read file: " + videoFilePath, e);
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("video/mp4"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
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
