package dev.fizlrock.services;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.yaml.snakeyaml.Yaml;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * YamlPulbicHolidayDateProvider
 */
@Slf4j
public class YamlPublicHolidayDateProvider implements PublicHolidayDateProvider {

  private final YamlConfig yamlConfig;

  private LocalDate startPeriod;
  private LocalDate endPeriod;
  private List<LocalDate> holidayDates;

  public YamlPublicHolidayDateProvider(String yamlContent) {
    Yaml yaml = new Yaml();

    yamlConfig = yaml.loadAs(yamlContent, YamlConfig.class);

    startPeriod = LocalDate.parse(yamlConfig.start);
    endPeriod = LocalDate.parse(yamlConfig.end);
    holidayDates = yamlConfig.dates.stream()
        .map(LocalDate::parse)
        .collect(Collectors.toUnmodifiableList());
    log.info("Загружены праздничные даты: {}", holidayDates);
  }

  @Data
  private static class YamlConfig {
    public String start;
    public String end;
    public List<String> dates;
  }

  @Override
  public List<LocalDate> getPublicHolidayDates() {
    return holidayDates;
  }

  @Override
  public boolean hasInfoAboutPeriod(LocalDate start, LocalDate end) {
    return (start.isAfter(startPeriod) || start.equals(startPeriod)) &&
        (end.isBefore(endPeriod) || end.equals(endPeriod));
  }

}
