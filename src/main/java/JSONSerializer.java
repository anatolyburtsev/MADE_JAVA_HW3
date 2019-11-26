import strategy.JSONSerializerStrategy;

public class JSONSerializer implements Serializer {
    private ContextSerializer contextSerializer;

    public JSONSerializer() {
        this.contextSerializer = new ContextSerializer(new JSONSerializerStrategy());
    }

    @Override
    public String serialize(Object o) {
        return contextSerializer.serialize(o);
    }
}
