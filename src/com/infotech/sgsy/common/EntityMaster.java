package com.infotech.sgsy.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.userManagement.UserDAOImpl;
import com.infotech.sgsy.util.Constants;
import com.infotech.sgsy.util.SGSYConstants;

public class EntityMaster {
	//DEEPAK BISTH
	//GET STATE DISTRICTS AND BLOCKSLIST 
	//AND SET LISTS IN REQUEST
	public void showListForStateDistrictBlock(String roleCode, LocationVO locationVO,
			HttpServletRequest request)
throws Exception {
		//HttpSession session = request.getSession();
	
	@SuppressWarnings("rawtypes")
	List stateList, districtList,blockslist; 
	LoginVO dto=(LoginVO)request.getSession().getAttribute("loginVO");
	
	UserDAOImpl dao=new UserDAOImpl();
	
	try{

	     if(roleCode.equals(SGSYConstants.ENTITYTYPE_STATE) ) {	         
	    	 stateList=dao.getEntityList("ST", SGSYConstants.ENTITYTYPE_STATE, dto);	 				
	 			request.setAttribute("stateList",stateList);
	 			 			 
	 		 	 
	 			} else if(roleCode.equals(SGSYConstants.ENTITYTYPE_DISTRICT)  ) {	 				
	 				stateList=dao.getEntityList("ST", SGSYConstants.ENTITYTYPE_STATE, dto);	 				
	 				request.setAttribute("stateList",stateList);
	 			
	 				
	 				if(locationVO.getStateCode()!=null){
	 					dto.setStateCode(locationVO.getStateCode());
	 					dto.setStateName(locationVO.getStateName());
	 					districtList=dao.getEntityList(roleCode, SGSYConstants.ENTITYTYPE_DISTRICT, dto);
	 				 	request.setAttribute("districtList",districtList);
	 				}
	 				
	 			} 
	 			else if(roleCode.equals(SGSYConstants.ENTITYTYPE_BLOCK)  ) {
	 				if(dto.getLevelCode().equals("0")){
	 				stateList=dao.getEntityList("ST", SGSYConstants.ENTITYTYPE_STATE, dto);	 				
	 				request.setAttribute("stateList",stateList);
	 				}
	 				
	 				if(locationVO.getStateCode()!=null){	
	 					if(dto.getLevelCode().equals("0")||dto.getLevelCode().equals("2")){
	 					dto.setStateCode(locationVO.getStateCode());
	 					dto.setStateName(locationVO.getStateName());
	 					districtList=dao.getEntityList("DT",SGSYConstants.ENTITYTYPE_DISTRICT,dto);
	 				 	request.setAttribute("districtList",districtList);	 				
	 					}
	 				} 
	 				
	 				if(locationVO.getDistrictCode()!=null){
	 					dto.setDistrictCode(locationVO.getDistrictCode());						
	 					blockslist=dao.getEntityList(roleCode,SGSYConstants.ENTITYTYPE_BLOCK,dto); 					
	 					request.setAttribute(Constants.COLLECTION_GETBLOCK , blockslist);
						
	 				}
	 			}	
				
}
catch(Exception e){e.printStackTrace();}
	
}


}
