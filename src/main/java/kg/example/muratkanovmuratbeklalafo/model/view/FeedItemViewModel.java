package kg.example.muratkanovmuratbeklalafo.model.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedItemViewModel {
    private String title;
    private Integer price;
    private String city;
    private LocalDateTime createdAt;
    private String imageUrl;
}
