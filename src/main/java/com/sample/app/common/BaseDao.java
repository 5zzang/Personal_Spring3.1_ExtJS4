package com.sample.app.common;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * MySQL에서 DAO를 통한 데이터 핸들링에 필요한 요소를 포함한 부모 클래스입니다.
 */
public abstract class BaseDao {
	@Autowired @Qualifier("sqlSessionMaster") private SqlSession sqlSessionMaster;
	@Autowired @Qualifier("sqlSessionSlave") private SqlSession sqlSessionSlave;
	
//	@Autowired private DataSourceTransactionManager transactionManager;
//	@Autowired private DefaultTransactionDefinition transactionDefinition;
	
	/**
	 * INSERT, DELETE, UPDATE시에만 사용됩니다.
	 * @author 5zzang
	 * 2013. 2. 28.
	 * @return
	 * SqlSession
	 */
	protected SqlSession getMasterSession() {
		return sqlSessionMaster;
	}
	
	/**
	 * SELECT시에만 사용됩니다.
	 * @author 5zzang
	 * @date 2013. 2. 28.
	 * @return
	 * SqlSession
	 */
	protected SqlSession getSlaveSession() {
		return sqlSessionSlave;
	}
	
//	/**
//	 * Transaction 구현시 사용됩니다.
//	 * @author 5zzang
//	 * @date 2013. 3. 4.
//	 * @return
//	 * DataSourceTransactionManager
//	 */
//	protected DataSourceTransactionManager getTransactionManager() {
//		return transactionManager;
//	}
//	
//	/**
//	 * Transaction 구현시 사용됩니다.
//	 * @author 5zzang
//	 * @date 2013. 3. 4.
//	 * @param transactionName
//	 * @return
//	 * DefaultTransactionDefinition
//	 */
//	protected DefaultTransactionDefinition getTransactionDefinition(String transactionName) {
//		transactionDefinition.setName(transactionName);
//		transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//		
//		return transactionDefinition;
//	}
//	
//	/**
//	 * Transaction 구현시 사용됩니다.
//	 * @author 5zzang
//	 * @date 2013. 3. 4.
//	 * @return
//	 * TransactionStatus
//	 */
//	protected TransactionStatus getTransactionStatus() {
//		return transactionManager.getTransaction(transactionDefinition);
//	}
}
