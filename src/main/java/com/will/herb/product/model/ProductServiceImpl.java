package com.will.herb.product.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDao;

	@Override
	public List<ProductVO> selectEvent(String eventName) {
		return productDao.selectEvent(eventName);
	}

	@Override
	public List<ProductVO> selectPdByCategory(int categoryNo) {
		return productDao.selectPdByCategory(categoryNo);
	}

	@Override
	public ProductVO selectProduct(int productNo) {
		return productDao.selectProduct(productNo);
	}

	@Override
	public int insertProduct(ProductVO vo) {
		return productDao.insertProduct(vo);
	}

	@Override
	public List<ProductVO> selectProductAll(EventProductVO vo) {
		return productDao.selectProductAll(vo);
	}

	@Override
	public int selectTotalRecord(EventProductVO searchVo) {
		return productDao.selectTotalRecord(searchVo);
	}

	@Override
	@Transactional
	public int deleteMulti(List<ProductVO> pdList) {
		int cnt = 0;
		try {
			for (ProductVO vo : pdList) {
				if(vo.getProductNo() != 0) { //체크한 상품만 삭제
					cnt = productDao.deleteProduct(vo.getProductNo());
				}
			}
		} catch (RuntimeException e) {
			cnt = -1;
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		//선언적 트랜잭션에서는 런타임 예외가 발생하면 롤백한다.
		//반면에 예외가 전혀 발생하지 않거나  체크 예외가 발생하면 커밋한다.
		
		return cnt;
	}

	@Override
	@Transactional
	public int EventMulti(List<EventProductVO> list) {
		int cnt = 0;
		try {
			for (EventProductVO vo : list) {
				int count = productDao.selectEventByPdNo(vo);
				if(count == 0) {
					cnt = productDao.insertEventPd(vo);
				}
			
			}
		} catch (RuntimeException e) {
			cnt = -1;
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		return cnt;
	}
}
