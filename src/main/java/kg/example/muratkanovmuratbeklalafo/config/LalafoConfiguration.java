package kg.example.muratkanovmuratbeklalafo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("lalafo")
@Data
public class LalafoConfiguration {
    private String url;
    private String userAgent;
    private String headerAccept;
    private String device;
    private String countryId;
    private String language;
    private String requestId;
    private Integer timeout;
}
