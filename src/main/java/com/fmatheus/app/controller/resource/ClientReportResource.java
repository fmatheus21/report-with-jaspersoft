package com.fmatheus.app.controller.resource;

import com.fmatheus.app.controller.enumerable.ReportTypeEnum;
import com.fmatheus.app.controller.rule.ClientReportRule;
import jakarta.servlet.http.HttpServletRequest;
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

    private final ClientReportRule rule;

    @GetMapping
    public void findAll(HttpServletResponse response, HttpServletRequest request, @RequestParam ReportTypeEnum type) {
        this.rule.findAll(response, type);
    }

}
