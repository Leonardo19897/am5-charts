package it.shark.axis;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

public class ValueAxis implements Axis {

    private static final AtomicInteger progressiveId = new AtomicInteger(0);

    private final int id = progressiveId.getAndIncrement();
    private final AxisType type;

    public ValueAxis(AxisType type) {
        this.type = type;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getCreateJS(String chartId, String axisId) {
        String smallType = switch (type) {
            case X -> "x";
            case Y -> "y";
        };
        String bigType = smallType.toUpperCase(Locale.ROOT);
        return " var " + axisId + " = " + chartId + "." + smallType + "Axes.push(\n  am5xy.ValueAxis.new(root, {\n  renderer: am5xy.AxisRenderer" + bigType + ".new(root, {}),\n  })\n);\n";
    }
}
