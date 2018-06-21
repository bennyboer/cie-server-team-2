package edu.hm.cs.cieserver.notification;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class NotificationRequest<T> {

	private String to = "/topics/all";

	private String priority = "high";

	private Map<String, String> notification;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Map<String, Object> data;

	public NotificationRequest() {
		// Default constructor needed for jackson.
	}

	public NotificationRequest(String title, String content, Optional<String> to, Optional<T> additionalData) {
		notification = new HashMap<>();
		data = new HashMap<>();
		notification.put("title", title);
		notification.put("body", content);
		data.put("click-action", "FLUTTER_NOTIFICATION_CLICK");

		additionalData.ifPresent(addData -> data.put("additionalData", addData));

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

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
}
