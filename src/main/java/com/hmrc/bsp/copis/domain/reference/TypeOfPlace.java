package com.hmrc.bsp.copis.domain.reference;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "type_of_places")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@NoArgsConstructor
@Data
public class TypeOfPlace extends BaseReference {
    public TypeOfPlace(String key, String value) {
        super(key, value);
    }
}
