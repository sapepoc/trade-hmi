package com.sap.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.sap.dao.ITradeDataDao;
import com.sap.data.Trade;
import com.sap.exception.SapException;
import com.sap.persistence.HibernateUtil;

@Repository("tradeDataDao")
public class TradeDataDaoImpl implements ITradeDataDao{
	
	public List<Trade> fetchAllTradesForDate(String date) throws SapException {
		List<Trade> list =null;
		try{
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from TradeData");
		list = q.list();
		
		tx.commit();
//		
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		
		return list;
		
	}

}
