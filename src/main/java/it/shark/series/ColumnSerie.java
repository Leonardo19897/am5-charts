package it.shark.series;

import it.shark.utils.ValueExtractor;
import it.shark.axis.Axis;
import it.shark.tooltip.Tooltip;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class ColumnSerie<T, S> implements DataSerie<T, S> {
    private String name;
    private Axis xAxis;
    private Axis yAxis;
    private Tooltip tooltip;

    private ValueExtractor<T, String> categoryExtractor = Object::toString;
    private ValueExtractor<T, S> valueExtractor;

    private final List<T> data = new ArrayList<>();

    public ColumnSerie(Axis xAxis, Axis yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public Tooltip getTooltip() {
        return tooltip;
    }

    public void setTooltip(Tooltip tooltip) {
        this.tooltip = tooltip;
    }

    public ValueExtractor<T, String> getCategoryExtractor() {
        return categoryExtractor;
    }

    public void setCategoryExtractor(ValueExtractor<T, String> categoryExtractor) {
        this.categoryExtractor = categoryExtractor;
    }

    public ValueExtractor<T, S> getValueExtractor() {
        return valueExtractor;
    }

    public void setValueExtractor(ValueExtractor<T, S> valueExtractor) {
        this.valueExtractor = valueExtractor;
    }

    @Override
    public void addDataPoint(T data) {
        this.data.add(data);
    }

    @Override
    public void addData(Collection<T> data) {
        this.data.addAll(data);
    }

    @Override
    public void sortData(Comparator<T> comparator) {
        data.sort(comparator);
    }

    @Override
    public String getJSData(String serieId) {
        StringBuilder code = new StringBuilder();
        code.append("var data_").append(serieId).append(" = [");
        for (T data : this.data) {
            code.append("{\n  category: \"").append(categoryExtractor.getValue(data)).append("\",\n  value: ").append(valueExtractor.getValue(data)).append("\n},");
        }
        code.replace(code.length() - 1, code.length(), "]").append(";");
        return code.toString();
    }

    @Override
    public String getCreateJS(String chartId, String serieId) {
        String code = "x_axis_";
        code += chartId;
        code += "_";
        code += xAxis.getId();
        code += ".data.setAll(data_";
        code += serieId;
        code += ");\n ";
        code += " var ";
        code += serieId;
        code += " = ";
        code += chartId;
        code += ".series.push(\n am5xy.ColumnSeries.new (root, {\n name: \"";
        code += name;
        code += "\",\n    xAxis: ";
        code += "x_axis_";
        code += chartId;
        code += "_";
        code += xAxis.getId();
        code += ",\n    yAxis: ";
        code += "y_axis_";
        code += chartId;
        code += "_";
        code += yAxis.getId();
        code += ",\n    valueYField: \"value\",\n    categoryXField: \"category\"\n";
        if (tooltip != null) {
            code += ",\ntooltip: " + tooltip.getCreateJS() + "\n";
        }
        code += "  })\n);\n";
        code += serieId;
        code += ".data.setAll(data_";
        code += serieId;
        code += ");";
        return code;
    }
}
