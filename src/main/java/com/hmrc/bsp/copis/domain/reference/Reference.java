package com.hmrc.bsp.copis.domain.reference;

public interface Reference {
    String getCode();
    String getDescription();
    void setCode(String code);
    void setDescription(String description);
}
