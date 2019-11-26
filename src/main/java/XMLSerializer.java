import strategy.XMLSerializerStrategy;

public class XMLSerializer implements Serializer{
    private ContextSerializer serializer;

    public XMLSerializer() {
        this.serializer = new ContextSerializer(new XMLSerializerStrategy());
    }

    @Override
    public String serialize(Object o) {
        return serializer.serialize(o);
    }
}
