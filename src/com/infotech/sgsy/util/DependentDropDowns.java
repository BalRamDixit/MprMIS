package com.infotech.sgsy.util;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.infotech.sgsy.master.district.DistrictDAO;
import com.infotech.sgsy.master.district.DistrictVO;

public class DependentDropDowns {
	Log log = LogFactory.getLog(getClass());

	public void getDistrictByState(HttpServletRequest request, HttpServletResponse response) {
		String stateCode = request.getParameter("stateCode");
		response.setContentType("text/xml");
		try {
			PrintWriter out = response.getWriter();
			List<DistrictVO> districtList = new DistrictDAO().getDistrictByState(stateCode);
			StringBuilder outputText = new StringBuilder();
			outputText.append("<select name=district id=cmbDistrict onChange=getBlockByDistrictCode()>");
			outputText.append("<option value=").append("").append(">").append("--Select--").append("</option>");
			for (DistrictVO district : districtList) {
				outputText.append("<option value=").append(district.getDistrictCode());
				outputText.append(">").append(district.getDistrictName()).append("</option>");
			}
			outputText.append("</select>");
			out.write(outputText.toString());
			out.flush();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
