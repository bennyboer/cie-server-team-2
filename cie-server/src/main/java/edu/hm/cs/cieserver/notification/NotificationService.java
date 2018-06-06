package edu.hm.cs.cieserver.notification;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping({"/api/notifications"})
public class NotificationService {

	@Autowired
	PushNotificationService notificationService;

	@PostMapping
	public ResponseEntity<String> send(@RequestBody NotificationRequest request) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			HttpEntity<String> httpRequest = new HttpEntity<>(mapper.writer().writeValueAsString(request));
			CompletableFuture<String> pushNotification = notificationService.send(httpRequest);
			CompletableFuture.allOf(pushNotification).join();

			try {
				String firebaseResponse = pushNotification.get();
				return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
	}
}
