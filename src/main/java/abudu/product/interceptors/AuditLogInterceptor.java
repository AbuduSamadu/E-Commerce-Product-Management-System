package abudu.product.interceptors;

import abudu.product.models.AuditLog;
import abudu.product.repositories.AuditLogRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;

@Component
public class AuditLogInterceptor implements HandlerInterceptor {

    private final AuditLogRepository auditLogRepository;

    public AuditLogInterceptor(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String entityName = request.getHeader("entityName");
        String action = request.getMethod();
        String performedBy = request.getUserPrincipal() != null ? request.getUserPrincipal().getName() : "Anonymous";

        // Create and save audit log asynchronously
        saveAuditLogAsync(entityName, action, performedBy);

        return true;
    }

    @Async
    public void saveAuditLogAsync(String entityName, String action, String performedBy) {
        AuditLog auditLog = new AuditLog();
        auditLog.setEntityName(entityName);
        auditLog.setAction(action);
        auditLog.setPerformedBy(performedBy);
        auditLog.setTimestamp(LocalDateTime.now());

        auditLogRepository.save(auditLog);
    }
}