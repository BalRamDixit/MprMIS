/*
 * Created on Jul 4, 2007
 *A class to handle all hibernate related activities.
 */
package com.infotech.sgsy.configuration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.infotech.sgsy.util.SGSYConnectionUtil;

/**
 * A singelton class which returns only one instance of the session factory.
 * @author Henry Martin 
 */
public class PersisterImpl implements Persister{

	private static Logger log = Logger.getLogger(PersisterImpl.class);

	private static SessionFactory sessionFactory = null;
	private static PersisterImpl persister = null;

	public static Connection con;

	private PersisterImpl(){
		if(sessionFactory==null)
			initSessionFactory();
	}

	public static PersisterImpl getPersister(){
		synchronized(PersisterImpl.class){
			if(persister==null)
				persister = new PersisterImpl();
		}
		return persister;
	}

	/**
	 * Singelton method to initialize session factory.
	 * @author Henry Martin
	 * @since July 5, 2007 
	 */
	private static synchronized void initSessionFactory() {

		Configuration cfg = new Configuration();
		sessionFactory = SGSYConnectionUtil.getSessionFactory();
	}

	public Session getSession(){

//		Session session=sessionFactory.getCurrentSession();
//		if(session==null){
//			session=sessionFactory.openSession();
//		}
		return sessionFactory.openSession();
	}

	public static Connection getConnection() throws SQLException {

		// persister=new PersisterImpl();
		// Connection con=persister.getSession().connection();
		
		
		/*Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/ddu_atul",
					"postgres", "postgres");
			
			System.out.println("Error in Connection --charvi.... wwwwwwwwwwwwwwwwwwwwww" + con);
		} catch (Exception e) {
			System.out.println("Error in Connection --charvi.... " + e);
			e.printStackTrace();
		}
		return con;*/
		persister=new PersisterImpl();
		con=persister.getSession().connection();
		System.out.println("con...-------->>>"+con);
		return con;
		
		
		
	}
	
	public void initializeObject(Object obj)throws HibernateException{
		try{
			log.info("Initializing object.");
			Hibernate.initialize(obj);
			log.info("Object Initialize.");
		}catch (HibernateException e) {
			log.error("Some error occured while initializing objet "+obj.toString(),e);
			throw e;
		}
	}

	/**
	 * This method returns results for the given query as a list.
	 * @author Henry Martin 
	 * @since July 5, 2007 
	 * @param String query
	 * @return result list
	 * @throws HibernateException
	 * @throws Exception 
	 */
	public List getObjectsByQuery(String query)throws HibernateException, Exception{

		Session session =getSession();
		Query queryObj = null;
		if(query!=null && !"".equals(query.trim())){
			try{		   	
				queryObj = session.createQuery(query);
				List results = null;
				if(queryObj!=null)
					results = queryObj.list();			
				return results;
			}catch(HibernateException he){
				log.error("Invalid query error: "+he);
				throw new HibernateException("Invalid query : "+query);
			}catch(Exception e){
				log.error("Error: "+e);
				e.printStackTrace();
				throw new HibernateException(e);
			} finally {
				session.connection().close() ; 
				session.close();
			}
		}else{
			log.error("Null query error ");
			throw new HibernateException("Query cannot be null or empty.");
		}

	}

	/**
	 * This method returns the single result for the given query as a Object.
	 * @author Henry Martin 
	 * @since July 5, 2007 
	 * @param String query
	 * @return result object
	 * @throws HibernateException
	 * @throws SQLException 
	 */
	public Object getObjectByQuery(String query)throws HibernateException, SQLException{
		Session session =getSession();
		Query queryObj = null;
		if(query!=null && !"".equals(query.trim())){
			try{		   	
				queryObj = session.createQuery(query);
				Object obj = null;
				if(queryObj!=null)
					obj = queryObj.uniqueResult();

				return obj;
			}catch(HibernateException he){
				throw new HibernateException("Invalid query : "+query);
			}catch(Exception e){
				throw new HibernateException(e);
			} finally {
				session.connection().close() ; 
				session.close();
			}
		}else
			throw new HibernateException("Query cannot be null or empty.");

	}

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
	public Object getObjectById(Class className, Serializable id)throws HibernateException{

		Session session =getSession();
		Object obj = null;
		try{
			obj = session.get(className,id);
		}catch(HibernateException e){
			log.error("Some error occured while getting the object by id: "+e);
			throw e;
		}
		return obj;
	}

	/**
	 * This method returns the list of objects by the class name.	 
	 * @author Henry Martin 
	 * @since July 30, 2007 
	 * @param Class className	 
	 * @return resultList
	 * @throws Exception 
	 */
	public List getObjectsByClass(Class className)throws Exception{

		String query = "from "+className.getName()+" cn";

		List resultList = null;
		try{
			resultList = getObjectsByQuery(query);
		}catch (HibernateException e) {
			throw new HibernateException(e);
		}
		return resultList;
	}

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
	throws HibernateException, IndexOutOfBoundsException
	{
		if(firstResult < 1 || pageSize < 1)
			throw new IndexOutOfBoundsException("Page boundaries must both be positive integers");

		Query hq = null;

		try{

			hq = getSession().createQuery(query);
			hq.setFirstResult(firstResult - 1);
			hq.setMaxResults(pageSize);
			List results =  hq.list();

			return results;
		}catch(HibernateException e){          
			log.error("Hibernate Exception caught during query: " + query, e);
			throw new HibernateException(e);
		}
	}

	/**
	 * This method returns the hibernate transaction.
	 * @author Henry Martin 
	 * @since July 30, 2007		 
	 * @return Transaction	  
	 * @throws HibernateException
	 */
	public Transaction getTransaction() throws HibernateException{

		try{
			return getSession().getTransaction();
		}catch (HibernateException e) {
			log.error("Error getting transaction: ", e);
			throw new HibernateException(e);
		}
	}

	/**
	 * This method makes the object persistent.
	 * @author Henry Martin 
	 * @since July 5, 2007 
	 * @param Object obj
	 * @return key	  
	 * @throws HibernateException
	 * @throws SQLException 
	 */
	public Serializable saveObject(Object obj)throws HibernateException, SQLException{

		Serializable key = null;
		Session sess=null;
		try{			
			sess = getSession();			
			key = sess.save(obj);		
			sess.flush();
			log.info("object saved successfully.");
		}catch (HibernateException e) {
			log.error("Error while saving the object: ", e);
			throw new HibernateException(e);
		} finally {
			  sess.connection().close() ; 
			  sess.close();
		   }
		return key;
	}
	
	/**
	 * @author 53414
	 */
	public void saveOrUpdateObject(Object obj)throws HibernateException, SQLException{

		Session sess=null;
		try{			
			sess = getSession();			
			sess.saveOrUpdate(obj);		
			sess.flush();
			log.info("object saved successfully.");
		}catch (HibernateException e) {
			log.error("Error while saving the object: ", e);
			throw new HibernateException(e);
		} finally {
			  sess.connection().close() ; 
			  sess.close();
		   }
	
		
	}


	/**
	 * This method updates the object in the database.
	 * @author Henry Martin 
	 * @since July 30, 2007 
	 * @param Object obj
	 * @return key	  
	 * @throws HibernateException
	 * @throws SQLException 
	 */
	public Object updateObject(Object obj)throws HibernateException, SQLException{

		Object key = null;
		Session sess =null;
		try{			
			sess = getSession();			
			key = sess.merge(obj);		    
			log.info("object updated successfully.");
		}catch (HibernateException e) {
			log.error("Error while updating the object: ", e);
			throw new HibernateException(e);
		}finally{
			sess.connection().close() ; 
			sess.close();
		}
		return key;
	}

	/**
	 * This method deletes the object from the database.
	 * @author Henry Martin 
	 * @since July 31, 2007 
	 * @param Object obj	   
	 * @throws HibernateException
	 */
	public void deleteObject(Object obj)throws HibernateException{

		try{			
			Session sess = getSession();			
			sess.delete(obj);		    
			log.info("object deleted successfully.");
		}catch (HibernateException e) {
			log.error("Error while deleting the object: ", e);
			throw new HibernateException(e);
		}		
	}

	/**
	 * This method gets the total number of records for the given class 
	 * from the corresponding table.
	 * @author Henry Martin 
	 * @since Aug 03, 2007 
	 * @param Class className
	 * @return int rowCount	   
	 * @throws HibernateException
	 */
	public int getRowCountByClass(Class className)throws HibernateException{

		String query = "select count(*) from "+className.getName()+" cn ";
		int rowCount = 0;
		try{			
			Session sess = getSession();			
			rowCount = ((Long)sess.createQuery(query).iterate().next()).intValue();		    
			log.info("rows returned  successfully.");
		}catch (HibernateException e) {
			log.error("Error while getting row count for the class : "+className, e);
			throw new HibernateException(e);
		}		
		return rowCount;
	}

	/**
	 * This method returns the number of rows for the given query.
	 * @author Henry Martin 
	 * @since July 31, 2007 
	 * @param String query
	 * @return int rowCount
	 * @throws HibernateException
	 */
	public int getRowCountByQuery(String query)throws HibernateException{

		int rowCount = 0;
		try{			
			Session sess = getSession();
			sess.beginTransaction();
			rowCount = ((Long)sess.createQuery(query).iterate().next()).intValue();		    
			sess.getTransaction().commit();
			log.info("rows returned  successfully.");
		}catch (HibernateException e) {
			log.error("Error while getting row count for the query : "+query, e);
			throw new HibernateException(e);
		}		
		return rowCount;	
	}
	/*	
	 *//**
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
	 *//*
	public List executeProcedure(String procName, Object inParamList[])throws HibernateException,MissingResourceException,SQLException,Exception{

		if(procName!=null){
		  procName = procName.trim(); 
		  if("".equals(procName)){
		  	log.error("******** Error Procedure Name Is Empty *******");
		    throw new HibernateException("Procedure name required.");
		  }
		}else{
		    log.error("******** Error Procedure Name Is Null *******");
			throw new HibernateException("Procedure name required.");
		}

		procName = procName.toUpperCase();
		String outParamStr = null;
		try {	
			Check if procedure name is registered in thr properties file.
			outParamStr = registeredProcs.getString(procName);
		}catch (MissingResourceException mre) {			
			log.error("Procedure Not registered in the resource file : "+mre);
			throw mre;
		}catch (Exception e) {			
			log.error("Some error occured while getting the procedure name from the resource file : "+e);
			throw e;
		}

		String []outParamTypes = null;

		Get the out parameter type list as a string array.
		if(outParamStr!=null)
		   outParamTypes = outParamStr.split(",");

		Logic to make a procedure call statement.
		StringBuffer procCall = new StringBuffer("{call ");
		             procCall.append(procName);
		             procCall.append('(');

		if(inParamList!=null){
		   for(int i=0;i<inParamList.length;i++){
		   	procCall.append("?,");
		   }
		} 		   	

		if(outParamTypes!=null && outParamTypes.length>0){
		  for(int i=0;i<outParamTypes.length;i++){	
		    if(!"".equals(outParamTypes[i].trim())){
		     procCall.append("?,");  	     	
		    }
		   }
		  }

		if(procCall.charAt(procCall.length()-1)==',')
		   procCall.setCharAt(procCall.length()-1,')');
		else
		   procCall.append(')');

		procCall.append('}');

		log.info("Procedure call is : "+procCall.toString());
		//Connection con = null;
	try{			
		Session sess = getSession();
		//DataSource ds = null;

		//InitialContext ic = new InitialContext();
		//ds = (DataSource)ic.lookup("java:comp/env/jdbc/nrisr");

		Prepare a procedure call.
		CallableStatement cs = sess.connection().prepareCall(procCall.toString());
		//con = ds.getConnection();
		//CallableStatement cs =con.prepareCall(procCall.toString());

		int index = 1;
		if(inParamList!=null){
		   for(int i=0;i<inParamList.length;i++){
		   	   Set the IN parameters.
		   	   cs.setObject(index,inParamList[i]);
		       index++;
		   }
		}		

		Variable to hold the start index of the OUT parameter. 
		int outParamIndex = 0;

		if(outParamTypes!=null){
		  for(int i=0;i<outParamTypes.length;i++){	
		     if(!"".equals(outParamTypes[i].trim())){
			    if(outParamIndex==0)
			    	outParamIndex = index;	
		     	Register the OUT parameter type.
			    cs.registerOutParameter(index,getParamType(outParamTypes[i].trim()));
		        index++;
		        }
		       }
		     }

		Execute the procedure.
		cs.execute();	

		List resultsList = null;

		if(outParamIndex>0){
			resultsList = new ArrayList(index-outParamIndex);
		  for(int i=outParamIndex;i<index;i++)
		  	  resultsList.add(cs.getObject(i));
		}

		return resultsList;
	}catch(SQLException e){
		log.error("SQL exception occued while executing procedure ",e);
		throw e;
	}catch(Exception e){
		log.error("Some error occued while executing procedure ",e);
		throw e;
	}finally{
		if(con!=null && !con.isClosed()){
		   con.close();	
		   log.info("Closing connection.");
		}
	}

}

	  */	/**
	  * This method is used to return the data type of the OUT parameter.
	  * @author Henry Martin 
	  * @since Aug 10, 2007 
	  * @param String type	 
	  * @return int	 
	  */
	private int getParamType(String type){

		if("String".equalsIgnoreCase(type) || "Varchar".equalsIgnoreCase(type))
			return Types.VARCHAR;
		/*else if("Integer".equalsIgnoreCase(type) || "int".equalsIgnoreCase(type) ||
				"Number".equalsIgnoreCase(type))
			    return Types.INTEGER;
		else if("Long".equalsIgnoreCase(type))
			    return Types.BIGINT;
		else if("Float".equalsIgnoreCase(type))
		        return Types.FLOAT;*/
		else if(type.matches("Double|Float|Long|Number|int|Integer"))
			return Types.DOUBLE;
		else if("Boolean".equalsIgnoreCase(type))
			return Types.BOOLEAN;
		else if("Char".equalsIgnoreCase(type) || "Character".equalsIgnoreCase(type))
			return Types.CHAR;

		return 0;
	}

	/**
	 * This method is used to execute native sql query. If the query returns single result, this method
	 * will return a list of size one. 
	 * @author Henry Martin 
	 * @since Aug 14, 2007 
	 * @param String query	 	 
	 * @return List
	 * @throws SQLException 
	 * @throws Hibernateexception	 
	 */
	public List executeNativeQuery(String query) throws HibernateException, SQLException{

		Session sess = null;
		try{			
			 sess = getSession();			
			List resultList = sess.createSQLQuery(query).list();	    
			log.info("Native query executed successfully.");
			return resultList;
		}catch (HibernateException e) {
			log.error("Error while executing native query : "+query, e);
			throw new HibernateException(e);
		}finally{
			sess.connection().close() ; 
			sess.close();
		} 		

	}	

	/**
	 * This method is used to execute native sql query. If the query returns single result, this method
	 * will return a list of size one. 
	 * @author Henry Martin 
	 * @since Aug 14, 2007 
	 * @param String query	 	 
	 * @return List
	 * @throws Hibernateexception	 
	 */
	public int executeNativeQry(String query) throws HibernateException{

		try{			
			Session sess = getSession();			
			int result = sess.createSQLQuery(query).executeUpdate();	    
			log.info("Native query executed successfully.");
			return result;
		}catch (HibernateException e) {
			log.error("Error while executing native query : "+query, e);
			throw new HibernateException(e);
		}		

	}

	public String executeBatchQuery(String queries[])throws HibernateException{

		if(queries==null || queries.length==0){
			log.error("Empty batch queries.");
			throw new HibernateException("Batch queries cannot be empty. ");       
		} 

		try{			
			Session sess = getSession();	
			for(int i=0;i<queries.length;i++){
				if(queries[i]!=null && !"".equals(queries[i].trim()))
					sess.createSQLQuery(queries[i].trim()).executeUpdate();	     

				if(i%100==0){
					log.info("Flushing session at : "+i);
					sess.flush();
					sess.clear();
				}
			}
			log.info("Native batch query executed successfully.");
			return "Success";
		}catch (HibernateException e) {
			log.error("Error while executing native batch query : "+e);
			throw new HibernateException(e);
		}	        
	}


	public String executeBatchInsert(StringBuffer queries[])throws HibernateException{

		if(queries==null || queries.length==0){
			log.error("Empty batch Insert queries.");
			throw new HibernateException("Batch Insert queries cannot be empty. ");       
		} 

		try{			
			Session sess = getSession();	
			for(int i=0;i<queries.length;i++){
				if(queries[i]!=null && !"".equals(queries[i]))
					sess.createSQLQuery(queries[i].toString().trim()).executeUpdate();	     

				if(i%100==0){
					log.info("Flushing session at : "+i);
					sess.flush();
					sess.clear();
				}
			}
			log.info("Native batch Insert executed successfully.");
			return "Success";
		}catch (HibernateException e) {
			log.error("Error while executing batch Insert : "+e);
			throw new HibernateException(e);
		}	        
	}


	public Query getParametrizedQuery(String query) throws HibernateException{
		try{			
			return getSession().createQuery(query);		    
		}catch (HibernateException e) {
			log.error("Error while getting Query object: ", e);
			throw new HibernateException(e);
		}
	}

	/**
	 * This method closes the session object.
	 * @author Henry Martin 
	 * @since July 30, 2007	 	  
	 * @throws HibernateException
	 */
	public void closeSession() throws HibernateException{
		try{			
			getSession().close();		    
		}catch (HibernateException e) {
			log.error("Error while closing the session: ", e);
			throw new HibernateException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.infotech.persistent.configuration.Persister#isOpen()
	 */
	public boolean isOpen(){		

		return !sessionFactory.isClosed();	
	}

	/* (non-Javadoc)
	 * @see com.infotech.persistent.configuration.Persister#close()
	 */
	public void close() {		
		sessionFactory.close();
	}

	public Object loadObjectById(Class className, Serializable id)throws HibernateException{

		Session session =getSession();
		Object obj = null;
		try{
			obj = session.load(className,id);

			System.out.println("printing date after loading : "+new Date());
			//log.info("Class of loading the object by id: "+obj.getClass());
		}catch(HibernateException e){
			log.error("Some error occured while loading the object by id: "+e);
			throw e;
		}
		return obj;
	}
	
	
	/**@author CVAS2273
	 * @since 5TH - JAN - 2010
	 * @param query
	 * @throws HibernateException
	 * @throws SQLException
	 * This method is used to execute native sql query for update
	 */
	public void updateDetailsByNativeQry(String query) throws HibernateException, SQLException{
		
		Session sess = null;
		try{			
			sess = getSession();			
			sess.createSQLQuery(query).executeUpdate();	    
			log.info("Native query executed successfully.");
			
		}catch (HibernateException e) {
			log.error("Error while executing native query : "+query, e);
			throw new HibernateException(e);
		}finally{
			sess.connection().close() ; 
			sess.close();
		}		

	}
	
	
}
