package com.example.CallerApp;

import com.billdesk.paymenthsm.client.HSMClient;
import com.billdesk.paymenthsm.client.internal.config.HSMConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@EnableConfigurationProperties(HSMConfig.class)
public class HSMConfigBeans {

    @Bean
    public Map<String, String> keyBlocks() {
        return Map.of(
                "SBI_VISA_CAVV_GEN", "1CDNE000,...",
                "BD_HMAC1", "3M2NE000,..."
        );
    }

    @Bean
    public HSMClient hsmClient(HSMConfig config, Map<String, String> keyBlocks) {
        return new HSMClient(config, keyBlocks);
    }
}
