package com.hmrc.bsp.copis.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.util.Objects;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "file_exports")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FileExportHistory {

    @NotNull
    @Id
    private Long id;

    @PastOrPresent(message="Exported date cannot be in the future.")
    private LocalDate exported;

    @NotBlank(message="Filename cannot be empty.")
    private String fileName;

    @PositiveOrZero
    private Integer numberOfRecords;

    private String transactionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileExportHistory that = (FileExportHistory) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

