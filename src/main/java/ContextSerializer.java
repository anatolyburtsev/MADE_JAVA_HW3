import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import strategy.JSONSerializerStrategy;
import strategy.SerializerStrategy;
import strategy.XMLSerializerStrategy;

import java.lang.reflect.Field;
import java.util.Collection;

@AllArgsConstructor
public class ContextSerializer implements Serializer {
    private final SerializerStrategy serializerStrategy;

    public static Serializer newJsonSerializer() {
        return new ContextSerializer(new JSONSerializerStrategy());
    }

    public static Serializer newXmlSerializer() {
        return new ContextSerializer(new XMLSerializerStrategy());
    }

    @Override
    public String serialize(Object o) {
        return serialize(o, 0);
    }

    @SneakyThrows
    private String serialize(Object o, int shift) {
        StringBuilder sb = new StringBuilder();
        Class<?> clazz = o.getClass();
        String objectName = clazz.getSimpleName();
        sb.append(serializerStrategy.processOpenTag(objectName, shift));
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            boolean isLastField = i == fields.length - 1;
            if (isPrimitive(field)) {
                String primitiveFieldName = field.getName();
                String primitiveFieldValue = field.get(o).toString();
                sb.append(serializerStrategy.processPrimitiveField(primitiveFieldName,
                        primitiveFieldValue, shift + 1, isLastField));
            } else if (isCollection(field)) {
                String collectionFieldName = field.getName();
                Collection<String> collection = (Collection<String>) field.get(o);
                sb.append(serializerStrategy.processCollection(collectionFieldName, collection,
                        shift + 1, isLastField));
            } else {
                Object nonPrimitiveField = field.get(o);
                sb.append(serialize(nonPrimitiveField, shift + 1));
            }
        }
        sb.append(serializerStrategy.processCloseTag(objectName, shift));
        return sb.toString();
    }

    private boolean isPrimitive(Field field) {
        Class<?> clazz = field.getType();
        return clazz.isPrimitive() || clazz == String.class;
    }

    private boolean isCollection(Field field) {
        return Collection.class.isAssignableFrom(field.getType());
    }
}
