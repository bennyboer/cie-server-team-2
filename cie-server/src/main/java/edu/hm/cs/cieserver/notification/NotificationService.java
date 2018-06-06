package edu.hm.cs.cieserver.notification;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class NotificationService {


    @Autowired
    PushNotificationService notificationService;

    @PostMapping
    public ResponseEntity<String> send(@RequestBody NotificationRequest request) throws JSONException {

        JSONObject body = new JSONObject();
        //body.put("to", "/topics/" + TOPIC); Topics, i.e. Courses
        body.put("priority", "normal");

        JSONObject notification = new JSONObject();
        notification.put("title", request.getTitle());
        notification.put("body", request.getContent());

        JSONObject data = new JSONObject();
        data.put("click_action", "FLUTTER_NOTIFICATION_CLICK");
        //Weitere data elemente hier rein!

        body.put("notification", notification);
        body.put("data", data);

        HttpEntity<String> httpRequest = new HttpEntity<>(body.toString());

        CompletableFuture<String> pushNotification = notificationService.send(httpRequest);
        CompletableFuture.allOf(pushNotification).join();

        try {
            String firebaseResponse = pushNotification.get();
            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
    }
}
