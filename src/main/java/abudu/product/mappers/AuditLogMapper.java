package abudu.product.mappers;

import abudu.product.dto.AuditLogDTO;
import abudu.product.models.AuditLog;
import org.springframework.stereotype.Component;

@Component
public class AuditLogMapper {
    public AuditLogDTO mapToDTO(AuditLog auditLog){
        if(auditLog == null){
            return null;
        }
        AuditLogDTO auditLogDTO = new AuditLogDTO();
        auditLogDTO.setId(auditLog.getId());

        return auditLogDTO;
    }

    public AuditLog mapToEntity(AuditLogDTO auditLogDTO){
        if(auditLogDTO == null){
            return null;
        }
        AuditLog auditLog = new AuditLog();
        auditLog.setId(auditLogDTO.getId());
        return auditLog;
    }
}
