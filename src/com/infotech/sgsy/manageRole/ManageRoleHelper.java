package com.infotech.sgsy.manageRole;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

public class ManageRoleHelper{
	
	public void getVillageByBlockCode(HttpServletRequest request,HttpServletResponse response){
		
		ManageRoleService manageRoleService = new ManageRoleServiceImpl();
		String blockCode = request.getParameter("block");
		String loginId = request.getParameter("loginId");
		String roleCode = request.getParameter("roleCode");

		Map<String, Object> blockMap = null;
		try {
			blockMap = manageRoleService.getVillageList(blockCode,loginId,roleCode);
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
	
	public void getSelectedVillageByBlockCode(HttpServletRequest request,HttpServletResponse response) {
		
		ManageRoleService manageRoleService = new ManageRoleServiceImpl();
		String blockCode = request.getParameter("block");
		String loginId = request.getParameter("loginId");
		String roleCode = request.getParameter("roleCode");
		
		Map<String, Object> blockMap = null;
		try {
			blockMap = manageRoleService.getSelectedVillageList(blockCode,loginId,roleCode);
			request.getSession().setAttribute("selectedVMap", blockMap);
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

	public void getBlocksByDistrictCode(HttpServletRequest request,	HttpServletResponse response) {
		ManageRoleService manageRoleService = new ManageRoleServiceImpl();
		String districtCode = request.getParameter("district");
		String loginId = request.getParameter("loginId");
		String roleCode = request.getParameter("roleCode");

		Map<String, Object> districtMap = null;
		try {
			districtMap = manageRoleService.getBlocksList(districtCode,loginId,roleCode);
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
			sb.append("<BlockList>");
			sb.append("<valid>true</valid>");
			for (Iterator<String> it = districtMap.keySet().iterator(); it.hasNext();) {
				String key = (String) it.next();
				String value = (String) districtMap.get(key);
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
	
	public void getBlockChosenByDistrictCode(HttpServletRequest request,HttpServletResponse response) {
		ManageRoleService manageRoleService = new ManageRoleServiceImpl();
		String districtCode = request.getParameter("district");
		String loginId = request.getParameter("loginId");
		String roleCode = request.getParameter("roleCode");

		Map<String, Object> districtMap = null;
		try {
			districtMap = manageRoleService.getSelectedBlocksList(districtCode,loginId,roleCode);
			request.getSession().setAttribute("selectedBMap", districtMap);
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
			sb.append("<BlockList>");
			sb.append("<valid>true</valid>");
			for (Iterator<String> it = districtMap.keySet().iterator(); it.hasNext();) {
				String key = (String) it.next();
				String value = (String) districtMap.get(key);
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

	public void getBlockSelectedByDistrictCode(HttpServletRequest request,HttpServletResponse response) {
		ManageRoleService manageRoleService = new ManageRoleServiceImpl();
		String districtCode = request.getParameter("district");
		String loginId = request.getParameter("loginId");
		String roleCode = request.getParameter("roleCode");

		Map<String, Object> districtMap = null;
		try {
			districtMap = manageRoleService.getSelectedBlocksList(districtCode,loginId,roleCode);
			request.getSession().setAttribute("selectedBMap", districtMap);
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
			sb.append("<BlockList>");
			sb.append("<valid>true</valid>");
			for (Iterator<String> it = districtMap.keySet().iterator(); it.hasNext();) {
				String key = (String) it.next();
				String value = (String) districtMap.get(key);
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
	
	public void getDistrictsByStateCode(HttpServletRequest request,	HttpServletResponse response) {
		ManageRoleService manageRoleService = new ManageRoleServiceImpl();
		
		String stateCode = request.getParameter("state");
		String loginId = request.getParameter("loginId");
		String roleCode = request.getParameter("roleCode");
		Map<String, Object> stateMap = null;
		try {
			stateMap = manageRoleService.getDistrictsList(stateCode,loginId,roleCode);
			
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

		if (stateMap != null && stateMap.size() != 0) {
			sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			sb.append("<DistrictList>");
			sb.append("<valid>true</valid>");
			for (Iterator<String> it = stateMap.keySet().iterator(); it.hasNext();) {
				String key = (String) it.next();
				String value = (String) stateMap.get(key);
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

	public void getSelectedDistrictsByStateCode(HttpServletRequest request,HttpServletResponse response) {
		ManageRoleService manageRoleService = new ManageRoleServiceImpl();
		
		String stateCode = request.getParameter("state");
		String loginId = request.getParameter("loginId");
		String roleCode = request.getParameter("roleCode");
		Map<String, Object> stateMap = null;
		try {
			stateMap = manageRoleService.getSelectedDistrictsList(stateCode,loginId,roleCode);
			request.getSession().setAttribute("selectedDMap", stateMap);
			
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

		if (stateMap != null && stateMap.size() != 0) {
			sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			sb.append("<DistrictList>");
			sb.append("<valid>true</valid>");
			for (Iterator<String> it = stateMap.keySet().iterator(); it.hasNext();) {
				String key = (String) it.next();
				String value = (String) stateMap.get(key);
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
}