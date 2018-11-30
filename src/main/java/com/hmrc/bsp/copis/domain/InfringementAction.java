package com.hmrc.bsp.copis.domain;

import com.hmrc.bsp.copis.domain.reference.ActionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfringementAction {

    @NotNull
    private ActionType actionType;

    @PastOrPresent(message="Decision date cannot be in the future.")
    @NotNull
    private LocalDate decisionDate;

    @Size(max = 254, message = "Decision reason must be less than 255 characters")
    private String decisionReason;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfringementAction that = (InfringementAction) o;
        return Objects.equals(actionType, that.actionType) &&
                Objects.equals(decisionDate, that.decisionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actionType, decisionDate);
    }
}

