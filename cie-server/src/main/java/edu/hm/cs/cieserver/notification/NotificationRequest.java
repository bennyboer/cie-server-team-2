package edu.hm.cs.cieserver.notification;

import java.util.HashMap;
import java.util.Map;

public class NotificationRequest {

	//private String to;

	private String priority = "normal";

	private Map<String, String> notification;

	private Map<String, String> data = null;

	public NotificationRequest() {
		// Default constructor for jackson
	}

	public NotificationRequest(String title, String content) {
		notification = new HashMap<>();
		notification.put("title", title);
		notification.put("body", content);
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
}
