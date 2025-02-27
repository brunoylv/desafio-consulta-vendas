package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SalesSummaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleMinDTO(s.id, s.amount, s.date, s.seller.name) "
            + "FROM Sale s "
            + "WHERE s.date BETWEEN :minDate AND :maxDate "
            + "AND (:sellerName = '' OR LOWER(s.seller.name) LIKE LOWER(CONCAT('%', :sellerName, '%')))")
    Page<SaleMinDTO> getSalesReport(@Param("minDate") LocalDate minDate,
                                    @Param("maxDate") LocalDate maxDate,
                                    @Param("sellerName") String sellerName,
                                    Pageable pageable);

    @Query("SELECT new com.devsuperior.dsmeta.dto.SalesSummaryDTO(s.seller.name, SUM(s.amount)) " +
            "FROM Sale s " +
            "WHERE s.date BETWEEN :minDate AND :maxDate " +
            "GROUP BY s.seller.name")
    List<SalesSummaryDTO> findSalesSummary(LocalDate minDate, LocalDate maxDate);
}
