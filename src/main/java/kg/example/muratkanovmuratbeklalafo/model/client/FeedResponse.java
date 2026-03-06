package kg.example.muratkanovmuratbeklalafo.model.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedResponse {
    @JsonProperty("items")
    private List<ItemDto> items;
}
