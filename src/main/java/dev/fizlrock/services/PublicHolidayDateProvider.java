package dev.fizlrock.services;

import java.time.LocalDate;
import java.util.List;

/**
 * PublicHolidayDateProvider
 */
public interface PublicHolidayDateProvider {
  /**
   * Получить список праздничных дней
   * 
   * @return неизменяемый список дат в хронологическом порядке
   */
  public List<LocalDate> getPublicHolidayDates();

  /**
   * Имеет ли это список праздничных дней информацию об указанном периоде
   * 
   * @param start начало отрезка времени
   * @param end   конец отрезка времени
   * @return
   */
  public boolean hasInfoAboutPeriod(LocalDate start, LocalDate end);

}
