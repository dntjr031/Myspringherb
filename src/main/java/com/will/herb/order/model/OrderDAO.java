package com.will.herb.order.model;

import java.util.List;
import java.util.Map;

import com.will.herb.common.DateSearchVO;

public interface OrderDAO {
	int insertOrders(OrderVO orderVO);
	int insertOrderDetails(OrderVO orderVO);
	List<Map<String, Object>> selectDetailView(int orderNo);
	Map<String, Object> selectOrderView(int orderNo);
	List<OrderAllVO> selectOrderList(DateSearchVO dateSearchVO);
	int selectTotalRecord(DateSearchVO dateSearchVO);
	List<Map<String, Object>> selectBestPd(int productNo);
}
