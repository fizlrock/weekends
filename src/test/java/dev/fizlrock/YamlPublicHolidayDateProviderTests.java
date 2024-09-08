package dev.fizlrock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import dev.fizlrock.services.PublicHolidayDateProvider;
import dev.fizlrock.services.YamlPublicHolidayDateProvider;

/**
 * YamlPublicHolidayDateProviderTests
 */

public class YamlPublicHolidayDateProviderTests {

  @Test
  void hasInfoAboutPeriodTest() {
    PublicHolidayDateProvider provider = new YamlPublicHolidayDateProvider(
        "---\nstart: 2024-01-01\nend: 2024-02-01\ndates: \n  - 2024-01-11  # Новый год\n...\n");

    assertTrue(provider.hasInfoAboutPeriod(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 2, 1)));
    assertFalse(provider.hasInfoAboutPeriod(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 2, 2)));
    assertFalse(provider.hasInfoAboutPeriod(LocalDate.of(2023, 12, 31), LocalDate.of(2024, 2, 1)));
  }

  @Test
  void holidayDatesParsedTest() {
    PublicHolidayDateProvider provider = new YamlPublicHolidayDateProvider(
        "---\nstart: 2024-01-01\nend: 2024-02-01\ndates: \n  - 2024-01-11  # Новый год\n...\n");
    assertTrue(provider.getPublicHolidayDates().size() == 1);
    assertEquals(LocalDate.of(2024, 01, 11), provider.getPublicHolidayDates().get(0));
  }

}
