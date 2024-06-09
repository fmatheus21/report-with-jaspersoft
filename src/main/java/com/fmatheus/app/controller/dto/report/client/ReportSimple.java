package com.fmatheus.app.controller.dto.report.client;

import lombok.*;

import java.util.Collection;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportSimple {
    private String name;
    private String document;
    private String phone;
    private String email;
    private Collection<ReportDetails> addresses;
}
