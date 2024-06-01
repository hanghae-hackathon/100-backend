package hackerton.backend.dog.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DogRequestDto {
    private String video;
    private String image;
    private String type;
    private String name;
    private int age;
    private int weight;
    private String sex;
    private boolean isNeutrification;
    private int walkingTime;
    private String hairLength;
    private int childcareDifficulty;
}