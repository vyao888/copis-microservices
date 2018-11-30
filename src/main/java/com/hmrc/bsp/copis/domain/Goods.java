package com.hmrc.bsp.copis.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "goods")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Goods {
    @NotNull
    private SuspectedGoods suspectedGoods;
    private InfringementAction infringementAction;
    private ProtectedRights protectedRights;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return Objects.equals(suspectedGoods, goods.suspectedGoods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(suspectedGoods);
    }
}
