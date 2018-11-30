package com.hmrc.bsp.copis.domain.reference;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customs_offices")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@NoArgsConstructor
@Data
public class CustomsOffice extends BaseReference {
    public CustomsOffice(String key, String value) {
        super(key, value);
    }
}
