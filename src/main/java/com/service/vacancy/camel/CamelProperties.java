package com.service.vacancy.camel;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CamelProperties {

    @Value("${mail.from:}")
    public String fromEmail;

    @Value("${mail.smtp.host:}")
    public String smtpHost;

    @Value("${mail.smtp.port:465}")
    public int smtpPort;

    @Value("${mail.smtp.username:}")
    public String smtpUsername;

    @Value("${mail.smtp.password:}")
    public String smtpPassword;

    @Value("${mail.smtp.ssl:false}")
    public boolean useSsl;

    @Value("${mail.smtp.auth:true}")
    public boolean useAuth;

}
