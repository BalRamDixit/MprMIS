package com.infotech.sgsy.master.financialYear;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.infotech.sgsy.common.LocationVO;
import com.infotech.sgsy.common.MasterDAOFactory;
import com.infotech.sgsy.master.month.MonthDAO;
import com.infotech.sgsy.master.state.StateDAO;
import com.infotech.sgsy.util.Constants;
import com.infotech.sgsy.util.SGSYConstants;

public class FinancialYearAction extends DispatchAction{
	ActionMessages message = new ActionMessages();
	 
	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		try{
			
			message.clear();
			FinancialYrDAO financialYrDAO = new FinancialYrDAO();
		 	HttpSession session = request.getSession();
		 	LocationVO locationVO = ((LocationVO) session.getAttribute(SGSYConstants.SGSY_LOCATIONVO));		
			
			List yearList = financialYrDAO.getFinancialYrs(locationVO);
			
			request.setAttribute(Constants.ALL_YEAR, yearList); 
			 
		}catch(Exception e){
			e.printStackTrace();
		}
		return mapping.findForward(Constants.SHOW_PAGE);
	}	
}
