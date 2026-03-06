package kg.example.muratkanovmuratbeklalafo.service;

import kg.example.muratkanovmuratbeklalafo.model.view.FeedItemViewModel;
import kg.example.muratkanovmuratbeklalafo.service.client.LalafoIntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedService {

    private final FeedItemMapper feedItemMapper;
    private final LalafoIntegrationService lalafoIntegrationService;

    public List<FeedItemViewModel> getFeedItems() {
        return lalafoIntegrationService
                .getItems()
                .stream()
                .map(feedItemMapper::toViewModel)
                .toList();
    }
}
