package com.hmrc.bsp.copis.domain.reference;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "traffic_types")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@NoArgsConstructor
@Data
public class TrafficType extends BaseReference {
    public TrafficType(String key, String value) {
        super(key, value);
    }
}
