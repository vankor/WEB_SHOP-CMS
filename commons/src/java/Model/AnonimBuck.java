package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public class AnonimBuck implements GoodCollection {
	
	
	private int id;
	private String name;
//	private List <Integer> goodsid= new ArrayList<Integer>();
	private Collection <Row> rows= new TreeSet<Row>();
	
//	public List <Integer> getGoodsId(){
//		return goodsid;
//		}
		
//	public void setGoodsId(List<Integer> goods) {
//			this.goodsid = goods;
//		}
	
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
	
//	public void addGood(Integer k){
//		goodsid.add(k);
//	}
	
//	public void deleteGood(Integer k){
//		goodsid.remove(k);
//	}
	
//	public Iterator createIterator(){
//		return goodsid.iterator();
//	}

	
//	public void addGood(GoodItem k) {}




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
//		for(int i = 0; i < rows.size(); i++){
//			if(rows.get(i).getGood().equals(good)){
//				rows.remove(i);
//				return;
//			}
			
//		}
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



	

//	@Transactional
//	public List<GoodItem> getGoods() {
	//	List <GoodItem> goods = new ArrayList<GoodItem>();
	//	int id;
	//	System.out.println(goodsid.size());
	//	
		
	//	for(int i = 0; i < goodsid.size(); i++){
		//	if(serv !=null){
	//		id = goodsid.get(i);
	//		GoodItem g = serv.getGoodById(id);
	//		goods.add(g);
		//	}
	//	return goods;
	//	}
		
	


//	public void setGoods(List<GoodItem> goods) {
//		this.goods = goods;
		
//	}
	

	
}
