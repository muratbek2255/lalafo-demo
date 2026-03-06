package kg.example.muratkanovmuratbeklalafo.model.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedItemImageDto {
    @JsonProperty("original_url")
    private String imageUrl;
}
