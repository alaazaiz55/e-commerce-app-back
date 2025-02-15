package com.MyProject.Ecom.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnalyticsResponse {

    private Long placed;

    private Long shipped;

    private Long delivered;

    private Long currentMonthOrder;

    private Long previousMonthOrder;

    private Long currentMonthEarning;

    private Long previousMonthEarning;

}
