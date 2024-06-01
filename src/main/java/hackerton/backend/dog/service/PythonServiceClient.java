package hackerton.backend.dog.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

@Service
public class PythonServiceClient {

    public String getDescriptionFromVideo(String videoUrl) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python3", "src/main/resources/scripts/model.py");
            Process process = processBuilder.start();

            // Python 스크립트에 입력 데이터를 전달
            OutputStream outputStream = process.getOutputStream();
            outputStream.write(videoUrl.getBytes());
            outputStream.flush();
            outputStream.close();

            // Python 스크립트의 출력을 읽기
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();

            // 단순 String 형태로 반환된 description을 반환
            return result.toString();

        } catch (Exception e) {
            throw new RuntimeException("Failed to get description from Python script", e);
        }
    }
}

