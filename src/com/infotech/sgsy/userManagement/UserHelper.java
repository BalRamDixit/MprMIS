package com.infotech.sgsy.userManagement;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.manageRole.ManageRoleService;
import com.infotech.sgsy.manageRole.ManageRoleServiceImpl;

public class UserHelper {
	
	public void getDistrictByStateCode(HttpServletRequest request,HttpServletResponse response){
		
		UserService userService = new UserServiceImpl();
		String stateCode = request.getParameter("state");
		Map<String, Object> districtMap = null;
		try {
			districtMap = userService.getDistrictList(stateCode);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		response.setContentType("text/xml");
		response.setHeader("Pragma", "no-cache");
		response.addHeader("Cache-Control", "must-revalidate");
		response.addHeader("Cache-Control", "no-cache");
		response.addHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);

		StringBuffer sb = new StringBuffer();

		if (districtMap != null && districtMap.size() != 0) {
			sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			sb.append("<DistrictList>");
			sb.append("<valid>true</valid>");
			for (Iterator<String> it = districtMap.keySet().iterator(); it.hasNext();) {
				String key = (String) it.next();
				String value = (String) districtMap.get(key);
				sb.append("<DistrictDetails>");
				sb.append("<Districtcode>" + key + "</Districtcode>");
				sb.append("<DistrictName>" + value + "</DistrictName>");
				sb.append("</DistrictDetails>");
			}
			sb.append("</DistrictList>");
			System.out.println("StateList"+sb.toString());

			try {
				response.getWriter().write(sb.toString());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			sb.append("<DistrictList>");
			sb.append("<valid>false</valid>");
			sb.append("</DistrictList>");
			try {
				response.getWriter().write(sb.toString());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
public void getUserByCode(HttpServletRequest request,HttpServletResponse response){
		
		ManageRoleService manageRoleService = new ManageRoleServiceImpl();
		String stateCode = request.getParameter("entity");
		Map<String, Object> userMap = null;
		try {
			userMap = manageRoleService.getUserList(stateCode,request);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		response.setContentType("text/xml");
		response.setHeader("Pragma", "no-cache");
		response.addHeader("Cache-Control", "must-revalidate");
		response.addHeader("Cache-Control", "no-cache");
		response.addHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);

		StringBuffer sb = new StringBuffer();

		if (userMap != null && userMap.size() != 0) {
			sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			sb.append("<UserList>");
			sb.append("<valid>true</valid>");
			for (Iterator<String> it = userMap.keySet().iterator(); it.hasNext();) {
				String key = (String) it.next();
				String value = (String) userMap.get(key);
				sb.append("<UserDetails>");
				sb.append("<UserCode>" + key + "</UserCode>");
				sb.append("<UserName>" + value + "</UserName>");
				sb.append("</UserDetails>");
			}
			sb.append("</UserList>");

			try {
				response.getWriter().write(sb.toString());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			sb.append("<UserList>");
			sb.append("<valid>false</valid>");
			sb.append("</UserList>");
			try {
				response.getWriter().write(sb.toString());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

public void getLevelOfRole(HttpServletRequest request,HttpServletResponse response){
	
	ManageRoleService manageRoleService = new ManageRoleServiceImpl();
	String levelCode = request.getParameter("level");
	Map<String, Object> userMap = null;
	try {
		userMap = manageRoleService.getLevelRoleList(levelCode);
	} catch (Exception ex) {
		ex.printStackTrace();
	}

	response.setContentType("text/xml");
	response.setHeader("Pragma", "no-cache");
	response.addHeader("Cache-Control", "must-revalidate");
	response.addHeader("Cache-Control", "no-cache");
	response.addHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);

	StringBuffer sb = new StringBuffer();

	if (userMap != null && userMap.size() != 0) {
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		sb.append("<RoleList>");
		sb.append("<valid>true</valid>");
		for (Iterator<String> it = userMap.keySet().iterator(); it.hasNext();) {
			String key = (String) it.next();
			String value = (String) userMap.get(key);
			sb.append("<RoleDetails>");
			sb.append("<levelCodeA>" + key + "</levelCodeA>");
			sb.append("<roleDetails>" + value + "</roleDetails>");
			sb.append("</RoleDetails>");
		}
		sb.append("</RoleList>");

		try {
			response.getWriter().write(sb.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	} else {
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		sb.append("<RoleList>");
		sb.append("<valid>false</valid>");
		sb.append("</RoleList>");
		try {
			response.getWriter().write(sb.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

public void getRole(HttpServletRequest request,HttpServletResponse response){
	
	ManageRoleService manageRoleService = new ManageRoleServiceImpl();
	String levelCode = request.getParameter("levelA");
	Map<String, Object> userMap = null;
	try {
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		if(loginVO.getEntityCode().length() == 2){
			userMap = manageRoleService.getRole(levelCode, "state");
		}else{
			userMap = manageRoleService.getRole(levelCode);
		}
	} catch (Exception ex) {
		ex.printStackTrace();
	}

	response.setContentType("text/xml");
	response.setHeader("Pragma", "no-cache");
	response.addHeader("Cache-Control", "must-revalidate");
	response.addHeader("Cache-Control", "no-cache");
	response.addHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);

	StringBuffer sb = new StringBuffer();

	if (userMap != null && userMap.size() != 0) {
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		sb.append("<RolesList>");
		sb.append("<valid>true</valid>");
		for (Iterator<String> it = userMap.keySet().iterator(); it.hasNext();) {
			String key = (String) it.next();
			String value = (String) userMap.get(key);
			sb.append("<RoleDetail>");
			sb.append("<RoleCode>" + key + "</RoleCode>");
			sb.append("<RoleName>" + value + "</RoleName>");
			sb.append("</RoleDetail>");
		}
		sb.append("</RolesList>");
System.out.println(sb.toString());
		try {
			response.getWriter().write(sb.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	} else {
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		sb.append("<RolesList>");
		sb.append("<valid>false</valid>");
		sb.append("</RolesList>");
		try {
			response.getWriter().write(sb.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}


	public void getBlockByDistrictCode(HttpServletRequest request,HttpServletResponse response) {
		UserService userService = new UserServiceImpl();
		String districtCode = request.getParameter("district");

		Map<String, Object> blockMap = null;
		try {
			blockMap = userService.getBlockList(districtCode);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		response.setContentType("text/xml");
		response.setHeader("Pragma", "no-cache");
		response.addHeader("Cache-Control", "must-revalidate");
		response.addHeader("Cache-Control", "no-cache");
		response.addHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);

		StringBuffer sb = new StringBuffer();

		if (blockMap != null && blockMap.size() != 0) {
			sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			sb.append("<BlockList>");
			sb.append("<valid>true</valid>");
			for (Iterator<String> it = blockMap.keySet().iterator(); it.hasNext();) {
				String key = (String) it.next();
				String value = (String) blockMap.get(key);
				sb.append("<BlockDetails>");
				sb.append("<Blockcode>" + key + "</Blockcode>");
				sb.append("<BlockName>" + value + "</BlockName>");
				sb.append("</BlockDetails>");
			}
			sb.append("</BlockList>");

			try {
				response.getWriter().write(sb.toString());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			sb.append("<BlockList>");
			sb.append("<valid>false</valid>");
			sb.append("</BlockList>");
			try {
				response.getWriter().write(sb.toString());

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void getVillageByBlockCode(HttpServletRequest request,HttpServletResponse response) {
		UserService userService = new UserServiceImpl();
		String blockCode = request.getParameter("block");

		Map<String, Object> blockMap = null;
		try {
			blockMap = userService.getVillageList(blockCode);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		response.setContentType("text/xml");
		response.setHeader("Pragma", "no-cache");
		response.addHeader("Cache-Control", "must-revalidate");
		response.addHeader("Cache-Control", "no-cache");
		response.addHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);

		StringBuffer sb = new StringBuffer();

		if (blockMap != null && blockMap.size() != 0) {
			sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			sb.append("<VillageList>");
			sb.append("<valid>true</valid>");
			for (Iterator<String> it = blockMap.keySet().iterator(); it
					.hasNext();) {
				String key = (String) it.next();
				String value = (String) blockMap.get(key);
				sb.append("<VillageDetails>");
				sb.append("<Villagecode>" + key + "</Villagecode>");
				sb.append("<VillageName>" + value + "</VillageName>");
				sb.append("</VillageDetails>");
			}
			sb.append("</VillageList>");

			try {
				response.getWriter().write(sb.toString());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			sb.append("<VillageList>");
			sb.append("<valid>false</valid>");
			sb.append("</VillageList>");
			try {
				response.getWriter().write(sb.toString());

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}		
	}
	
	public void getBlockPanchayatListByDistrictCode(HttpServletRequest request,HttpServletResponse response) {
		UserService userService = new UserServiceImpl();
		String districtCode = request.getParameter("district");
		LoginVO loginVO= (LoginVO)request.getSession().getAttribute("loginVO");
		String useId= loginVO.getUserid();
		Map<String, Object> blockMap = null;
		try {
			blockMap = userService.getBlockPanchayatListByDistrictCode(useId,districtCode);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		response.setContentType("text/xml");
		response.setHeader("Pragma", "no-cache");
		response.addHeader("Cache-Control", "must-revalidate");
		response.addHeader("Cache-Control", "no-cache");
		response.addHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);

		StringBuffer sb = new StringBuffer();

		if (blockMap != null && blockMap.size() != 0) {
			sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			sb.append("<BlockList>");
			sb.append("<valid>true</valid>");
			for (Iterator<String> it = blockMap.keySet().iterator(); it.hasNext();) {
				String key = (String) it.next();
				String value = (String) blockMap.get(key);
				sb.append("<BlockDetails>");
				sb.append("<Blockcode>" + key + "</Blockcode>");
				sb.append("<BlockName>" + value + "</BlockName>");
				sb.append("</BlockDetails>");
			}
			sb.append("</BlockList>");
			//System.out.println("sb====>"+sb.toString());
			
			try {
				response.getWriter().write(sb.toString());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			sb.append("<BlockList>");
			sb.append("<valid>false</valid>");
			sb.append("</BlockList>");
			try {
				response.getWriter().write(sb.toString());

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
	}
	
	public void getVillagePanchayatListByBlockCode(HttpServletRequest request,HttpServletResponse response) {
		UserService userService = new UserServiceImpl();
		String blockCode = request.getParameter("block");
		LoginVO loginVO= (LoginVO)request.getSession().getAttribute("loginVO");
		String useId= loginVO.getUserid();
		
		
		Map<String, Object> blockMap = null;
		try {
			blockMap = userService.getVillagePanchayatListByBlockCode(useId,blockCode);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		response.setContentType("text/xml");
		response.setHeader("Pragma", "no-cache");
		response.addHeader("Cache-Control", "must-revalidate");
		response.addHeader("Cache-Control", "no-cache");
		response.addHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);

		StringBuffer sb = new StringBuffer();

		if (blockMap != null && blockMap.size() != 0) {
			sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			sb.append("<VillageList>");
			sb.append("<valid>true</valid>");
			for (Iterator<String> it = blockMap.keySet().iterator(); it
					.hasNext();) {
				String key = (String) it.next();
				String value = (String) blockMap.get(key);
				sb.append("<VillageDetails>");
				sb.append("<Villagecode>" + key + "</Villagecode>");
				sb.append("<VillageName>" + value + "</VillageName>");
				sb.append("</VillageDetails>");
			}
			sb.append("</VillageList>");
			//System.out.println("sb====>"+sb.toString());
			try {
				response.getWriter().write(sb.toString());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			sb.append("<VillageList>");
			sb.append("<valid>false</valid>");
			sb.append("</VillageList>");
			try {
				response.getWriter().write(sb.toString());

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}		
		
	}
		
}