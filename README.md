

## Техническое задание 
Приложение "Калькулятор отпускных".
Микросервис на SpringBoot + Java 11 c одним API:
GET "/calculacte"

Минимальные требования: Приложение принимает твою среднюю зарплату за 12 месяцев и количество дней отпуска - отвечает суммой отпускных, которые придут сотруднику.
Доп. задание: При запросе также можно указать точные дни ухода в отпуск, тогда должен проводиться рассчет отпускных с учётом праздников и выходных.

Проверяться будет чистота кода, структура проекта, название полей\классов, правильность использования паттернов. Желательно написание юнит-тестов, проверяющих расчет.


## Компоненты
WeekendPayAPI 
WeekendPayService - расчет отпускных
WeekendDayCalculator - расчет дней отпуска без учета праздничных дней
PublicHolidayDayDateProvider - источник данных о праздничных днях
YamlPublicHolidayDayDateProvider 

## Особенности
Обработка периода праздники в котором неизвестны? 


## Использованные технологии

- Java 11
- Spring Framework+MVC
- Сборка - maven
- Миграции БД - liquibase
