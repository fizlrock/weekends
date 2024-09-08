package dev.fizlrock.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class WeekendPayServiceImpl implements WeekendPayService {

  private final WeekendDayCalculator weekendDayCalculator;

  public WeekendPayServiceImpl(WeekendDayCalculator weekendDayCalculator) {
    this.weekendDayCalculator = weekendDayCalculator;
  }

  @Override
  public double getPay(double averageMonthlyIncome, long weekendDayCount) {
    if (averageMonthlyIncome < 0)
      throw new IllegalArgumentException("Средней доход в месяц должен быть положительной величиной");
    if (weekendDayCount < 1)
      throw new IllegalArgumentException("Кол-во дней отпуска должно быть больше нуля");

    var dayAnnual = averageMonthlyIncome / 29.3;
    var weekendPay = dayAnnual * weekendDayCount;
    return weekendPay;
  }

  @Override
  public double getPay(double averageAnnual, LocalDate weekendStart, LocalDate weekendEnd) {
    int weekendDayCount = weekendDayCalculator.countWeekendDays(weekendStart, weekendEnd);

    return getPay(averageAnnual, weekendDayCount);
  }

  @Override
  public double getPay(double averageMountlyIncome, Optional<Long> weekendDayCount, Optional<LocalDate> weekendStart,
      Optional<LocalDate> weekendEnd) {
    if (weekendStart.isPresent() & weekendEnd.isPresent())
      return getPay(averageMountlyIncome, weekendStart.get(), weekendEnd.get());
    else if (weekendDayCount.isPresent())
      return getPay(averageMountlyIncome, weekendDayCount.get());
    else
      throw new IllegalArgumentException("You must specify vacation dates or duration");

  }

}
