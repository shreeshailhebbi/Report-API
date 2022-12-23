package com.flowable.reportapi.util;

import com.flowable.reportapi.model.CitizenPlan;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PdfGenerator {

    public static void generatePdf(List<CitizenPlan> citizenPlans, HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        Font blueFontWithSize18 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        blueFontWithSize18.setSize(18);
        blueFontWithSize18.setColor(Color.BLUE);

        Paragraph paragraph = new Paragraph("Citizen Plans List", blueFontWithSize18);
        paragraph.setAlignment(Element.ALIGN_CENTER);

        document.open();
        document.add(paragraph);

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.5f, 3f, 3f, 1f, 1.5f, 1.5f, 1.5f});
        table.setSpacingBefore(10);

        setTableHeader(table);
        setTableData(table, citizenPlans);

        document.add(table);
        document.close();

    }

    public static void setTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Email", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Gender", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("SSN", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Plan Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Plan Status", font));
        table.addCell(cell);
    }

    public static void setTableData(PdfPTable table, List<CitizenPlan> citizenPlans) {

        for (CitizenPlan citizenPlan : citizenPlans) {
            table.addCell(String.valueOf(citizenPlan.getCitizenId()));
            table.addCell(citizenPlan.getCitizenName());
            table.addCell(citizenPlan.getCitizenEmail());
            table.addCell(citizenPlan.getGender());
            table.addCell(String.valueOf(citizenPlan.getSsn()));
            table.addCell(citizenPlan.getPlanName());
            table.addCell(citizenPlan.getPlanStatus());
        }

    }
}
