package strategy;

import java.util.Collection;

public class JSONSerializerStrategy implements SerializerStrategy {
    private static final String DEFAULT_SHIFT = "  ";
    private static final String NEW_LINE = "\n";

    @Override
    public String processOpenTag(String name, int shift) {
        if (shift == 0) {
            return "{" + NEW_LINE;
        } else {
            return getShift(shift) + "\"" + name + "\": {" + NEW_LINE;
        }
    }

    @Override
    public String processCloseTag(String name, int shift) {
        String commaAtTheEnd = shift == 0 ? "" : ",";
        return getShift(shift) + "}" + commaAtTheEnd + NEW_LINE;
    }

    @Override
    public String processPrimitiveField(String name, String value, int shift, boolean isLastField) {
        String commaAtTheEnd = shift == 0 ? "" : ",";
        return getShift(shift) + "\"" + name + "\":\"" + value + "\"" + commaAtTheEnd + NEW_LINE;
    }

    @Override
    public String processCollection(String name, Collection<String> collection, int shift, boolean isLastField) {
        StringBuilder sb = new StringBuilder();
        sb.append(getShift(shift)).append("\"").append(name).append("\":[").append(NEW_LINE);
        int index = 0;
        for (String value : collection) {
            boolean isLastValue = index == collection.size() - 1;
            sb.append(getShift(shift + 1)).append("\"").append(value).append("\"");
            if (!isLastValue) {
                sb.append(",");
            }
            sb.append("\n");
            index++;
        }
        sb.append(getShift(shift)).append("]");
        if (!isLastField) {
            sb.append(",");
        }
        sb.append(NEW_LINE);
        return sb.toString();
    }

    @Override
    public String getShift(int shift) {
        return DEFAULT_SHIFT.repeat(Math.max(0, shift));
    }
}
