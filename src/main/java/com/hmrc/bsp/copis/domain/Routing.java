package com.hmrc.bsp.copis.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.hmrc.bsp.copis.domain.reference.CustomsProcedure;
import com.hmrc.bsp.copis.domain.reference.Reference;
import com.hmrc.bsp.copis.domain.reference.TrafficType;
import com.hmrc.bsp.copis.domain.reference.TypeOfTransport;
import javafx.util.Pair;
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
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "routings")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Routing {

    @NotNull
    private UUID uuid;

    @NotNull
    private Location departure;

    private Location transhipment;

    @NotNull
    private Location entry;

    private Reference destinationCountry;

    private Reference transportMeans;

    private Reference customersProcedure;

    private Reference trafficTypeUsed;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Routing routing = (Routing) o;
        return Objects.equals(uuid, routing.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}

