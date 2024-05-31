package hackerton.backend.dog.controller;

import hackerton.backend.dog.entity.DogEntity;
import hackerton.backend.dog.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dogs")
public class DogController {

    @Autowired
    private DogService dogService;

    @PostMapping("/admin/dog")
    public String addDog(@RequestBody DogEntity dog) {
        dogService.saveDog(dog);
        // Python script 호출
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python3", "model.py", dog.toString());
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @PostMapping("/search")
    public Map<String, Integer> searchDogs(@RequestBody DogEntity dog) {
        // AI 모델 호출하여 검색 작업 수행 (Python 스크립트 호출해야함)
        int requestId = 1; // 요청 ID 생성 로직
        return Collections.singletonMap("requestId", requestId);
    }

    @GetMapping("/search/result/{id}")
    public DogEntity getSearchResult(@PathVariable Long id) {
        // AI 모델 호출 결과 반환
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
