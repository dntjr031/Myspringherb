package com.will.herb.order.model;

import java.util.List;
import java.util.Map;

public class OrderAllVO {
	// orders 테이블
	private OrderVO orderVO;
	// => mybatis- 1:1 관계를 처리하는 association 엘리먼트 이용

	// orderDetailsView의 정보를 여러개 넣을 수 있는 List
	private List<Map<String, Object>> orderDetailsList;
	// 1:N 관계를 처리하는 collection 엘리먼트 이용

	public OrderVO getOrderVO() {
		return orderVO;
	}

	public void setOrderVO(OrderVO orderVO) {
		this.orderVO = orderVO;
	}

	public List<Map<String, Object>> getOrderDetailsList() {
		return orderDetailsList;
	}

	public void setOrderDetailsList(List<Map<String, Object>> orderDetailsList) {
		this.orderDetailsList = orderDetailsList;
	}

	@Override
	public String toString() {
		return "OrderAllVO [orderVO=" + orderVO + ", orderDetailsList=" + orderDetailsList + "]";
	}

}
