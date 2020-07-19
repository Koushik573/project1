package com.niit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.dao.CartDAO;
import com.niit.dao.OrderDAO;
import com.niit.model.CartItem;
import com.niit.model.OrderDetail;

@Controller
public class OrderController 
{
	
	@Autowired
	CartDAO cartDAO;
	
	@Autowired
	OrderDAO orderDAO;
	
	@RequestMapping(value="/payment")
	public String showPaymentPage()
	{
		return "Payment";
	}
	
	@RequestMapping(value="/cc")
	public String showCCPage() 
	{
		return "Cc";
	}
	
	@RequestMapping(value="/paymentprocess",method=RequestMethod.POST)
	public String paymentProcess(@RequestParam("pmode")String pmode,HttpSession session,Model m)
	{
		String username = (String)session.getAttribute("username");
		
		List<CartItem> listCartItem = cartDAO.listCartItem(username);
		m.addAttribute("cartItem", listCartItem);
		m.addAttribute("grandTotal", this.calcGrandTotalValue(listCartItem));
		
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setUsername(username);
		orderDetail.setOrderDate(new java.util.Date());
		orderDetail.setPmode(pmode);
		orderDetail.setTotalShoppingAmount((int)this.calcGrandTotalValue(listCartItem));
		
		orderDAO.insertOrderDetail(orderDetail);
		
		return "ThankYou";
		
	}
	
	public long calcGrandTotalValue(List<CartItem> listCartItem) 
	{
		int count=0;
		long grandTotalPrice = 0;
		while(count<listCartItem.size())
		{
			grandTotalPrice+=listCartItem.get(count).getQuantity()*listCartItem.get(count).getPrice();
			count++;
		}
		return grandTotalPrice;
	}
}
