package abudu.product.aspect;

import abudu.product.models.AuditLog;
import abudu.product.repositories.AuditLogRepository;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class AuditLogAspect {

    private final AuditLogRepository auditLogRepository;

    @Autowired
    public AuditLogAspect(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @Pointcut("@annotation(abudu.product.annotations.Loggable)")
    public void loggableMethod() {}

    @Before("loggableMethod()")
    public void logBefore() {
        System.out.println("Executing method in service layer...");
    }

    @AfterReturning(pointcut = "loggableMethod()", returning = "result")
    public void logAfterReturning(Object result) {
        saveAuditLogAsync("ExampleEntity", "Performed action", "System");
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