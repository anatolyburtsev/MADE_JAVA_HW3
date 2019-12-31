package strategy;

import java.util.Collection;

public interface SerializerStrategy {
    String processOpenTag(String name, int shift);
    String processCloseTag(String name, int shift);
    String processPrimitiveField(String name, String value, int shift, boolean isLastField);
    String processCollection(String name, Collection<String> collection, int shift, boolean isLastField);
    String getShift(int shift);
}
