package hackerton.backend.dog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hackerton.backend.dog.entity.DogEntity;
import hackerton.backend.dog.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dogs")
public class DogController {

    @Autowired
    private DogService dogService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/healthCheck")
    public String healthCheck() {
        return "hello world";
    }

    @PostMapping("/admin/dog")
    public String addDog(@RequestBody DogEntity dog) {
        dogService.saveDog(dog);

        // Python script 호출
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python3", "model.py");
            Process process = processBuilder.start();

            // DogEntity를 JSON 문자열로 변환
            String jsonData = objectMapper.writeValueAsString(dog);

            // Python 스크립트로 JSON 데이터 전달
            try (OutputStream os = process.getOutputStream()) {
                os.write(jsonData.getBytes(StandardCharsets.UTF_8));
                os.flush();
            }

            // Python 스크립트의 결과를 읽기
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
