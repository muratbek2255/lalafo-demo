package kg.example.muratkanovmuratbeklalafo.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;


@Configuration
@RequiredArgsConstructor
@Slf4j
public class LalafoWebClientConfiguration {

    private final LalafoConfiguration lalafoConfiguration;

    @Bean
    public WebClient webClientWithTimeout() {
        HttpClient httpClient = HttpClient.create()
                .option(io.netty.channel.ChannelOption.CONNECT_TIMEOUT_MILLIS, lalafoConfiguration.getTimeout())
                .responseTimeout(Duration.ofMillis(lalafoConfiguration.getTimeout()));

        return WebClient.builder()
                .baseUrl(lalafoConfiguration.getUrl())
                .defaultHeader("User-Agent", lalafoConfiguration.getUserAgent())
                .defaultHeader("Accept", lalafoConfiguration.getHeaderAccept())
                .defaultHeader("device", lalafoConfiguration.getDevice())
                .defaultHeader("country-id", lalafoConfiguration.getCountryId())
                .defaultHeader("language", lalafoConfiguration.getLanguage())
                .defaultHeader("request-id", lalafoConfiguration.getRequestId())
                .filter(logRequest())
                .filter(logResponse())
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            return Mono.just(clientRequest);
        });
    }

    private ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            log.info("Response status: {}", clientResponse.statusCode());
            return Mono.just(clientResponse);
        });
    }
}
