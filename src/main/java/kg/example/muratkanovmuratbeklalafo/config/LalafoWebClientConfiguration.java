package kg.example.muratkanovmuratbeklalafo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;


@Configuration
@RequiredArgsConstructor
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
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
