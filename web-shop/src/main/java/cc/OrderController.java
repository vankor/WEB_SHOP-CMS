package cc;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import Model.GoodItem;
import Model.Order;
import Model.OrderService;

@Controller
public class OrderController {
	
	private Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService ordServ;
	
	@RequestMapping("/ordgoods/{orderid}")
	public String listOrdGoods(@PathVariable(value = "orderid")Integer id,Map<String, Object> map) {
		Order or = ordServ.getById(id);
		map.put("ord", new GoodItem() );
		map.put("ordRows", or.getRows());
		System.out.println("listOrderGoods");
		return "ordergoodlist";
	}
	
}
