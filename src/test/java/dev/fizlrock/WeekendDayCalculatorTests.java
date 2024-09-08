package dev.fizlrock;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import dev.fizlrock.services.PublicHolidayDateProvider;
import dev.fizlrock.services.VacitionDayCalculator;
import dev.fizlrock.services.VacationDayCalculatorImpl;

/**
 * WeekendDayCalculatorTests
 * 
 * Тестовые случаи:
 * <ol>
 * <li>Дата начала и конца отпуска совпадают - длительность 1 день
 * <li>Дата начала и конца отпуска совпадают и попадают на праздничный день -
 * длительность 0 дней
 * </ol>
 */
public class WeekendDayCalculatorTests {

  @Test
  void startAndEndSameDaySpec() {

    PublicHolidayDateProvider dateProvider = Mockito.mock(PublicHolidayDateProvider.class);
    Mockito.when(dateProvider.hasInfoAboutPeriod(any(), any())).thenReturn(true);
    Mockito.when(dateProvider.getPublicHolidayDates())
        .thenReturn(Collections.emptyList());

    VacitionDayCalculator calculator = new VacationDayCalculatorImpl(dateProvider);

    int result = calculator.countWeekendDays(LocalDate.of(2024, 01, 01), LocalDate.of(2024, 01, 01));
    assertEquals(1, result);
  }

  @Test
  void startAndEndSameDatasAndItHoliday() {

    PublicHolidayDateProvider dateProvider = Mockito.mock(PublicHolidayDateProvider.class);
    Mockito.when(dateProvider.getPublicHolidayDates())
        .thenReturn(List.of(LocalDate.of(2024, 01, 01)));
    Mockito.when(dateProvider.hasInfoAboutPeriod(any(), any())).thenReturn(true);
    VacitionDayCalculator calculator = new VacationDayCalculatorImpl(dateProvider);
    int result = calculator.countWeekendDays(LocalDate.of(2024, 01, 01), LocalDate.of(2024, 01, 01));
    assertEquals(0, result);
  }

  @Test
  void vacationWithoutHolidays() {
    PublicHolidayDateProvider dateProvider = Mockito.mock(PublicHolidayDateProvider.class);
    Mockito.when(dateProvider.hasInfoAboutPeriod(any(), any())).thenReturn(true);
    Mockito.when(dateProvider.getPublicHolidayDates())
        .thenReturn(Collections.emptyList());

    VacitionDayCalculator calculator = new VacationDayCalculatorImpl(dateProvider);

    var actual = calculator.countWeekendDays(LocalDate.of(2022, 02, 01), LocalDate.of(2022, 02, 05));
    assertEquals(5, actual);
  }

  @Test
  void holidayInStartOfVacation(){
    
    PublicHolidayDateProvider dateProvider = Mockito.mock(PublicHolidayDateProvider.class);
    Mockito.when(dateProvider.hasInfoAboutPeriod(any(), any())).thenReturn(true);
    Mockito.when(dateProvider.getPublicHolidayDates())
        .thenReturn(List.of(LocalDate.of(2022, 02, 01)));

    VacitionDayCalculator calculator = new VacationDayCalculatorImpl(dateProvider);

    var actual = calculator.countWeekendDays(LocalDate.of(2022, 02, 01), LocalDate.of(2022, 02, 05));
    assertEquals(4, actual);
  }
  @Test
  void holidayInEndOfVacation(){
    
    PublicHolidayDateProvider dateProvider = Mockito.mock(PublicHolidayDateProvider.class);
    Mockito.when(dateProvider.hasInfoAboutPeriod(any(), any())).thenReturn(true);
    Mockito.when(dateProvider.getPublicHolidayDates())
        .thenReturn(List.of(LocalDate.of(2022, 02, 05)));

    VacitionDayCalculator calculator = new VacationDayCalculatorImpl(dateProvider);

    var actual = calculator.countWeekendDays(LocalDate.of(2022, 02, 01), LocalDate.of(2022, 02, 05));
    assertEquals(4, actual);
  }

}
