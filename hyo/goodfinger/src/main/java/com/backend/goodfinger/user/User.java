package com.backend.goodfinger.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
@Getter @Setter @ToString
public class User {
    @Id
    private String id;
    private String name;
    private int type;
    private int sex;
}
