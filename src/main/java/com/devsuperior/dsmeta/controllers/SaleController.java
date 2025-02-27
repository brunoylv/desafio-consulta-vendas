package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SalesSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

	@Autowired
	private SaleService saleService;

	@GetMapping("/report")
	public Page<SaleMinDTO> getSalesReport(
			@RequestParam(value = "minDate", required = false) String minDate,
			@RequestParam(value = "maxDate", required = false) String maxDate,
			@RequestParam(value = "name", required = false, defaultValue = "") String sellerName,
			Pageable pageable) {
		return saleService.getSalesReport(minDate, maxDate, sellerName, pageable);
	}

	@GetMapping("/summary")
	public List<SalesSummaryDTO> getSalesSummary(
			@RequestParam(value = "minDate", required = false) String minDate,
			@RequestParam(value = "maxDate", required = false) String maxDate) {
		return saleService.getSalesSummary(minDate, maxDate);
	}
}

