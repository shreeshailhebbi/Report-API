package com.flowable.reportapi.service;

import com.flowable.reportapi.model.CitizenPlan;
import com.flowable.reportapi.model.SearchRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface CitizenPlanService {
    List<String> getAllPlanNames();

    List<String> getAllPlanStatuses();

    List<CitizenPlan> getAllCitizenPlan(SearchRequest searchRequest);

    void generateExcelReport(String fileName,HttpServletResponse httpResponse) throws IOException;

    void generatePdfReport(String fileName,HttpServletResponse httpResponse) throws IOException;
}
