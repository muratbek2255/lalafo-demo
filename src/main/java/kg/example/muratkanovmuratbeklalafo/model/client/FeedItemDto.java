package kg.example.muratkanovmuratbeklalafo.model.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedItemDto {
    @JsonProperty("title")
    private String title;
    @JsonProperty("price")
    private Integer price;
    @JsonProperty("city_alias")
    private String city;
    @JsonProperty("created_time")
    private Long createdAt;
    @JsonProperty("images")
    private List<FeedItemImageDto> images;
}
