package com.flowable.reportapi.util;

import com.flowable.reportapi.model.CitizenPlan;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ExcelGenerator {

    public static void generateExcel(List<CitizenPlan> list, HttpServletResponse response) throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook();) {
            XSSFSheet sheet = workbook.createSheet("Citizen Plans Info");
            XSSFRow headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Id");
            headerRow.createCell(1).setCellValue("Name");
            headerRow.createCell(2).setCellValue("Email");
            headerRow.createCell(3).setCellValue("Gender");
            headerRow.createCell(4).setCellValue("SSN");
            headerRow.createCell(5).setCellValue("Plan Name");
            headerRow.createCell(6).setCellValue("Plan Status");

            int rowIndex = 1;
            for (CitizenPlan citizenPlan : list) {
                XSSFRow dataRow = sheet.createRow(rowIndex);
                dataRow.createCell(0).setCellValue(citizenPlan.getCitizenId());
                dataRow.createCell(1).setCellValue(citizenPlan.getCitizenName());
                dataRow.createCell(2).setCellValue(citizenPlan.getCitizenEmail());
                dataRow.createCell(3).setCellValue(citizenPlan.getGender());
                dataRow.createCell(4).setCellValue(citizenPlan.getSsn());
                dataRow.createCell(5).setCellValue(citizenPlan.getPlanName());
                dataRow.createCell(6).setCellValue(citizenPlan.getPlanStatus());
                rowIndex++;
            }
            OutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        }


    }
}
