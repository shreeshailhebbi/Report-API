package com.flowable.reportapi.util;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CitizenPlanUtil {

    private CitizenPlanUtil() {
    }

    ;

    public static HttpServletResponse getResponseWithHeaders(String fileName, String fileExtension, HttpServletResponse httpResponse) {
        httpResponse.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + fileName + currentDateTime + fileExtension;
        httpResponse.setHeader(headerKey, headerValue);
        return httpResponse;
    }
}
