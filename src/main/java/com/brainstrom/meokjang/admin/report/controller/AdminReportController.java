package com.brainstrom.meokjang.admin.report.controller;

import com.brainstrom.meokjang.admin.auth.dto.HandleReportForm;
import com.brainstrom.meokjang.admin.report.dto.AdminReport;
import com.brainstrom.meokjang.admin.report.dto.AdminReportDetail;
import com.brainstrom.meokjang.admin.report.service.AdminReportService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminReportController {

    private final AdminReportService adminReportService;

    @Autowired
    public AdminReportController(AdminReportService adminReportService) {
        this.adminReportService = adminReportService;
    }

    @GetMapping("/admin/report")
    public String adminReportList(Model model, HttpSession httpSession) {
        if (validSession(httpSession)) {
            List<AdminReport> reportList = adminReportService.getReportList();
            model.addAttribute("reports", reportList);
            return "adminReport";
        } else {
            return "redirect:/admin";
        }
    }

    @GetMapping("/admin/report/{reportId}")
    public String adminReportDetail(@PathVariable Long reportId, Model model, HttpSession httpSession) {
        if (validSession(httpSession)) {
            String adminName = (String) httpSession.getAttribute("canAccessAdminPage");
            AdminReportDetail adminReportDetail = adminReportService.getReportDetail(reportId, adminName);
            model.addAttribute("report", adminReportDetail);
            return "adminReportDetail";
        } else {
            return "redirect:/admin";
        }
    }

    @PostMapping("/admin/handleReport")
    public String handleReport(HandleReportForm handleReportForm, HttpSession httpSession) {
        if (validSession(httpSession)) {
            adminReportService.handleReport(handleReportForm);
            return "redirect:/admin/report";
        } else {
            return "redirect:/admin";
        }
    }

    private boolean validSession(HttpSession httpSession) {
        String canAccessAdminPage = (String) httpSession.getAttribute("canAccessAdminPage");
        return canAccessAdminPage != null && adminReportService.validate(canAccessAdminPage);
    }
}
