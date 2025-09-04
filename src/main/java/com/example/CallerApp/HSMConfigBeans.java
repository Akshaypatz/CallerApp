package com.example.CallerApp;

import com.billdesk.paymenthsm.client.HSMClient;
import com.billdesk.paymenthsm.client.internal.config.HSMConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class HSMConfigBeans {

    @Bean
    @Qualifier("hsmKeyBlocks")
    public Map<String, String> hsmKeyBlocks() {
        return Map.of(
                "SBI_VISA_CAVV_GEN", "1CDNE000dbdbdbd,...",
                "BD_HMAC1dbdbdb", "3M2NE000,..."
        );
    }

}
