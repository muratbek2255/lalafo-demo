package kg.example.muratkanovmuratbeklalafo.service.client;

import kg.example.muratkanovmuratbeklalafo.model.client.FeedItemDto;
import kg.example.muratkanovmuratbeklalafo.model.client.FeedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LalafoIntegrationService {
    private final WebClient webClientWithTimeout;

    public List<FeedItemDto> getItems(){
        return webClientWithTimeout.get()
                .retrieve()
                .bodyToMono(FeedResponse.class)
                .map(FeedResponse::getItems)
                .block();
    }
}
