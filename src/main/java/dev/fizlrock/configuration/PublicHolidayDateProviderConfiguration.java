package dev.fizlrock.configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.fizlrock.services.PublicHolidayDateProvider;
import dev.fizlrock.services.YamlPublicHolidayDateProvider;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class PublicHolidayDateProviderConfiguration {

  @Bean
  public PublicHolidayDateProvider getDateProvider() {
    String path = "src/main/resources/publicholiday.yaml";
    String yaml = null;
    try {
      yaml = Files.readString(Path.of(path));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    log.info(yaml);
    return new YamlPublicHolidayDateProvider(yaml);
  }

}
