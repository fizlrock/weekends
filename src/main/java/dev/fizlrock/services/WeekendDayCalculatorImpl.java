package dev.fizlrock.services;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * WeekendDayCalculatorImpl
 */
@Component
public class WeekendDayCalculatorImpl implements WeekendDayCalculator {

  private final List<LocalDate> holidayDates;
  private final PublicHolidayDateProvider holidayDateProvider;

  public WeekendDayCalculatorImpl(PublicHolidayDateProvider dateProvider) {
    this.holidayDates = dateProvider.getPublicHolidayDates();
    this.holidayDateProvider = dateProvider;
  }

  @Override
  public int countWeekendDays(LocalDate start, LocalDate end) {

    if (start == null)
      throw new IllegalArgumentException("start can't be null");
    if (end == null)
      throw new IllegalArgumentException("end can't be null");

    if (start.isAfter(end))
      throw new IllegalArgumentException("weekend start must be before weekend end");

    var has_info = holidayDateProvider.hasInfoAboutPeriod(start, end);
    if (!has_info)
      throw new IllegalArgumentException("Нет информации о празниках в указанном промежутке");

    int weekendDaysCount = (int) Duration.between(start, end).toDays();

    // не самая эффективная реализация. O(n) где n кол-во праздников в БД
    // а можно за log(n)
    weekendDaysCount -= holidayDates.stream()
        .filter(d -> {
          return d.isAfter(start) && d.isBefore(end) || d.equals(start) || d.equals(end);
        }).count();

    return weekendDaysCount;
  }

}
