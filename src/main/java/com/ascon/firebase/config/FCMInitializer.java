package com.ascon.firebase.config;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class FCMInitializer {
    private final Logger logger = LoggerFactory.getLogger(FCMInitializer.class);

    @PostConstruct
    public void initialize() {
        try {
            Resource resource = new ClassPathResource("fir-poc-83d41-firebase-adminsdk-xg83m-5f2b671524.json");
            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(resource.getInputStream()))
                .build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                logger.info("Firebase application has been initialized");
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public void sendMessage(String token, String title, String body, Map<String, String> data) {
        try {
            Message message = Message.builder()
                .putAllData(data)
                //            .setTopic("top")
                .setToken(token)
                .setNotification(Notification.builder()
                        .setBody("Initializing Spring DispatcherServlet 'dispatcherServlet'")
                        .setTitle(title)
                        .setImage("https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png")
                        .build())
                .build();
            String response = sendAndGetResponse(message);
            logger.info("Sent message data." + response);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    private String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException {
        return FirebaseMessaging.getInstance(FirebaseApp.getInstance()).sendAsync(message).get();
    }

}
