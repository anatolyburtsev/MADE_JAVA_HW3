package strategy;

import java.util.Collection;

public class JSONSerializerStrategy implements SerializerStrategy {
    private static final String DEFAULT_SHIFT = "  ";

    @Override
    public StringBuilder processOpenTag(String name, int shift) {
        StringBuilder sb = new StringBuilder();
        if (shift == 0) {
            sb.append("{").append("\n");
        } else {
            sb.append(getShift(shift)).append("\"").append(name).append("\": {\n");
        }
        return sb;
    }

    @Override
    public StringBuilder processCloseTag(String name, int shift) {
        StringBuilder sb = new StringBuilder();
        sb.append(getShift(shift)).append("}");
        if (shift != 0) {
            sb.append(",");
        }
        sb.append("\n");
        return sb;
    }

    @Override
    public StringBuilder processPrimitiveField(String name, String value, int shift, boolean isLastField) {
        StringBuilder sb = new StringBuilder();
        sb.append(getShift(shift)).append("\"").append(name).append("\":\"").append(value).append("\"");
        if (!isLastField) {
            sb.append(",");
        }
        sb.append("\n");
        return sb;
    }

    @Override
    public StringBuilder processCollection(String name, Collection<String> collection, int shift, boolean isLastField) {
        StringBuilder sb = new StringBuilder();
        sb.append(getShift(shift)).append("\"").append(name).append("\":[\n");
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
        sb.append("\n");
        return sb;
    }

    @Override
    public String getShift(int shift) {
        return DEFAULT_SHIFT.repeat(Math.max(0, shift));
    }
}
