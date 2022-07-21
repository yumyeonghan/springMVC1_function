package hello.springmvc.basic;

import lombok.Data;

@Data //@Getter , @Setter , @ToString , @EqualsAndHashCode , @RequiredArgsConstructor 자동 생성
public class HelloData {
    private String username;
    private int age;

}
