package com.flowable.reportapi.service.impl;

import com.flowable.reportapi.constant.ReportConstant;
import com.flowable.reportapi.model.CitizenPlan;
import com.flowable.reportapi.model.SearchRequest;
import com.flowable.reportapi.repository.CitizenPlanRepository;
import com.flowable.reportapi.service.CitizenPlanService;
import com.flowable.reportapi.util.CitizenPlanUtil;
import com.flowable.reportapi.util.ExcelGenerator;
import com.flowable.reportapi.util.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class CitizenPlanServiceImpl implements CitizenPlanService {

    @Autowired
    private CitizenPlanRepository citizenPlanRepository;

    @Override
    public List<String> getAllPlanNames() {
        return citizenPlanRepository.findDistinctByPlanName();
    }

    @Override
    public List<String> getAllPlanStatuses() {
        return citizenPlanRepository.findDistinctByPlanStatus();
    }

    @Override
    public List<CitizenPlan> getAllCitizenPlan(SearchRequest searchRequest) {
        CitizenPlan citizenPlan = new CitizenPlan();
        if (searchRequest.getPlanName() != null && searchRequest.getPlanName() != "") {
            citizenPlan.setPlanName(searchRequest.getPlanName());
        }
        if (searchRequest.getPlanStatus() != null && searchRequest.getPlanStatus() != "") {
            citizenPlan.setPlanStatus(searchRequest.getPlanStatus());
        }
        Example example = Example.of(searchRequest);
        return citizenPlanRepository.findAll(example);
    }

    @Override
    public void generateExcelReport(String fileName, HttpServletResponse httpResponse) throws IOException {
        httpResponse = CitizenPlanUtil.getResponseWithHeaders(fileName, ReportConstant.EXCEL_FILE_EXTENSION, httpResponse);
        ExcelGenerator.generateExcel(citizenPlanRepository.findAll(), httpResponse);
    }

    @Override
    public void generatePdfReport(String fileName, HttpServletResponse httpResponse) throws IOException {
        httpResponse = CitizenPlanUtil.getResponseWithHeaders(fileName, ReportConstant.PDF_FILE_EXTENSION, httpResponse);
        PdfGenerator.generatePdf(citizenPlanRepository.findAll(), httpResponse);

    }
}
