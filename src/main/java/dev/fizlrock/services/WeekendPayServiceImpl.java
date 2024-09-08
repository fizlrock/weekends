package dev.fizlrock.services;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public class WeekendPayServiceImpl implements WeekendPayService {

  private final WeekendDayCalculator weekendDayCalculator;

  public WeekendPayServiceImpl(WeekendDayCalculator weekendDayCalculator) {
    this.weekendDayCalculator = weekendDayCalculator;
  }

  @Override
  public double getPay(double averageMonthlyIncome, int weekendDayCount) {
    if (averageMonthlyIncome < 0)
      throw new IllegalArgumentException("Средней доход в месяц должен быть положительной величиной");
    if (weekendDayCount < 1)
      throw new IllegalArgumentException("Кол-во дней отпуска должно быть больше нуля");

    var dayAnnual = averageMonthlyIncome * 29.3;
    var weekendPay = dayAnnual * weekendDayCount;
    return weekendPay;
  }

  @Override
  public double getPay(double averageAnnual, LocalDate weekendStart, LocalDate weekendEnd) {
    int weekendDayCount = weekendDayCalculator.countWeekendDays(weekendStart, weekendEnd);

    return getPay(averageAnnual, weekendDayCount);
  }

}
