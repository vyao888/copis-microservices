package com.hmrc.bsp.copis.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "infringements")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Infringement {

    @Id
    @Size(min = 3, max = 22, message = "Infringement ID length must be between 3 and 22.")
    @NotNull
    @Pattern(message = "Infringement ID must Starts GB and end with a number", regexp = "^GB[0-9]+$")
    private String id;

    @PastOrPresent(message="Detention date cannot be in the future.")
    private LocalDate detentionDate;

    private String grossWeight;

    @NotNull
    private String referenceNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Infringement that = (Infringement) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

