package com.hmrc.bsp.copis.domain;

import com.hmrc.bsp.copis.domain.reference.BaseReference;
import com.hmrc.bsp.copis.domain.reference.CountryCode;
import com.hmrc.bsp.copis.domain.reference.TypeOfPlace;
import javafx.util.Pair;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.Objects;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    private BaseReference country;
    @Size(max = 100, message = "Transhipment place must be less than 101 characters")
    private String place;
    private TypeOfPlace placeType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(country, location.country) &&
                Objects.equals(place, location.place) &&
                Objects.equals(placeType, location.placeType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, place, placeType);
    }
}
