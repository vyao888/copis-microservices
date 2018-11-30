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
@Document(collection = "file_imports")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FileImportHistory {

    @NotNull
    @Id
    private Long id;

    @PastOrPresent(message="Imported date cannot be in the future.")
    private LocalDate imported;

    @NotBlank(message="Site cannot be empty.")
    private String site;

    @PastOrPresent(message="Created date cannot be in the future.")
    private LocalDate created;

    @PositiveOrZero
    private Integer numberOfRecords;

    @NotBlank(message="Version cannot be empty.")
    private String version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileImportHistory that = (FileImportHistory) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

