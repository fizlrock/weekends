package dev.fizlrock.controllers;

import java.time.LocalDate;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.openapitools.api.CalculacteApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import dev.fizlrock.services.VacationPayService;

@RestController
public class WeekendPayController implements CalculacteApi {

	@Autowired
	VacationPayService service;

	@Override
	public ResponseEntity<Double> getVacationPay(
			@NotNull @Valid Double salary,
			@Valid Long weekendDayCount,
			@Valid LocalDate vacancyStart,
			@Valid LocalDate vacancyEnd) {

		return ResponseEntity.ok(
				service.getPay(
						salary,
						Optional.ofNullable(weekendDayCount),
						Optional.ofNullable(vacancyStart),
						Optional.ofNullable(vacancyEnd)));
	}

}
