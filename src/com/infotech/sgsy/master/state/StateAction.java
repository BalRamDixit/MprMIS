package com.infotech.sgsy.master.state;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.infotech.sgsy.login.LoginVO;

public class StateAction extends DispatchAction {

	private StateDaoImp stateDaoImp = new StateDaoImp();

	public ActionForward show(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<StateVO> stateList = new ArrayList<StateVO>();
		try {
			for(int indexCount=1;indexCount<=2474;indexCount++){
				System.out.println("INSERT INTO public.batch_creation(id, tc_trade_id, batch_id, batch_start_date,batch_freeze_date, batch_size, batch_end_date) VALUES ('"+indexCount+"','"+indexCount+"','batch"+indexCount+"','2017-04-03','2017-04-05',35,'2017-04-06');");
				
			}
			stateList = (List<StateVO>) stateDaoImp.getList(StateVO.class);
			System.out.println("size of list--->>    " + stateList.size());
			StateForm stateForm = new StateForm();
			request.setAttribute("stateBean", stateForm);
		} catch (Exception e) {

			e.printStackTrace();

		}
		request.setAttribute("stateList", stateList);
		request.setAttribute("formName", "State Master");
		return mapping.findForward("stateMastershowPage");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		StateForm stateform = (StateForm) form;
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		try {

			stateDaoImp.saveOrUpdateStateDetails(stateform,loginVO);

		} catch (Exception e) {

			e.printStackTrace();

		}

		List<StateVO> stateList = (List<StateVO>) stateDaoImp.getList(StateVO.class);
		request.setAttribute("stateList", stateList);

		request.setAttribute("formName", "State Master");
		return mapping.findForward("stateMastershowPage");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		StateForm stateform = (StateForm) form;
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		try {

			stateDaoImp.saveOrUpdateStateDetails(stateform,loginVO);

		} catch (Exception e) {

			e.printStackTrace();

		}
		List<StateVO> stateList = (List<StateVO>) stateDaoImp.getList(StateVO.class);
		request.setAttribute("stateList", stateList);
		request.setAttribute("formName", "State Master");
		return mapping.findForward("stateMastershowPage");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		StateForm stateform = (StateForm) form;
		try {
			String state_id = request.getParameter("id");
			StateVO statevo = (StateVO) stateDaoImp.getResultById(StateVO.class, state_id);
			request.setAttribute("stateBean", statevo);
		} catch (Exception e) {

			e.printStackTrace();

		}

		List<StateVO> stateList = (List<StateVO>) stateDaoImp.getList(StateVO.class);
		request.setAttribute("stateList", stateList);
		request.setAttribute("formName", "State Master");
		return mapping.findForward("stateMastershowPage");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
            String message="delete succesfully";
		try {

			String state_id = request.getParameter("id");
			boolean result = stateDaoImp.delete(StateVO.class, state_id);
			
		} catch (Exception e) {
			message= "unable to delete";
		}
		request.setAttribute("message",message );
		List<StateVO> stateList = (List<StateVO>) stateDaoImp.getList(StateVO.class);
		//System.out.println(stateList);
		request.setAttribute("stateList", stateList);
		request.setAttribute("stateBean", new StateForm());
		request.setAttribute("formName", "State Master");
		return mapping.findForward("stateMastershowPage");
	}

}
