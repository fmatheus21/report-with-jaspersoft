package com.fmatheus.app.controller.rule;

import com.fmatheus.app.controller.constant.FormatConstant;
import com.fmatheus.app.controller.dto.report.client.ReportDetails;
import com.fmatheus.app.controller.dto.report.client.ReportSimple;
import com.fmatheus.app.controller.enumerable.ReportTypeEnum;
import com.fmatheus.app.controller.exception.JasperException;
import com.fmatheus.app.controller.util.AppUtil;
import com.fmatheus.app.model.entity.Client;
import com.fmatheus.app.model.service.ClientService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
@Component
public class ClientReportRule {

    private final ClientService clientService;
    private final ReportRule reportRule;

    public void findAll(HttpServletResponse response, ReportTypeEnum type) {
        this.printOut(response, type);
    }

    private void printOut(HttpServletResponse response, ReportTypeEnum type) {
        try {
            this.reportRule.createReport(response, this.converterReportList(), type);
        } catch (JRException | IOException e) {
            throw new JasperException(e.getMessage());
        }

    }

    private Collection<ReportSimple> converterReportList() {
        var clients = this.clientService.findAll();
        return clients.stream().map(this::converterReport).toList();
    }

    private ReportSimple converterReport(Client client) {
        return ReportSimple.builder()
                .name(AppUtil.convertFirstUppercaseCharacter(client.getIdPerson().getName()))
                .document(client.getIdPerson().getPersonType().getName().equalsIgnoreCase("PESSOA FISICA") ?
                        AppUtil.formatCPF(client.getIdPerson().getDocument()) : AppUtil.formatCNPJ(client.getIdPerson().getDocument()))
                .email(AppUtil.convertAllLowercaseCharacters(client.getIdPerson().getContact().getEmail()))
                .phone(AppUtil.countCharacter(client.getIdPerson().getContact().getPhone()) == 11 ?
                        AppUtil.formatMask(client.getIdPerson().getContact().getPhone(), FormatConstant.PHONE) :
                        AppUtil.formatMask(client.getIdPerson().getContact().getPhone(), FormatConstant.PHONE_OLD))
                .addresses(Collections.singletonList(
                        ReportDetails.builder()
                                .place(client.getIdPerson().getAddress().getPlace())
                                .number(client.getIdPerson().getAddress().getNumber())
                                .complement(client.getIdPerson().getAddress().getComplement())
                                .district(client.getIdPerson().getAddress().getDistrict())
                                .city(client.getIdPerson().getAddress().getCity())
                                .state(client.getIdPerson().getAddress().getState())
                                .zipCode(AppUtil.formatMask(client.getIdPerson().getAddress().getZipCode(), FormatConstant.ZIP_CODE))
                                .build()
                ))
                .build();
    }

}
