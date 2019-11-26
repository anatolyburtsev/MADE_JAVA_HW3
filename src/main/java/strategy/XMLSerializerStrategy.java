package strategy;

import java.util.Collection;

public class XMLSerializerStrategy implements SerializerStrategy {
    private static final String DEFAULT_SHIFT = "  ";
    private static final int INITIAL_COLLECTION_INDEX = 1;

    @Override
    public StringBuilder processOpenTag(String name, int shift) {
        StringBuilder sb = new StringBuilder();
        sb.append(getShift(shift)).append("<").append(name).append(">").append("\n");
        return sb;
    }

    @Override
    public StringBuilder processCloseTag(String name, int shift) {
        StringBuilder sb = new StringBuilder();
        sb.append(getShift(shift)).append("</").append(name).append(">").append("\n");
        return sb;
    }

    @Override
    public StringBuilder processPrimitiveField(String name, String value, int shift, boolean isLastField) {
        StringBuilder sb = new StringBuilder();
        sb.append(getShift(shift)).append("<").append(name).append(">");
        sb.append(value);
        sb.append("</").append(name).append(">").append("\n");
        return sb;
    }

    @Override
    public StringBuilder processCollection(String name, Collection<String> collection, int shift, boolean isLastField) {
        StringBuilder sb = new StringBuilder();
        sb.append(getShift(shift)).append("<").append(name).append(">\n");
        int index = INITIAL_COLLECTION_INDEX;
        for (String value: collection) {
            sb.append(getShift(shift+1)).append("<").append(index).append(">")
                    .append(value).append("</").append(index).append(">\n");
            index++;
        }
        sb.append(getShift(shift)).append("</").append(name).append(">\n");
        return sb;
    }

    @Override
    public String getShift(int shift) {
        return DEFAULT_SHIFT.repeat(Math.max(0, shift));
    }
}
