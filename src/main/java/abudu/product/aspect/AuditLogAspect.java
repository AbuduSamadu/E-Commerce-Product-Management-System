package abudu.product.aspect;

import abudu.product.models.AuditLog;
import abudu.product.repositories.AuditLogRepository;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;



public class AuditLogAspect {


    private AuditLogRepository auditLogRepository;

    // Define a pointcut for methods annotated with @Loggable or specific package methods
    @Pointcut("@annotation(abudu.product.annotations.Loggable)")
    public void loggableMethod() {}

    // Log before method execution
    @Before("loggableMethod()")
    public void logBefore() {
        System.out.println("Executing method in service layer...");
    }

    // Log after returning from the method
    @AfterReturning(pointcut = "loggableMethod()", returning = "result")
    public void logAfterReturning(Object result) {
        AuditLog auditLog = new AuditLog();
        auditLog.setEntityName("ExampleEntity"); // Replace with dynamic logic if needed
        auditLog.setAction("Performed action"); // Replace with dynamic logic if needed
        auditLog.setPerformedBy("System"); // Replace with authenticated user
        auditLog.setTimestamp(LocalDateTime.now());

        // Save the audit log
        auditLogRepository.save(auditLog);
    }
}
