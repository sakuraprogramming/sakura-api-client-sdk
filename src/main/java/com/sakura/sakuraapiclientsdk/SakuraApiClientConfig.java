package com.sakura.sakuraapiclientsdk;

import com.sakura.sakuraapiclientsdk.client.SakuraClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("sakura.client")
@Data
@ComponentScan
public class SakuraApiClientConfig {

    private String accessKey;

    private String secretKey;

    @Bean
    public SakuraClient sakuraClient() {
        return new SakuraClient(accessKey, secretKey);
    }

}
