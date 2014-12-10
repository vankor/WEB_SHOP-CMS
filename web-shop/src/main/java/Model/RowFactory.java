package Model;

public class RowFactory {
	
	private GoodCollection col;
	
	public RowFactory(GoodCollection col) {
		super();
		this.col = col;
	}

	public GoodCollection getCol() {
		return col;
	}

	public void setCol(GoodCollection col) {
		this.col = col;
	}

	public Row createRow(){
		Row row = null;
		if(col instanceof AnonimBuck){
			row = new BucketRowBean();
		}
		
		if(col instanceof Order){
			row = new OrderRow();
		}
		return row;
	}

}
