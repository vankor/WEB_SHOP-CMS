package Model.others;

import java.util.Collection;
import java.util.TreeSet;


import Model.dto_beans.BucketRowBean;
import Model.entity.GoodItem;


public class AnonimBuck implements GoodCollection {
	
	
	private int id;
	private String name;

	private Collection <Row> rows= new TreeSet<Row>();

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	


	public void deleteGood(GoodItem good){
		int i = 0;
		Row r = null;
		for(Row orderbean: rows){
			if(orderbean.getGood().equals(good)){
				r=orderbean;
				break;
			}
			i++;
		}
		rows.remove(r);

	}
	
	public void changeCount(GoodItem good, Integer count){
		if(count<=0){
			deleteGood(good);
			return;
		}
		for(Row orderbean: rows){
			if(orderbean.getGood().equals(good)){
				orderbean.setGoodcount(count);
				return;
			}
			
		}
	}

	public void addGood(GoodItem good){
		Integer count;
		for(Row orderbean: rows){
			if(orderbean.getGood().equals(good)){
				count = orderbean.getGoodcount()+1;
				orderbean.setGoodcount(count);
				return;
			}
			
		}
		
		
		Row f = new BucketRowBean();
		f.setGood(good);
		f.setGoodcount(1);
		rows.add(f);

		
	}

	public Collection<Row> getRows() {
		return rows;
	}

	public void setRows(Collection<Row> rows) {
		this.rows = rows;
	}

	@Override
	public void addRows(Row k) {
		rows.add(k);
		
	}

	public Integer getSize(){
		Integer size = 0;
		for(Row orderbean: rows){
			size += orderbean.getGoodcount();
		}
		return size;
	}



	







		








		
	




		

	

	
}
