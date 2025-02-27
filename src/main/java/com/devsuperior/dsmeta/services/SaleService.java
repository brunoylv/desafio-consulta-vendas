package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import com.devsuperior.dsmeta.dto.SalesSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository saleRepository;

	public Page<SaleMinDTO> getSalesReport(String minDate, String maxDate, String sellerName, Pageable pageable) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate max = (maxDate == null || maxDate.isEmpty()) ? today : LocalDate.parse(maxDate);
		LocalDate min = (minDate == null || minDate.isEmpty()) ? max.minusYears(1) : LocalDate.parse(minDate);
		sellerName = (sellerName == null || sellerName.isEmpty()) ? "" : sellerName;

		return saleRepository.getSalesReport(min, max, sellerName, pageable);
	}


	public List<SalesSummaryDTO> getSalesSummary(String minDate, String maxDate) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate max = (maxDate == null || maxDate.isEmpty()) ? today : LocalDate.parse(maxDate);
		LocalDate min = (minDate == null || minDate.isEmpty()) ? max.minusYears(1) : LocalDate.parse(minDate);

		return saleRepository.findSalesSummary(min, max);
	}
}
