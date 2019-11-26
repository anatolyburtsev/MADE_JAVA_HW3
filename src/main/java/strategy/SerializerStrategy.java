package strategy;

import java.util.Collection;

public interface SerializerStrategy {
    public StringBuilder processOpenTag(String name, int shift);
    public StringBuilder processCloseTag(String name, int shift);
    public StringBuilder processPrimitiveField(String name, String value, int shift, boolean isLastField);
    public StringBuilder processCollection(String name, Collection<String> collection, int shift, boolean isLastField);
    public String getShift(int shift);
}
