package dev.fizlrock.controllers;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.openapitools.api.CalculacteApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import dev.fizlrock.services.WeekendPayService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class WeekendPayController implements CalculacteApi {

	@Autowired
	WeekendPayService service;

	@Override
	public ResponseEntity<Double> getHolidayPay(
			@NotNull @Valid Double salary,
			@Valid Long weekendDayCount,
			@Valid LocalDate vacancyStart,
			@Valid LocalDate vacancyEnd) {

		// не уверен, что тут стоит оставлять логику расчета
		if (vacancyStart != null && vacancyEnd != null)
			return ResponseEntity.ok(service.getPay(salary, vacancyStart, vacancyEnd));

		log.warn("weekend count == null: {}", weekendDayCount==null);

		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getHolidayPay'");
	}

}
