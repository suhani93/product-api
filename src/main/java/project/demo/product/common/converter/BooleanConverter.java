package project.demo.product.common.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BooleanConverter implements AttributeConverter<Boolean, String> {
    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return (attribute != null && attribute) ? "TRUE" : "FALSE";
    }

    @Override
    public Boolean convertToEntityAttribute(String flag) {
        return "TRUE".equalsIgnoreCase(flag);
    }
}

