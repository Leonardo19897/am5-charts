package it.shark.series;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class ChordDirectedSerie<T, S> implements DataSerie<T, S> {

    private final List<T> data = new ArrayList<>();

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
        return null;
    }

    @Override
    public String getCreateJS(String chartId, String serieId) {
        return null;
    }
}
