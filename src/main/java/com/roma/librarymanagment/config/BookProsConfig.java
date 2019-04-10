package com.roma.librarymanagment.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:libraryconfig.properties")
@ConfigurationProperties(prefix = "book")
@Setter
@Getter
@Data
public class BookProsConfig {
    private String displayPublishers;
    private String displayAuthors;
    private String displayCategories;
    private String displayBook;
    private String displayBooks;
}
