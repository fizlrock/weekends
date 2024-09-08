package dev.fizlrock.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class VacationPayServiceImpl implements VacationPayService {

  private static final double AVERAGE_DAYS_IN_MONTH = 29.3;

  private final VacitionDayCalculator weekendDayCalculator;

  public VacationPayServiceImpl(VacitionDayCalculator weekendDayCalculator) {
    this.weekendDayCalculator = weekendDayCalculator;
  }

  @Override
  public double getPay(double averageMonthlyIncome, long weekendDayCount) {
    if (averageMonthlyIncome < 0)
      throw new IllegalArgumentException("Средней доход в месяц должен быть положительной величиной");
    if (weekendDayCount < 1)
      throw new IllegalArgumentException("Кол-во дней отпуска должно быть больше нуля");

    var dayAnnual = averageMonthlyIncome / AVERAGE_DAYS_IN_MONTH;
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
