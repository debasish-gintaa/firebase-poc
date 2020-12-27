package com.ascon.firebase.payload;


import lombok.*;

import java.util.Map;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessagePayload {
    private String token;
    private String title;
    private String body;
    private Map<String, String> data;
}
