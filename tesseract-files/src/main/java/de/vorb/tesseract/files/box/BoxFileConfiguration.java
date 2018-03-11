package de.vorb.tesseract.files.box;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BoxFileConfiguration {

    @Bean
    public BoxFileParser boxFileParser() {
        return new DefaultBoxFileParser();
    }

}
