package com.hmrc.bsp.copis.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class User {

    @ToString.Exclude
    private final  List<String> passwordHistory = new ArrayList<>();

    @Size(min = 1, max = 20, message = "Username length must be between 1 and 20.")
    @NotNull
    @Pattern(message = "Username must be between alpha-numeric characters", regexp = "[a-zA-Z0-9]+")
    private String username;

    @Size(min = 4, max = 20, message = "PID must be between 4 and 20 alpha-numeric characters")
    @NotNull
    private String pid;

    @Size(min = 8, max = 18, message = "Password must be between 8 and 18 alpha-numeric characters")
    @NotNull
    @ToString.Exclude
    private String password;

    @Size(min = 8, max = 18, message = "Password must be between 8 and 18 alpha-numeric characters")
    @NotNull
    @ToString.Exclude
    private String passwordConfirm;

    @NotBlank(message="userGroup cannot be empty.")
    private String userGroup;

    @NotBlank(message="User Role cannot be empty.")
    private String role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    public void addPasswordToHistory(String password) {
        if(!this.passwordHistory.contains(password)) {
            this.passwordHistory.add(password);
        }
    }

}

