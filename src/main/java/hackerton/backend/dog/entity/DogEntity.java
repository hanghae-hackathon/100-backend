package hackerton.backend.dog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "dogs")
public class DogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String video; // url타입
    private String image; // url타입
    private String type; // 종
    private String name;
    private int age;
    private int weight;
    private String sex;
    @Column(nullable = true)
    private boolean neutrification; // 중성화
    private int walkingTime; // 산책 시간, 최대 30분
    private String hairLength; // 털길이, long, middle, short
    private boolean isIndependent = false; // 기본 값 설정
    private int childcareDifficulty; // 육아 난이도 0 5 10
}
