package edu.hm.cs.cieserver.notification;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class NotificationRequest {

    private String to;
    private String priority = "normal";
    private Map<String, String> notification;

    private Map<String, String> data;

    public NotificationRequest(String title, String content, Optional<String> to) {
        notification = new HashMap<>();
        data = new HashMap<>();
        notification.put("title", title);
        notification.put("body", content);
        data.put("click-action", "FLUTTER_NOTIFICATION_CLICK");
        this.to = to.orElse("/topics/all");
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Map<String, String> getNotification() {
        return notification;
    }

    public void setNotification(Map<String, String> notification) {
        this.notification = notification;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
