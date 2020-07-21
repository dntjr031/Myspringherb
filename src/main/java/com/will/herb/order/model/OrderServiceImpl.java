package com.will.herb.order.model;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.will.herb.cart.model.CartDAO;
import com.will.herb.common.DateSearchVO;

@Service
public class OrderServiceImpl implements OrderService {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private OrderDAO orderDAO;
	
	@Autowired
	private CartDAO cartDAO;

	@Override
	@Transactional
	public int insertOrder(OrderVO orderVO) {
		
		int cnt1 = 0, cnt2 = 0, cnt3 = 0;
		
		cnt1 = orderDAO.insertOrders(orderVO);
		logger.info("orders insert 결과, cnt1={}",cnt1);
		
		cnt2 = orderDAO.insertOrderDetails(orderVO);
		logger.info("OrderDetails insert 결과, cnt2={}",cnt2);
		
		cnt3 = cartDAO.deleteCartByUserid(orderVO.getCustomerId());
		logger.info("CartByUserid delete 결과, cnt3={}",cnt3);
			
		return cnt3;
	}

	@Override
	public List<Map<String, Object>> selectDetailView(int orderNo) {
		return orderDAO.selectDetailView(orderNo);
	}

	@Override
	public Map<String, Object> selectOrderView(int orderNo) {
		return orderDAO.selectOrderView(orderNo);
	}

	@Override
	public List<OrderAllVO> selectOrderList(DateSearchVO dateSearchVO) {
		return orderDAO.selectOrderList(dateSearchVO);
	}

	@Override
	public int selectTotalRecord(DateSearchVO dateSearchVO) {
		return orderDAO.selectTotalRecord(dateSearchVO);
	}

	@Override
	public List<Map<String, Object>> selectBestPd(int productNo) {
		return orderDAO.selectBestPd(productNo);
	}

}
