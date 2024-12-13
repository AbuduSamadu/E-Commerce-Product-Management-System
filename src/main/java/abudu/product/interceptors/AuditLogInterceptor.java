package abudu.product.interceptors;


import abudu.product.models.AuditLog;
import abudu.product.repositories.AuditLogRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;

@Component
public class AuditLogInterceptor implements HandlerInterceptor {

    @Autowired
    private final AuditLogRepository auditLogRepository;

    public AuditLogInterceptor(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String entityName = request.getHeader("entityName");
        String action = request.getMethod(); // GET, POST, PUT, DELETE
        String performedBy = request.getUserPrincipal() != null ? request.getUserPrincipal().getName() : "Anonymous";

        // Create an audit log
        AuditLog auditLog = new AuditLog();
        auditLog.setEntityName(entityName);
        auditLog.setAction(action);
        auditLog.setPerformedBy(performedBy);
        auditLog.setTimestamp(LocalDateTime.now());

        auditLogRepository.save(auditLog);

        return true;



    }
}
