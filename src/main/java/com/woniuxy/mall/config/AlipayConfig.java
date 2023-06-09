package com.woniuxy.mall.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "alipay")
public class AlipayConfig {
    private String app_id;
    private String   merchant_private_key;
    private String    alipay_public_key;
    private String  notify_url;
    private String  return_url;
    private String  sign_type;
    private String    charset;
    private String   gatewayUrl;
}
