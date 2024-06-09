package com.fmatheus.app.controller.enumerable;

import lombok.Getter;

@Getter
public enum ReportTypeEnum {

    CLIENT_SIMPLE("Client Simple", "Clientes Simplificados", "report", "none"),
    CLIENT_DETAILS("Client Details", "Clientes Detalhado", "report", "subreport");

    private final String type;
    private final String title;
    private final String reportName;
    private final String subreportName;

    ReportTypeEnum(String type, String title, String reportName, String subreportName) {
        this.type = type;
        this.title = title;
        this.reportName = reportName;
        this.subreportName = subreportName;
    }
}
