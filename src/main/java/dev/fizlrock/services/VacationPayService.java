package dev.fizlrock.services;

import java.time.LocalDate;
import java.util.Optional;

/**
 * <p>
 * Сервис для подсчета компенсации за отпуск.
 * <p>
 * Поддерживаеться вычисление в двух режимах:
 * <ol>
 * <li>Под среднегодовой заработной плате и числу дней в отпуске.
 * <p>
 * В таком случает считается, что в даты прохождения отпуска праздников не
 * проходило.
 * 
 * <li>Под сред. ЗП и датам отпуска.
 * <p>
 * В этом случает расчет осуществляеться с учетом праздничных дней
 * </ol>
 * <p>
 * Отпускные выплачиваються за все дни отпуска(даже если часть из них - суббота
 * и воскресенье), но невыплачиваются за официальные праздничные дни.
 * <p>
 * 
 * Среднедневной заработок = Доход за расчетный период / Количество отработанных
 * дней *
 */
public interface VacationPayService {
  /**
   * @param averageMonthlyIncome - средняя зарплата в месяц за 12 месяцев
   * @param weekendDayCount      - кол-во дней отпуска
   * @return
   */
  public double getPay(double averageMonthlyIncome, long weekendDayCount);

  /**
   * @param averageMountlyIncome - средняя зарплата за 12 месяцев
   * @param weekendStart         - дата начала отпуска (включительно)
   * @param weekendEnd           - дата конца отпуска (включительно)
   * @return
   */
  public double getPay(double averageMountlyIncome, LocalDate weekendStart, LocalDate weekendEnd);

  public double getPay(double averageMountlyIncome, Optional<Long> weekendDayCount, Optional<LocalDate> weekendStart,
      Optional<LocalDate> weekendEnd);

}
