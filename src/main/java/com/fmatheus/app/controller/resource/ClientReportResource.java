package com.fmatheus.app.controller.resource;

import com.fmatheus.app.controller.enumerable.ReportTypeEnum;
import com.fmatheus.app.controller.facade.ClientReportFacade;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reports/clients")
public class ClientReportResource {

    private final ClientReportFacade facade;

    @GetMapping
    public void findAll(HttpServletResponse response,  @RequestParam ReportTypeEnum type) {
        this.facade.findAll(response, type);
    }

}
