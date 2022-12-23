package com.flowable.reportapi.resource;

import com.flowable.reportapi.exception.CitizenPlanException;
import com.flowable.reportapi.model.CitizenPlan;
import com.flowable.reportapi.model.SearchRequest;
import com.flowable.reportapi.service.CitizenPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class CitizenPlanResource {
    @Autowired
    private CitizenPlanService citizenPlanService;

    @GetMapping("/plan-names")
    public List<String> getPlanNames() {
        return citizenPlanService.getAllPlanNames();
    }

    @GetMapping("/plan-status")
    public List<String> getPlanStatuses() {
        return citizenPlanService.getAllPlanStatuses();
    }

    @GetMapping("/citizen-plans")
    public List<CitizenPlan> getCitizenPlans(@RequestParam(required = false) SearchRequest searchRequest) {
        return citizenPlanService.getAllCitizenPlan(searchRequest);
    }

    @GetMapping("/citizen-plans/export/{reportType}")
    public void exportCitizenPlans(@PathVariable String reportType, @RequestParam(required = false) String name, HttpServletResponse httpResponse) throws IOException {
        if (name==null) {
            name = "citizen-plans";
        }
        if (reportType.equalsIgnoreCase("excel")) {
            citizenPlanService.generateExcelReport(name, httpResponse);
        } else if (reportType.equalsIgnoreCase("pdf")) {
            citizenPlanService.generatePdfReport(name, httpResponse);
        } else {
            throw new CitizenPlanException("Invalid ReportType "+reportType);
        }
    }
}
