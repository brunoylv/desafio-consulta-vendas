package com.devsuperior.dsmeta.dto;

public class SalesSummaryDTO {
    private String sellerName;
    private Double totalSales;

    public SalesSummaryDTO() {
    }

    public SalesSummaryDTO(String sellerName, Double totalSales) {
        this.sellerName = sellerName;
        this.totalSales = totalSales;
    }

    public String getSellerName() {
        return sellerName;
    }

    public Double getTotalSales() {
        return totalSales;
    }
}
