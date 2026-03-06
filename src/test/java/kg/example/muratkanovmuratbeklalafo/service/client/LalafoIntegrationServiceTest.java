package kg.example.muratkanovmuratbeklalafo.service.client;

import kg.example.muratkanovmuratbeklalafo.model.client.FeedItemDto;
import kg.example.muratkanovmuratbeklalafo.model.client.FeedResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LalafoIntegrationServiceTest {
    private WebClient.ResponseSpec mockResponseSpec;

    private LalafoIntegrationService service;

    @BeforeEach
    void setUp() {
        WebClient mockWebClient = mock(WebClient.class);
        var mockUriSpec = mock(WebClient.RequestHeadersUriSpec.class);
        mock(WebClient.RequestHeadersSpec.class);
        mockResponseSpec = mock(WebClient.ResponseSpec.class);

        when(mockWebClient.get()).thenReturn(mockUriSpec);
        when(mockUriSpec.retrieve()).thenReturn(mockResponseSpec);

        service = new LalafoIntegrationService(mockWebClient);
    }

    @Test
    void testGetItems_success() {
        FeedItemDto item = new FeedItemDto();
        item.setTitle("Test item");
        item.setPrice(3000);
        item.setCity("Tokyo");

        FeedResponse response = new FeedResponse();
        response.setItems(List.of(item));

        when(mockResponseSpec.bodyToMono(FeedResponse.class)).thenReturn(Mono.just(response));

        List<FeedItemDto> items = service.getItems();

        assertNotNull(items);
        assertEquals(1, items.size());
        assertEquals("Test item", items.getFirst().getTitle());
    }

    @Test
    void testGetItems_emptyList() {
        FeedResponse response = new FeedResponse();
        response.setItems(List.of());

        when(mockResponseSpec.bodyToMono(FeedResponse.class)).thenReturn(Mono.just(response));

        List<FeedItemDto> items = service.getItems();

        assertNotNull(items);
        assertTrue(items.isEmpty());
    }

    @Test
    void testGetItems_nullResponse() {
        when(mockResponseSpec.bodyToMono(FeedResponse.class)).thenReturn(Mono.empty());

        List<FeedItemDto> items = service.getItems();
        assertNull(items);
    }

    @Test
    void testGetItems_errorResponse() {
        when(mockResponseSpec.bodyToMono(FeedResponse.class))
                .thenReturn(Mono.error(new RuntimeException("Service error")));

        assertThrows(RuntimeException.class, () -> service.getItems());
    }
}