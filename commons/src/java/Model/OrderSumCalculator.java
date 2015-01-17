package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderSumCalculator {
	private Map<GoodCollection, Double> ordmap;
	private GoodCollection ord;

	OrderSumCalculator(){}
	public double countSum(){
		
		return getSumValue();
	}
	

	public Map<GoodCollection, Double> getOrdmap() {
		return ordmap;
	}


	public void setOrdmap(Map<GoodCollection, Double> ordmap) {
		this.ordmap = ordmap;
	}


//	public OrderSumCalculator(Set<GoodCollection> orders) {
//		ordmap = new HashMap<GoodCollection, Double>();
//		for(GoodCollection f: orders){
//			ordmap.put(f, countSum(f));
//		}
//	}
	
	public OrderSumCalculator(GoodCollection ord) {
		this.ord =ord;
		ordmap = new HashMap<GoodCollection, Double>();
		
		ordmap.put(ord, countSum());
		
	}
	
	

	
	@Transactional
	public List<Row> createReport(List<GoodItem> ordergoods){
		Integer count = new Integer(0);
		boolean eq = false;
		int iter = 0;
//		List<GoodItem> ordergoods = ord.getGoods();
			
		List <Row> map = new ArrayList<Row>();
		RowFactory rf = new RowFactory(ord);
		Row f = rf.createRow();
		for(int i = 0; i < ordergoods.size(); i++){
			System.out.println(count);
			
			GoodItem h = ordergoods.get(i);
			if(i==0){
				count++; 
				Row first = rf.createRow();
				first.setGood(ordergoods.get(i));
				first.setGoodcount(count);
				map.add(first);
				}
			
			else{
				f.setGood(h);
				System.out.println(f.getGood().getId());
				System.out.println(map.contains(f));
				
				if(map.contains(f)){
				
				f =  map.get(map.indexOf(f));
				count = f.getGoodcount();
				f.setGoodcount(++count);	
				f = rf.createRow();
				System.out.println("hello1");
				}
				else{
					System.out.println("hello2");
//					map.put(ordergoods.get(i-1), count);
					count = 1;
					Row row = rf.createRow();
					row.setGood(h);
					row.setGoodcount(count);
					map.add(row);
//					map.add(new OrderRowBean(h,count));
					//				f = new OrderRowBean(ordergoods.get(i),count);
				}
			}
			
//			if(i==ordergoods.size()-1){
		//		map.put(h, count);
	//			f = new OrderRowBean(h,count);
	//			map.add(f);
//				}
		}
		
		
		ord.setRows(map);
		return  map;
		
	}
	
	public double getSumValue(){
		double sum = 0;
		Collection<Row> o = ord.getRows();
		Iterator i = o.iterator();
		
		while(i.hasNext()){
			Row row = (Row) i.next();
			GoodItem j = row.getGood();
			Integer k = row.getGoodcount();
			sum += j.getPrice()*k;
		}
		return sum;
	
	}

}
