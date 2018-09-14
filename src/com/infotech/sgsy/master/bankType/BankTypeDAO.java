package com.infotech.sgsy.master.bankType;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Transaction; 

import com.infotech.sgsy.common.MasterDAO;
import com.infotech.sgsy.common.MasterVO;
import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;
/*import com.infotech.sgsy.individualSwarojgaris.UP_IndvidualSwaVO;*/
import com.infotech.sgsy.master.bankLevel.BankLevelVO;
import com.infotech.sgsy.util.PropertyModel;

public class BankTypeDAO implements MasterDAO{

	protected final Log log = LogFactory.getLog(getClass());

	public List getBankType(MasterVO masterVO) throws Exception {		

		List results = null;		
		String query=" from com.infotech.sgsy.master.bankType.BankTypeVO ";
		List finalList =  new ArrayList();
		PropertyModel propertyModel=new PropertyModel();
		propertyModel.setPropertyCode("");
		propertyModel.setPropertyValue("Select");
		finalList.add(propertyModel);
		try{
			Persister persister = PersisterImpl.getPersister();	
			Transaction transaction = persister.getTransaction();
			transaction.begin();
			results=(List) persister.getObjectsByQuery(query);
			for(int x=0;x<results.size();x++){				
				propertyModel=new PropertyModel();
				propertyModel.setPropertyCode(((BankTypeVO)results.get(x)).getBankTypeCode());
				propertyModel.setPropertyValue(((BankTypeVO)results.get(x)).getBankTypeDetail());				
				finalList.add(propertyModel);
			}

			transaction.commit();
			return finalList;
		}catch (Exception e) {
			log.error("error in get Groups "+e);
			throw e;
		}	
	}

	public List getBankLevels(String entityCode) throws Exception {		
		List results = null;
		List finalList =  new ArrayList();
		String query=" from com.infotech.sgsy.master.bankLevel.BankLevelVO bk WHERE bk.bankLevelCode IN('1')";
		if(entityCode != null){
			if(entityCode.length() == 2){
				query=" from com.infotech.sgsy.master.bankLevel.BankLevelVO bk WHERE bk.bankLevelCode IN('2')";
			}else if(entityCode.length() == 4){
				query=" from com.infotech.sgsy.master.bankLevel.BankLevelVO bk WHERE bk.bankLevelCode IN('3')";
			}
		}
		PropertyModel propertyModel=new PropertyModel();
		propertyModel.setPropertyCode("");
		propertyModel.setPropertyValue("Select");
		finalList.add(propertyModel);
		try{
			Persister persister = PersisterImpl.getPersister();	
			Transaction transaction = persister.getTransaction();
			transaction.begin();			
			results=(List) persister.getObjectsByQuery(query);
			for(int x=0;x<results.size();x++){				
				propertyModel=new PropertyModel();
				propertyModel.setPropertyCode(((BankLevelVO)results.get(x)).getBankLevelCode());
				propertyModel.setPropertyValue(((BankLevelVO)results.get(x)).getBankLevelName());				
				finalList.add(propertyModel);
			}	
			
			transaction.commit();
			return finalList;
		}catch (Exception e) {
			log.error("error in get Groups "+e);
			throw e;
		}	
	}

	@Override
	public int delete(MasterVO masterVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(MasterVO masterVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(MasterVO masterVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
