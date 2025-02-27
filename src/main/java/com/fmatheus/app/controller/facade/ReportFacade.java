package com.fmatheus.app.controller.facade;

import com.fmatheus.app.config.properties.ReportProperties;
import com.fmatheus.app.controller.dto.report.client.Header;
import com.fmatheus.app.controller.dto.report.client.ReportSimple;
import com.fmatheus.app.controller.enumerable.ReportTypeEnum;
import com.fmatheus.app.controller.exception.JasperException;
import com.fmatheus.app.controller.util.AppUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class ReportFacade {
    private static final String JRXML = ".jrxml";
    private static final String JASPER = ".jasper";
    private static final String SEPARATOR = File.separator;
    private static final Logger log = LoggerFactory.getLogger(ReportFacade.class);
    private String pathReport;
    private String pathSubReport;
    private String pathHeader;
    private String pathLogo;
    private String pathLogoCoffee;
    private String reportHeader;
    private final ReportProperties reportProperties;

    public void createReport(HttpServletResponse response, Collection<ReportSimple> reports, ReportTypeEnum type) throws JRException, IOException {
        this.initPath(type);
        JRDataSource dataSource = new JRBeanCollectionDataSource(reports);
        this.jasperPrint(type, response, dataSource);
    }


    private void initPath(ReportTypeEnum type) {
        String classPath = System.getProperty("user.dir");
        this.pathReport = classPath.concat(this.path(type));
        this.pathSubReport = classPath.concat(this.path(type));
        this.pathHeader = classPath.concat(this.reportProperties.getPathJasper());
        this.pathLogo = classPath.concat(this.reportProperties.getPathLogo());
        this.pathLogoCoffee = classPath.concat(this.reportProperties.getPathLogoCoffee());
        this.reportHeader = this.reportProperties.getReportHeader();
        this.jasperCompile(type);
    }

    private String path(ReportTypeEnum type) {
        return switch (type) {
            case CLIENT_SIMPLE -> this.reportProperties.getPathClientSimple();
            case CLIENT_DETAILS -> this.reportProperties.getPathClientDetails();
        };
    }


    private void jasperPrint(ReportTypeEnum type, HttpServletResponse response, JRDataSource dataSource) throws JRException, IOException {

        var reportJasper = this.pathReport.concat(SEPARATOR).concat(type.getReportName()).concat(JASPER);
        Map<String, Object> reportParam = this.parametersReport(type);
        Thread.currentThread().getContextClassLoader();

        JasperPrint printOut = JasperFillManager.fillReport(reportJasper, reportParam, dataSource);
        byte[] bytes = JasperExportManager.exportReportToPdf(printOut);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline");
        response.setContentLength(bytes.length);

        OutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes, 0, bytes.length);
        outputStream.flush();
        outputStream.close();

    }

    private Map<String, Object> parametersReport(ReportTypeEnum type) {

        var header = Header.builder()
                .title(type.getTitle())
                .logo(this.logo(this.pathLogo))
                .logoCoffee(this.logo(this.pathLogoCoffee))
                .build();

        Map<String, Object> paramaters = new HashMap<>();
        paramaters.put("pathHeader", this.pathHeader.concat(SEPARATOR).concat(this.reportHeader).concat(JASPER));
        paramaters.put("listHeader", Collections.singleton(header));
        paramaters.put("pathReport", this.pathReport);
        paramaters.put("pathSubRepor", this.pathSubReport.concat(SEPARATOR).concat(type.getSubreportName()).concat(JASPER));
        return paramaters;
    }


    private void jasperCompile(ReportTypeEnum type) {
        var headerJrxml = this.pathHeader.concat(SEPARATOR).concat(this.reportHeader).concat(JRXML);
        var headerJasper = this.pathHeader.concat(SEPARATOR).concat(this.reportHeader).concat(JASPER);

        var reportJrxml = this.pathReport.concat(SEPARATOR).concat(type.getReportName()).concat(JRXML);
        var reportJasper = this.pathReport.concat(SEPARATOR).concat(type.getReportName()).concat(JASPER);

        log.info("Convertendo o arquivo {} para {}", headerJrxml, headerJasper);
        log.info("Convertendo o arquivo {} para {}", reportJrxml, reportJasper);

        try {
            JasperCompileManager.compileReportToFile(headerJrxml, headerJasper);
            JasperCompileManager.compileReportToFile(reportJrxml, reportJasper);
        } catch (JRException e) {
            throw new JasperException("Erro ao compilar JRXML: " + e.getMessage());
        }

        if (type == ReportTypeEnum.CLIENT_DETAILS) {
            var subreportJrxml = this.pathSubReport.concat(SEPARATOR).concat(type.getSubreportName()).concat(JRXML);
            var subreportJasper = this.pathSubReport.concat(SEPARATOR).concat(type.getSubreportName()).concat(JASPER);

            try {
                JasperCompileManager.compileReportToFile(subreportJrxml, subreportJasper);
            } catch (JRException e) {
                throw new JasperException("Erro ao compilar JRXML: " + e.getMessage());
            }
        }

    }

    @SneakyThrows
    private String logo(String pathLogo) {
        File file = ResourceUtils.getFile(pathLogo);
        return AppUtil.converterImagetoBase64(file);
    }

}
