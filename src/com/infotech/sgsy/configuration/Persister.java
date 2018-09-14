/*
 * Created on Jul 4, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.infotech.sgsy.configuration;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.MissingResourceException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Henry Martin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface Persister {

	public void initializeObject(Object obj)throws HibernateException;
	
	/**
	 * This method returns results for the given query as a list.
	 * @author Henry Martin 
	 * @since July 5, 2007 
	 * @param String query
	 * @return result list
	 * @throws HibernateException
	 * @throws Exception 
	 */
	public List getObjectsByQuery(String query)throws HibernateException, Exception;
	
	/**
	 * This method returns the single result for the given query as a Object.
	 * @author Henry Martin 
	 * @since July 5, 2007 
	 * @param String query
	 * @return result object
	 * @throws HibernateException
	 */
	public Object getObjectByQuery(String query)throws HibernateException, Exception;
	
	/**
	 * This method returns the single class object for the given class and
	 * primary key field name.
	 * @author Henry Martin 
	 * @since July 5, 2007 
	 * @param Class className
	 * @param Serializable id(PK field)
	 * @return result object
	 * @throws HibernateException
	 */
	public Object getObjectById(Class className, Serializable id)throws HibernateException, Exception;
	
	/**
	 * This method returns the list of objects by the class name.	 
	 * @author Henry Martin 
	 * @since July 30, 2007 
	 * @param Class className	 
	 * @return resultList
	 * @throws HibernateException
	 */
	public List getObjectsByClass(Class className)throws HibernateException, Exception;
	
	/**
	 * This method returns the page wise list of objects specified by <code>pageSize</code> for the given query.
	 * starting from the <code>firstResult</code>. 	 
	 * @author Henry Martin 
	 * @since July 30, 2007 
	 * @param String query
	 * @param int firstResult
	 * @param int pageSize	 
	 * @return list
	 * @throws HibernateException
	 */
	public List getPagedObjects(String query, int firstResult, int pageSize)
    throws HibernateException, IndexOutOfBoundsException;
	
	/**
	 * This method returns the hibernate transaction.
	 * @author Henry Martin 
	 * @since July 30, 2007		 
	 * @return Transaction	  
	 * @throws HibernateException
	 */
	public Transaction getTransaction() throws HibernateException;
	
	/**
	 * This method makes the object persistent.
	 * @author Henry Martin 
	 * @since July 5, 2007 
	 * @param Object obj
	 * @return key	  
	 * @throws HibernateException
	 */
	public Serializable saveObject(Object obj)throws HibernateException, Exception;
	
	/**
	 * @author 53414
	 * @param obj
	 * @return
	 * @throws HibernateException
	 * @throws Exception
	 */
	public void saveOrUpdateObject(Object obj)throws HibernateException, Exception;
	
	/**
	 * This method updates the object in the database.
	 * @author Henry Martin 
	 * @since July 30, 2007 
	 * @param Object obj
	 * @return key	  
	 * @throws HibernateException
	 */
	public Object updateObject(Object obj)throws HibernateException, Exception;
	
	/**
	 * This method deletes the object from the database.
	 * @author Henry Martin 
	 * @since July 31, 2007 
	 * @param Object obj	   
	 * @throws HibernateException
	 */
	public void deleteObject(Object obj)throws HibernateException, Exception;
	
	/**
	 * This method gets the total number of records for the given class 
	 * from the corresponding table.
	 * @author Henry Martin 
	 * @since Aug 03, 2007 
	 * @param Class className
	 * @return int rowCount	   
	 * @throws HibernateException
	 */
	public int getRowCountByClass(Class className)throws HibernateException, Exception;
	
	/**
	 * This method returns the number of rows for the given query.
	 * @author Henry Martin 
	 * @since July 31, 2007 
	 * @param String query
	 * @return int rowCount
	 * @throws HibernateException
	 */
	public int getRowCountByQuery(String query)throws HibernateException, Exception;
	
	/**
	 * This method closes the session object.
	 * @author Henry Martin 
	 * @since July 30, 2007	 	  
	 * @throws HibernateException
	 */	
	public void closeSession() throws HibernateException, Exception;
	
	/**
	 * This method is used to execute a database procedure.
	 * It takes a procedure name and the array of input parameters as arguments.
	 * If the procedure accepts no input parameters <code>inParamList</code> should be set
	 * to <code>null</code>.
	 * The procedure name should be registered in the <code>ApplicationProcedures.properties</code>
	 * file.
	 * @author Henry Martin 
	 * @since Aug 10, 2007 
	 * @param String procName
	 * @param Object []inParamList
	 * @return List resultsList
	 * @throws HibernateException,MissingResourceException,SQLException,Exception
	 */
	/*public List executeProcedure(String query, Object inParamList[])throws HibernateException,MissingResourceException,SQLException,Exception;
*/
	/**
	 * This method is used to execute native sql query. If the query returns single result, this method
	 * will return a list of size one. 
	 * @author Henry Martin 
	 * @since Aug 14, 2007 
	 * @param String query	 	 
	 * @return List
	 * @throws Hibernateexception	 
	 */
	public List executeNativeQuery(String query) throws HibernateException, Exception;
	
	public String executeBatchQuery(String queries[])throws HibernateException, Exception;
	
	
	public String executeBatchInsert(StringBuffer queries[])throws HibernateException, Exception;
	
	/**
	 * This method returns the Query object. This method is intended to be used for parametrized queries.
	 * @param query
	 * @return Query
	 * @throws HibernateException
	 */
	public Query getParametrizedQuery(String query) throws HibernateException, Exception;
	
	public Session getSession();

	/**
	 * This method is used to check whether session factory is closed or open.
	 * It returns <code>true</code> if session factory is open. Otherwise it returns <code>false</code>.
	 * @author Henry Martin
	 * @since Oct 3, 2007
	 * @return
	 */
	public boolean isOpen();

	/**
	 * This method is used to close the session factory, if open.
	 * @author Henry Martin
	 * @since Oct 3, 2007
	 */
	public void close();	
	
	public Object loadObjectById(Class className, Serializable id)throws HibernateException, Exception;

/**
 * This method is used to execute native sql query to delete data form temp table.
 * @author Henry Martin 
 * @since Aug 14, 2007 
 * @param String query	 	 
 * @return int
 * @throws Hibernateexception	 
 */
	public int executeNativeQry(String query) throws HibernateException, Exception;
	
	
	/**@author CVAS2273
	 * @since 5TH - JAN - 2010
	 * @param query
	 * @throws HibernateException
	 * @throws SQLException
	 * This method is used to execute native sql query for update
	 */
	
	public void updateDetailsByNativeQry(String query) throws HibernateException, SQLException;
	
	
}