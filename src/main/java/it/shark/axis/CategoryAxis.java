package it.shark.axis;

import it.shark.renderer.AxisRenderer;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

public class CategoryAxis implements Axis {

    private static final AtomicInteger progressiveId = new AtomicInteger(0);

    private final int id = progressiveId.getAndIncrement();
    private final AxisType type;

    private AxisRenderer axisRenderer;

    public CategoryAxis(AxisType type) {
        this.type = type;
    }

    @Override
    public int getId() {
        return id;
    }

    public AxisRenderer getAxisRenderer() {
        return axisRenderer;
    }

    public void setAxisRenderer(AxisRenderer axisRenderer) {
        this.axisRenderer = axisRenderer;
    }

    @Override
    public String getCreateJS(String chartId, String axisId) {
        String smallType = switch (type) {
            case X -> "x";
            case Y -> "y";
        };
        String bigType = smallType.toUpperCase(Locale.ROOT);

        StringBuilder code = new StringBuilder();
        if (axisRenderer != null) {
            code.append(axisRenderer.getCreateJS(axisId));
        }
        code.append(" var ").append(axisId).append(" = ").append(chartId).append(".").append(smallType).append("Axes.push(\n  am5xy.CategoryAxis.new(root, {\n  ");
        code.append("renderer:");
        if (axisRenderer == null) {
            code.append(" am5xy.AxisRenderer").append(bigType).append(".new(root, {})");
        } else {
            code.append(axisId).append("_").append(smallType).append("renderer");
        }
        code.append(",\n  categoryField: \"category\"\n  })\n);\n");
        return code.toString();
    }

}
