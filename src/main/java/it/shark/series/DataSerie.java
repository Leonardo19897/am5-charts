package it.shark.series;

import java.util.Collection;
import java.util.Comparator;

public interface DataSerie<T, S> {

    void addDataPoint(T data);

    void addData(Collection<T> data);

    void sortData(Comparator<T> comparator);

    String getJSData(String serieId);

    String getCreateJS(String chartId, String serieId);
}
