package com.example.petiteboard.dto;

import com.example.petiteboard.domain.Role;
import com.example.petiteboard.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attrs;
    private String nameAttrKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(
            Map<String, Object> attrs,
            String nameAttrKey,
            String name,
            String email,
            String picture
    ) {
        this.attrs = attrs;
        this.nameAttrKey = nameAttrKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public static OAuthAttributes of(String uid, String userNameAttrName, Map<String, Object> attrs) {
        return ofGoogle(userNameAttrName, attrs);
    }

    public static OAuthAttributes ofGoogle(String userNameAttrName, Map<String, Object> attrs) {
        return OAuthAttributes
                .builder()
                .name((String) attrs.get("name"))
                .email((String) attrs.get("email"))
                .picture((String) attrs.get("picture"))
                .attrs(attrs)
                .nameAttrKey(userNameAttrName)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }
}
