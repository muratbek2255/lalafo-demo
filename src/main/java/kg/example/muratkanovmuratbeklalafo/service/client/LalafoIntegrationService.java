package kg.example.muratkanovmuratbeklalafo.service.client;

import kg.example.muratkanovmuratbeklalafo.config.LalafoConfiguration;
import kg.example.muratkanovmuratbeklalafo.model.client.FeedItemDto;
import kg.example.muratkanovmuratbeklalafo.model.client.FeedResponse;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@Data
public class LalafoIntegrationService {
    private final WebClient webClientWithTimeout;
    private final LalafoConfiguration lalafoConfiguration;

    public List<FeedItemDto> getItems(){
        return webClientWithTimeout.get()
                .retrieve()
                .bodyToMono(FeedResponse.class)
                .map(FeedResponse::getItems)
                .block();
    }
}
