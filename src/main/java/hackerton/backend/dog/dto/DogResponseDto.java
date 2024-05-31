package hackerton.backend.dog.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DogResponseDto {
    private Long id;
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
    private boolean isIndependent;
    private int childcareDifficulty;
    private String location;
    private String phone;
    private List<String> behavior;
    private List<String> character;
}