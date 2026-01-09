package com.example.talentportal.model;

import java.util.List;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {
    private String userName;
    private String password; // Must be stored securely!
    private String email;
    private List<String> roles; // e.g., "ROLE_BASIC", "ROLE_ADMIN"
}

