package com.fmatheus.app.controller.dto.report.client;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Header {
    private String title;
    private String logo;
    private String logoCoffee;
}
