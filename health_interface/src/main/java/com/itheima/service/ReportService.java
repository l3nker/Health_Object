package com.itheima.service;

import java.util.List;
import java.util.Map;

public interface ReportService {
    List<String> searchMealNames();

    List<Map> searchMealCount();

    Map<String, Object> getBusinessReportData() throws Exception;
}
