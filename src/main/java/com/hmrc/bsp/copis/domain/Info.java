package com.hmrc.bsp.copis.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.plexus.util.StringUtils;

import java.util.Objects;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Info {
    private final String DESCRIPTION = "If this record was rejected by CoPIS during the upload process you can reexport it after correcting any errors by clicking on the Resubmit Record button. If the record has already been into CoPIS it will be rejected during the upload process as it would cause a duplicate Infringement ID.";
    private String error;
    private FileExportHistory exportInformation;

    public boolean hasError() {
        return StringUtils.isNotBlank(this.error);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Info info = (Info) o;
        return Objects.equals(error, info.error) &&
                Objects.equals(exportInformation, info.exportInformation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(error, exportInformation);
    }
}
