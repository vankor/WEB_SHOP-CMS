package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface GoodCollection {
	public void addRows(Row k);
	public Collection<Row> getRows();
	public void setRows(Collection<Row> goods);
}
