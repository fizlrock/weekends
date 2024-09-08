package dev.fizlrock.services;

import java.time.LocalDate;

public interface VacitionDayCalculator {
  /**
   * Расчет кол-ва дней отпуска в отрезке двух дат за вычетом праздничных дней
   * входящих в этот отрезок.
   * <p> Все даты указываются включительно!
   * @param start начало отпуска
   * @param end конец отпуска
   * @return число дней отпуска за вычетом праздников
   */
  public int countWeekendDays(LocalDate start, LocalDate end);
}
