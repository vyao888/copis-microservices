package com.hmrc.bsp.copis.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.hmrc.bsp.copis.domain.reference.*;
import javafx.util.Pair;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "suspected_goods")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class SuspectedGoods {

    @NotNull
    private UUID uuid;

    @NotNull
    private Integer id;

    @Size(max = 254, message = "Description must be less than 255 characters")
    private String description;

    private CategoryOfGoods goodsCategory;

    private BaseReference originCountry;

    private Intervention intervention;

    @Positive
    @Max(message = "Max 10 digit number", value = 9999999999L)
    private Long quantity;

    @Positive
    @Max(message = "Max 10 digits before decimal place and 3 after Positive Value", value = 10000000000L)
    private BigDecimal value;

    private Boolean perishable;

    private UnitOfMeasure measureUnit;

    private Boolean smallConsignmentProcedure;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuspectedGoods that = (SuspectedGoods) o;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}

