package abudu.product.services;


import abudu.product.annotations.Loggable;
import abudu.product.models.AuditLog;
import abudu.product.repositories.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditLogService {
    private final AuditLogRepository auditLogRepository;

    @Autowired
    public AuditLogService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @Loggable
    public void createAuditLog(String action, String entityName) {
        createAuditLog(action, entityName, "System");
    }

    public AuditLog createAuditLog(String action, String entityName, String performedBy) {
        AuditLog auditLog = new AuditLog();
        auditLog.setAction(action);
        auditLog.getEntityName(entityName);
        auditLog.setPerformedBy(performedBy);
        auditLog.setTimestamp(LocalDateTime.now());;
        return auditLogRepository.save(auditLog);
    }

    public List<AuditLog> getAuditLogs() {
        return auditLogRepository.findAll();
    }

}
