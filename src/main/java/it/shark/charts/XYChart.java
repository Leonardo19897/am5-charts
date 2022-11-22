package it.shark.charts;

import it.shark.axis.Axis;
import it.shark.series.DataSerie;

import java.util.ArrayList;
import java.util.List;

public class XYChart<T> {
    private final List<Axis> xAxes = new ArrayList<>();
    private final List<Axis> yAxes = new ArrayList<>();

    private final List<DataSerie<T, ?>> series = new ArrayList<>();

    private boolean panX;
    private boolean panY;

    public void addXAxis(Axis axis) {
        xAxes.add(axis);
    }

    public void removeAllXAxes() {
        xAxes.clear();
    }

    public void addYAxis(Axis axis) {
        yAxes.add(axis);
    }

    public void removeAllYAxes() {
        yAxes.clear();
    }

    public void addSerie(DataSerie<T, ?> serie) {
        series.add(serie);
    }

    public boolean isPanX() {
        return panX;
    }

    public void setPanX(boolean panX) {
        this.panX = panX;
    }

    public boolean isPanY() {
        return panY;
    }

    public void setPanY(boolean panY) {
        this.panY = panY;
    }

    public String toJS(String divId) {
        String chartId = "chart_" + divId;
        StringBuilder code = new StringBuilder();
        code.append("var root = am5.Root.new(\"").append(divId).append("\");\n");
        code.append("root.setThemes([\n").append("  am5themes_Animated.new(root)\n").append("]);\n");
        code.append("var ").append(chartId).append(" = root.container.children.push(\n  am5xy.XYChart.new(root, {})\n);\n");
        for (Axis axis : yAxes) {
            code.append(axis.getCreateJS(chartId, "y_axis_" + chartId + "_" + axis.getId()));
        }
        for (Axis axis : xAxes) {
            code.append(axis.getCreateJS(chartId, "x_axis_" + chartId + "_" + axis.getId()));
        }
        int serieId = 0;
        for (DataSerie<T, ?> serie : series) {
            code.append(serie.getJSData("data_serie_" + chartId + "_" + serieId));
            serieId++;
        }
        serieId = 0;
        for (DataSerie<T, ?> serie : series) {
            code.append(serie.getCreateJS(chartId, "data_serie_" + chartId + "_" + serieId));
            serieId++;
        }
        return code.toString();
    }
}
