package com.gmail.fedeyak.book_service.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class LoggingConfig {
    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();

        // Показываем query-строку
        filter.setIncludeQueryString(true);

        // Читаем тело запроса
        filter.setIncludePayload(true);

        //Лимитируем размер, до 10k символов
        filter.setMaxPayloadLength(10000);

        // Логируем заголовки
        filter.setIncludeHeaders(true);

        filter.setAfterMessagePrefix("REQUEST DATA : ");

        return filter;
    }
}
