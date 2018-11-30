package com.hmrc.bsp.copis.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "iprs")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Ipr {

    @NotNull
    private UUID uuid;

    @NotNull
    private Integer id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ipr ipr = (Ipr) o;
        return Objects.equals(uuid, ipr.uuid) &&
                Objects.equals(id, ipr.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, id);
    }
}

