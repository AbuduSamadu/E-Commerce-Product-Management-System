package abudu.product.controllers;

import abudu.product.models.AuditLog;
import abudu.product.services.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/audit-log")
public class AuditLogController {
    private final AuditLogService auditLogService;

    @Autowired
    public AuditLogController(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }
    @RequestMapping("/create")
    public ResponseEntity<AuditLog> createAuditLog(String action, String entityName, String performedBy) {
        AuditLog auditLog = auditLogService.createAuditLog(action, entityName, performedBy);
        return ResponseEntity.ok(auditLog);
    }

    @RequestMapping("/get")
    public ResponseEntity <List<AuditLog>> getAuditLogs() {
        List<AuditLog> auditLog = auditLogService.getAuditLogs();
        return ResponseEntity.ok(auditLog);
    }

}
