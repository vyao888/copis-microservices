package com.hmrc.bsp.copis.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "afas")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Afa {

    @NotNull
    private UUID uuid;

    @Size(max = 22, message = "AFA id must be less than 23 characters")
    private String id;

    private String paperAfa;

    @PastOrPresent(message="Contact person notification date cannot be in the future.")
    private LocalDate contactPersonNotificationDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Afa afa = (Afa) o;
        return Objects.equals(uuid, afa.uuid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uuid);
    }
}

