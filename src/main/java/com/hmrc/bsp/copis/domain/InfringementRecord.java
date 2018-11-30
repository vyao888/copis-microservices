package com.hmrc.bsp.copis.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfringementRecord {
    @NotNull
    private Infringement infringement;
    @NotNull
    private Routing routing;
    @NotNull
    private Goods goods;
    @NotNull
    private Info info;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfringementRecord that = (InfringementRecord) o;
        return Objects.equals(infringement, that.infringement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(infringement);
    }
}
