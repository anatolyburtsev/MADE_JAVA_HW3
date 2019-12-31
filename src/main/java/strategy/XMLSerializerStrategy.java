package strategy;

import java.util.Collection;

public class XMLSerializerStrategy implements SerializerStrategy {
    private static final String DEFAULT_SHIFT = "  ";
    private static final int INITIAL_COLLECTION_INDEX = 1;
    private static final String NEW_LINE = "\n";

    @Override
    public String processOpenTag(String name, int shift) {
        return getShift(shift) + getOpenTag(name) + NEW_LINE;
    }

    @Override
    public String processCloseTag(String name, int shift) {
        return getShift(shift) + getCloseTag(name) + NEW_LINE;
    }

    @Override
    public String processPrimitiveField(String name, String value, int shift, boolean isLastField) {
        return getShift(shift) + getOpenTag(name) + value + getCloseTag(name) + NEW_LINE;
    }

    @Override
    public String processCollection(String name, Collection<String> collection, int shift, boolean isLastField) {
        StringBuilder sb = new StringBuilder();
        sb.append(getShift(shift)).append(getOpenTag(name)).append(NEW_LINE);
        int index = INITIAL_COLLECTION_INDEX;
        for (String value: collection) {
            sb.append(getShift(shift+1)).append(getOpenTag(String.valueOf(index)))
                    .append(value).append(getCloseTag(String.valueOf(index)))
                    .append(NEW_LINE);
            index++;
        }
        sb.append(getShift(shift)).append(getCloseTag(name)).append(NEW_LINE);
        return sb.toString();
    }

    @Override
    public String getShift(int shift) {
        return DEFAULT_SHIFT.repeat(Math.max(0, shift));
    }

    private String getOpenTag(String name) {
        return "<" + name + ">";
    }

    private String getCloseTag(String name) {
        return "</" + name + ">";
    }
}
