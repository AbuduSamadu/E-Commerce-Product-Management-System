package abudu.product.dto;

public class AuditLogDTO {
    private Long id;
    private String action;
    private Long userId;  // Refers to the User ID
    private String timestamp;
    private String details;

    public AuditLogDTO() {
    }

    public AuditLogDTO(Long id, String action, Long userId, String timestamp, String details) {
        this.id = id;
        this.action = action;
        this.userId = userId;
        this.timestamp = timestamp;
        this.details = details;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    // toString

    @Override
    public String toString() {
        return "AuditLog{" +
                "id=" + id +
                ", action='" + action + '\'' +
                ", userId=" + userId +
                ", timestamp='" + timestamp + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
