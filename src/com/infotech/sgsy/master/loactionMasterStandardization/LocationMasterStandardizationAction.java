package com.infotech.sgsy.master.loactionMasterStandardization;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.infotech.sgsy.common.LocationVO;
import com.infotech.sgsy.login.LoginVO;
import com.infotech.sgsy.master.block.BlockDAO;
import com.infotech.sgsy.master.block.BlockMasterVO;
import com.infotech.sgsy.master.blockCorrection.BlockCorrectionDAO;
import com.infotech.sgsy.master.blockCorrection.BlockCorrectionVO;
import com.infotech.sgsy.master.district.DistrictDAO;
import com.infotech.sgsy.master.grampanchayat.GramPanchayatDAO;
import com.infotech.sgsy.master.grampanchayat.GramPanchayatVO;
import com.infotech.sgsy.master.grampanchayatCorrection.GrampanchayatCorrectionDAO;
import com.infotech.sgsy.master.grampanchayatCorrection.GrampanchayatCorrectionVO;
import com.infotech.sgsy.master.village.VillageDAO;
import com.infotech.sgsy.master.village.VillageVO;
import com.infotech.sgsy.master.villageCorrection.VillageCorrectionDAO;
import com.infotech.sgsy.master.villageCorrection.VillageCorrectionVO;
import com.infotech.sgsy.util.SGSYConstants;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class LocationMasterStandardizationAction extends DispatchAction {
	Log log = LogFactory.getLog(getClass());
	public static Font HEADER_FONT_TOP = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, BaseColor.BLACK);
	public static Font SUB_HEADER_FONT = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, BaseColor.BLUE);
	public static Font TABLE_HEADER_FONT = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, BaseColor.WHITE);
	public static Font BODY_FONT = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, BaseColor.BLACK);
	public static BaseColor TABLE_HEADER_BACKGROUND = WebColors.getRGBColor("#067d4c");
	public static BaseColor BODY_BACKGROUND = WebColors.getRGBColor("#e6fef4");
	
	// Function used to show the location master standardization
	public ActionForward showPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		LocationVO location = (LocationVO)request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		if(loginVO.getLevelCode().equals("2")) {
			request.setAttribute("districtList", new DistrictDAO().getDistrictByState(location.getStateCode()));
		}
		else if(loginVO.getLevelCode().equals("3")) {
			request.setAttribute("districtName", location.getDistrictName());
			request.setAttribute("blockList", new LocationMasterStandardizationUtil().getBlockList(location.getDistrictCode()));
			request.setAttribute("blockShow", "true");
		}
		else if(loginVO.getLevelCode().equals("4")) {
			request.setAttribute("districtName", location.getDistrictName());
			request.setAttribute("blockName", location.getBlockName());
			request.setAttribute("grampanchayatList", new  LocationMasterStandardizationUtil().getGrampanchayatList(location.getBlockCode()));
			request.setAttribute("grampanchayatShow", "true");
		}
		return mapping.findForward("showPage");
	}
	
	public ActionForward districtChange(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		LocationVO location = (LocationVO)request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		if(loginVO.getLevelCode().equals("2")) {
			request.setAttribute("districtList", new DistrictDAO().getDistrictByState(location.getStateCode()));
			request.setAttribute("blockList", new LocationMasterStandardizationUtil().getBlockList(request.getParameter("districtCode")));
		}
		else if(loginVO.getLevelCode().equals("3")) {
			request.setAttribute("districtName", location.getDistrictName());
			request.setAttribute("blockList", new LocationMasterStandardizationUtil().getBlockList(location.getDistrictCode()));
		}
		request.setAttribute("blockShow", "true");
		return mapping.findForward("showPage");
	}
	
	public ActionForward blockChange(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		LocationVO location = (LocationVO)request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		if(loginVO.getLevelCode().equals("2")) {
			request.setAttribute("districtList", new DistrictDAO().getDistrictByState(location.getStateCode()));
			request.setAttribute("blockList", new LocationMasterStandardizationUtil().getBlockList(request.getParameter("districtCode")));
			request.setAttribute("grampanchayatList", new LocationMasterStandardizationUtil().getGrampanchayatList(request.getParameter("blockCode")));
		}
		else if(loginVO.getLevelCode().equals("3")) {
			request.setAttribute("districtName", location.getDistrictName());
			request.setAttribute("blockList", new LocationMasterStandardizationUtil().getBlockList(location.getDistrictCode()));
			request.setAttribute("grampanchayatList", new LocationMasterStandardizationUtil().getGrampanchayatList(request.getParameter("blockCode")));
		}
		else if(loginVO.getLevelCode().equals("4")) {
			//This else if block is only used by the back button on JSP Page.
			request.setAttribute("districtName", location.getDistrictName());
			request.setAttribute("blockName", location.getBlockName());
			request.setAttribute("grampanchayatList", new  LocationMasterStandardizationUtil().getGrampanchayatList(location.getBlockCode()));
		}
		request.setAttribute("blockShow", "false");
		request.setAttribute("grampanchayatShow", "true");
		return mapping.findForward("showPage");
	}
	
	public ActionForward grampanchayatChange(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		LocationVO location = (LocationVO)request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		if(loginVO.getLevelCode().equals("2")) {
			request.setAttribute("districtList", new DistrictDAO().getDistrictByState(location.getStateCode()));
			request.setAttribute("blockList", new LocationMasterStandardizationUtil().getBlockList(request.getParameter("districtCode")));
			request.setAttribute("grampanchayatList", new LocationMasterStandardizationUtil().getGrampanchayatList(request.getParameter("blockCode")));
		}
		else if(loginVO.getLevelCode().equals("3")) {
			request.setAttribute("districtName", location.getDistrictName());
			request.setAttribute("blockList", new LocationMasterStandardizationUtil().getBlockList(location.getDistrictCode()));
			request.setAttribute("grampanchayatList", new LocationMasterStandardizationUtil().getGrampanchayatList(request.getParameter("blockCode")));
		}
		else if(loginVO.getLevelCode().equals("4")) {
			request.setAttribute("districtName", location.getDistrictName());
			request.setAttribute("blockName", location.getBlockName());
			request.setAttribute("grampanchayatList", new LocationMasterStandardizationUtil().getGrampanchayatList(location.getBlockCode()));
		}
		request.setAttribute("blockShow", "false");
		request.setAttribute("grampanchayatShow", "false");
		request.setAttribute("villageShow", "true");
		request.setAttribute("villageList", new LocationMasterStandardizationUtil().getVillageList(request.getParameter("grampanchayatCode")));
		return mapping.findForward("showPage");
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String code = request.getParameter("code");
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		String errorMessage = "";
		boolean shgDataExisting = false;
		boolean flag = false;
		if(code.length() == 7) {
			shgDataExisting = new BlockDAO().shgDataExistance(code);
			errorMessage = "Shg Details have already been entered for some of the villages of the selected Block.So the Block couldnot be deleted.";
		}
		if(code.length() == 10) {
			shgDataExisting = new GramPanchayatDAO().shgDataExistance(code);
			errorMessage = "Shg Details have already been entered for some of the villages of the selected Grampanchayat.So the GP couldnot be deleted.";
		}
		if(code.length() == 13) {
			shgDataExisting = new VillageDAO().shgDataExistance(code);
			errorMessage = "Shg Details have already been entered from the selected Village.So the Village couldnot be deleted.";
		}
		try {
			PrintWriter out = response.getWriter();
			if (shgDataExisting == true) {
				out.write(errorMessage);
				return mapping.findForward(null);
			} 
			else {
				if(code.length() == 7) {
					log.info(loginVO.getUserid()+" trying to delete block("+ code + ")");
					
					BlockMasterVO block = new BlockDAO().getBlock(code);
					BlockCorrectionVO blockCorrection = new BlockCorrectionVO();
					Set<GrampanchayatCorrectionVO> grampanchayatCorrectionSet = new HashSet<GrampanchayatCorrectionVO>();
					Set<VillageCorrectionVO> villageCorrectionSet = new HashSet<VillageCorrectionVO>();
					BeanUtils.copyProperties(blockCorrection, block);
					
					boolean existingGrampanchayatDeleted = new GrampanchayatCorrectionDAO().deleteGrampanchayatsUnderBlock(code);
					boolean existingVillagesDeleted = new VillageCorrectionDAO().deleteVillagesUnderBlock(code);
					if(existingGrampanchayatDeleted && existingVillagesDeleted) {
						log.info("Grampanchayats and Villages under block(" + code + ") deleted from modifications table.");
						for(GramPanchayatVO grampanchayatVO : block.getGrampanchayatSet()) {
							GrampanchayatCorrectionVO grampanchayatCorrectionVO = new GrampanchayatCorrectionVO();
							BeanUtils.copyProperties(grampanchayatCorrectionVO, grampanchayatVO);
							grampanchayatCorrectionVO.setFlag("D");
							grampanchayatCorrectionVO.setLastModifedBy(loginVO.getUserid());
							grampanchayatCorrectionVO.setLastModifiedOn(new Date());
							grampanchayatCorrectionSet.add(grampanchayatCorrectionVO);
						}
						for(VillageVO villageVO : block.getVillageSet()) {
							VillageCorrectionVO villageCorrectionVO = new VillageCorrectionVO();
							BeanUtils.copyProperties(villageCorrectionVO, villageVO);
							villageCorrectionVO.setFlag("D");
							villageCorrectionVO.setLastModifedBy(loginVO.getUserid());
							villageCorrectionVO.setLastModifiedOn(new Date());
							villageCorrectionSet.add(villageCorrectionVO);
						}
						blockCorrection.setFlag("D");
						blockCorrection.setStateCode(code.substring(0, 2));
						blockCorrection.setDistrictCode(code.substring(0, 4));
						blockCorrection.setLastModifiedOn(new Date());
						blockCorrection.setLastModifedBy(loginVO.getUserid());
						blockCorrection.setGrampanchayatCorrectionSet(grampanchayatCorrectionSet);
						blockCorrection.setVillageCorrectionSet(villageCorrectionSet);
						flag = new BlockCorrectionDAO().insert(blockCorrection);
					}
				}
				else if(code.length() == 10) {
					log.info(loginVO.getUserid()+" trying to delete grampanchayat("+ code + ")");
					
					GramPanchayatVO grampanchayat = new GramPanchayatDAO().getGrampanchayat(code);
					GrampanchayatCorrectionVO grampanchayatCorrection = new GrampanchayatCorrectionVO();
					Set<VillageCorrectionVO> villageCorrectionSet = new HashSet<VillageCorrectionVO>();
					BeanUtils.copyProperties(grampanchayatCorrection, grampanchayat);
					
					boolean existingVillagesDeleted = new VillageCorrectionDAO().deleteVillagesUnderGrampanchayat(code);
					if(existingVillagesDeleted == true) {
						log.info("Villages under grampanchayat(" + code + ") deleted from modifications table.");
						for(VillageVO villageVO : grampanchayat.getVillageSet()) {
							VillageCorrectionVO villageCorrectionVO = new VillageCorrectionVO();
							BeanUtils.copyProperties(villageCorrectionVO, villageVO);
							villageCorrectionVO.setFlag("D");
							villageCorrectionVO.setLastModifedBy(loginVO.getUserid());
							villageCorrectionVO.setLastModifiedOn(new Date());
							villageCorrectionSet.add(villageCorrectionVO);
						}
						grampanchayatCorrection.setFlag("D");
						grampanchayatCorrection.setLastModifiedOn(new Date());
						grampanchayatCorrection.setLastModifedBy(loginVO.getUserid());
						grampanchayatCorrection.setVillageCorrectionSet(villageCorrectionSet);
						flag = new GrampanchayatCorrectionDAO().insert(grampanchayatCorrection);
					}
				}
				else if(code.length() == 13) {
					log.info(loginVO.getUserid()+" trying to delete village("+ code + ")");
					
					VillageVO village = (VillageVO) new VillageDAO().getVillageName(code).get(0);
					VillageCorrectionVO villageCorrection = new VillageCorrectionVO();
					BeanUtils.copyProperties(villageCorrection, village);
					villageCorrection.setFlag("D");
					villageCorrection.setLastModifedBy(loginVO.getUserid());
					villageCorrection.setLastModifiedOn(new Date());
					flag = new VillageCorrectionDAO().insert(villageCorrection);
				}
				if(flag == true) {
					out.write("true");
					log.info(code + " deleted successfully.");
				}
				else {
					out.write("false");
					log.info("Unable to delete " + code + ".");
				}
			}
		}
		catch (IOException e) {
			log.error("IOException while writing response LocationMasterStandardization.delete(). " + e.getMessage());
		} catch (IllegalAccessException e) {
			log.error("IllegalAccessException while copying properties. " + e.getMessage());
		} catch (InvocationTargetException e) {
			log.error("InvocationTargetException while copying properties. " + e.getMessage());
		}
		
		return mapping.findForward(null);
	}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String code = request.getParameter("code");
		String name = request.getParameter("name").toUpperCase();
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		boolean flag = false;
		try {
			PrintWriter out = response.getWriter();
			if(code.length() == 7) {
				log.info(loginVO.getUserid() + " trying to change block(" +code+ ") name to " + name);
				BlockDAO blockDAO = new BlockDAO();
				BlockMasterVO blockVO = blockDAO.getBlock(code);
				BlockCorrectionVO blockCorrectionVO = new BlockCorrectionVO();
				blockVO.setBlockName(name);
				BeanUtils.copyProperties(blockCorrectionVO, blockVO);
				blockCorrectionVO.setFlag("E");
				blockCorrectionVO.setStateCode(code.substring(0, 2));
				blockCorrectionVO.setDistrictCode(code.substring(0, 4));
				blockCorrectionVO.setLastModifedBy(loginVO.getUserid());
				blockCorrectionVO.setLastModifiedOn(new Date());
				flag = new LocationMasterStandardizationUtil().checkUnique("com.infotech.sgsy.master.block.BlockDAO", "com.infotech.sgsy.master.blockCorrection.BlockCorrectionDAO", code.substring(0, 4), name);
				if(flag == true) {
					flag = new BlockCorrectionDAO().insert(blockCorrectionVO);
				}
				else {
					log.info(loginVO.getUserid() + " unable to change block(" +code+ ") name to " + name +" beacuse block already exists");
					out.write("Block already exists. Please try again with new name.");
					return mapping.findForward(null);
				}
			}
			else if(code.length() == 10) {
				log.info(loginVO.getUserid() +" trying to change grampanchayat(" + code + ") name to " + name);
				GramPanchayatDAO grampanchayatDAO = new GramPanchayatDAO();
				GramPanchayatVO grampanchayatVO = grampanchayatDAO.getGrampanchayat(code);
				GrampanchayatCorrectionVO grampanchayatCorrectionVO = new GrampanchayatCorrectionVO();
				grampanchayatVO.setGramPanchayatName(name);
				BeanUtils.copyProperties(grampanchayatCorrectionVO, grampanchayatVO);
				grampanchayatCorrectionVO.setFlag("E");
				grampanchayatCorrectionVO.setLastModifedBy(loginVO.getUserid());
				grampanchayatCorrectionVO.setLastModifiedOn(new Date());
				flag = new LocationMasterStandardizationUtil().checkUnique("com.infotech.sgsy.master.grampanchayat.GramPanchayatDAO", "com.infotech.sgsy.master.grampanchayatCorrection.GrampanchayatCorrectionDAO", grampanchayatVO.getBlockCode(), name);
				if(flag == true) {
					flag = new GrampanchayatCorrectionDAO().insert(grampanchayatCorrectionVO);
				}
				else {
					log.info(loginVO.getUserid() + " unable to change grampanchayat(" +code+ ") name to " + name +" beacuse grampanchayat already exists");
					out.write("Grampanchayat already exists. Please try again with new name.");
					return mapping.findForward(null);
				}
			}
			else if(code.length() == 13) {
				log.info(loginVO.getUserid() +" trying to change village(" + code + ") name to " + name);
				VillageDAO villageDAO = new VillageDAO();
				VillageVO villageVO = (VillageVO)villageDAO.getVillageName(code).get(0);
				VillageCorrectionVO villageCorrectionVO = new VillageCorrectionVO();
				villageVO.setVillageName(name);
				BeanUtils.copyProperties(villageCorrectionVO, villageVO);
				villageCorrectionVO.setFlag("E");
				villageCorrectionVO.setLastModifedBy(loginVO.getUserid());
				villageCorrectionVO.setLastModifiedOn(new Date());
				flag = new LocationMasterStandardizationUtil().checkUnique("com.infotech.sgsy.master.village.VillageDAO", "com.infotech.sgsy.master.villageCorrection.VillageCorrectionDAO", villageVO.getGramPanchayatCode(), name);
				if(flag == true) {
					flag = new VillageCorrectionDAO().insert(villageCorrectionVO);
				}
				else {
					log.info(loginVO.getUserid() + " unable to change village(" +code+ ") name to " + name +" beacuse village already exists");
					out.write("Village already exists. Please try again with new name.");
					return mapping.findForward(null);
				}
			}
			if(flag == true) {
				out.write("true");
				log.info(name + "(" + code + ") edited successfully.");
			}
			else if(flag == false) {
				out.write("false");
				log.info("Unable to edit  "+name + "(" + code + ").");
			}
		}
		catch(IOException e) {
			log.error("IOException while writing response LocationMasterStandardization.delete(). " + e.getMessage());
		}
		catch (IllegalAccessException e) {
			log.error("IllegalAccessException while copying properties. " + e.getMessage());
		} catch (InvocationTargetException e) {
			log.error("InvocationTargetException while copying properties. " + e.getMessage());
		}
		return mapping.findForward(null);
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		LocationVO locationVO = (LocationVO)request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		LocationMasterStandardizationUtil util = new LocationMasterStandardizationUtil();
		String districtCode = request.getParameter("districtCode");
		String blockCode = request.getParameter("blockCode");
		String grampanchayatCode = request.getParameter("grampanchayatCode");
		String name = request.getParameter("name").toUpperCase();
		boolean flag = false;
		boolean isDeleted = false;
		try {
			PrintWriter out = response.getWriter();
			if(loginVO.getLevelCode().equals("2")) {
				if(districtCode!="" && blockCode.equals("undefined") && grampanchayatCode.equals("undefined")) {
					flag = new LocationMasterStandardizationUtil().checkUnique("com.infotech.sgsy.master.block.BlockDAO", "com.infotech.sgsy.master.blockCorrection.BlockCorrectionDAO", districtCode, name);
					if(flag == true) {
						flag = util.addBlock(locationVO.getStateCode(), districtCode, name, request);
					}
					else {
						out.write("Block already exists. Please try again with new name.");
						return mapping.findForward(null);
					}
				}
				else if(districtCode!="" && districtCode.length() == 4 && blockCode!="" && blockCode.length() == 7 && grampanchayatCode.equals("undefined")) {
					flag = new LocationMasterStandardizationUtil().checkUnique("com.infotech.sgsy.master.grampanchayat.GramPanchayatDAO", "com.infotech.sgsy.master.grampanchayatCorrection.GrampanchayatCorrectionDAO", blockCode, name);
					if(flag == true) {
						isDeleted = new BlockCorrectionDAO().isBlockDeleted(blockCode);
						if(isDeleted == false) {
							flag = util.addGrampanchayat(locationVO.getStateCode(), districtCode, blockCode, name, request);
						}
						else if(isDeleted == true) {
							out.write("Cannot add a Grampanchayat inside a deleted block.");
							return mapping.findForward(null);
						}
					}
					else {
						out.write("Grampanchayat already exists. Please try again with new name.");
						return mapping.findForward(null);
					}
				}
				else if(districtCode != "" && blockCode != "" && !grampanchayatCode.equals("undefined")) {
					flag = new LocationMasterStandardizationUtil().checkUnique("com.infotech.sgsy.master.village.VillageDAO", "com.infotech.sgsy.master.villageCorrection.VillageCorrectionDAO", grampanchayatCode, name);
					if(flag == true) {
						isDeleted = new GrampanchayatCorrectionDAO().isGrampanchayatDeleted(grampanchayatCode);
						if(isDeleted == false) {
							flag = util.addVillage(locationVO.getStateCode(), districtCode, blockCode, grampanchayatCode, name, request);
						}
						else if(isDeleted == true) {
							out.write("Cannot add a village inside a deleted Grampanchayat.");
							return mapping.findForward(null);
						}
						
					}
					else {
						out.write("Village already exists. Please try again with new name.");
						return mapping.findForward(null);
					}
				}
			}
			else if(loginVO.getLevelCode().equals("3")) {
				if(blockCode.equals("undefined")) {
					flag = new LocationMasterStandardizationUtil().checkUnique("com.infotech.sgsy.master.block.BlockDAO", "com.infotech.sgsy.master.blockCorrection.BlockCorrectionDAO", locationVO.getDistrictCode(), name);
					if(flag == true) {
						flag = util.addBlock(locationVO.getStateCode(), locationVO.getDistrictCode(), name, request);
					}
					else {
						out.write("Block already exists. Please try again with new name.");
						return mapping.findForward(null);
					}
				}
				else if(blockCode!="" && grampanchayatCode.equals("undefined")) {
					flag = new LocationMasterStandardizationUtil().checkUnique("com.infotech.sgsy.master.grampanchayat.GramPanchayatDAO", "com.infotech.sgsy.master.grampanchayatCorrection.GrampanchayatCorrectionDAO", blockCode, name);
					if(flag == true) {
						isDeleted = new BlockCorrectionDAO().isBlockDeleted(blockCode);
						if(isDeleted == false) {
							flag = util.addGrampanchayat(locationVO.getStateCode(), locationVO.getDistrictCode(), blockCode, name, request);
						}
						else if(isDeleted == true) {
							out.write("Cannot add a Grampanchayat inside a deleted block.");
							return mapping.findForward(null);
						}
					}
					else {
						out.write("Grampanchayat already exists. Please try again with new name.");
						return mapping.findForward(null);
					}
				}
				else if(blockCode != "" && !grampanchayatCode.equals("undefined")) {
					flag = new LocationMasterStandardizationUtil().checkUnique("com.infotech.sgsy.master.village.VillageDAO", "com.infotech.sgsy.master.villageCorrection.VillageCorrectionDAO", grampanchayatCode, name);
					if(flag == true) {
						isDeleted = new GrampanchayatCorrectionDAO().isGrampanchayatDeleted(grampanchayatCode);
						if(isDeleted == false) {
							flag = util.addVillage(locationVO.getStateCode(), locationVO.getDistrictCode(), blockCode, grampanchayatCode, name, request);
						}
						else if(isDeleted == true) {
							out.write("Cannot add a village inside a deleted Grampanchayat.");
							return mapping.findForward(null);
						}
					}
					else {
						out.write("Village already exists. Please try again with new name.");
						return mapping.findForward(null);
					}
				}
			}
			else if(loginVO.getLevelCode().equals("4")) {
				if(grampanchayatCode.equals("undefined")) {
					flag = new LocationMasterStandardizationUtil().checkUnique("com.infotech.sgsy.master.grampanchayat.GramPanchayatDAO", "com.infotech.sgsy.master.grampanchayatCorrection.GrampanchayatCorrectionDAO", locationVO.getBlockCode(), name);
					if(flag == true) {
						isDeleted = new BlockCorrectionDAO().isBlockDeleted(blockCode);
						if(isDeleted == false) {
							flag = util.addGrampanchayat(locationVO.getStateCode(), locationVO.getDistrictCode(), locationVO.getBlockCode(), name, request);
						}
						else if(isDeleted == true) {
							out.write("Cannot add a Grampanchayat inside a deleted block.");
							return mapping.findForward(null);
						}
					}
				}
				else if(!grampanchayatCode.equals("undefined")) {
					flag = new LocationMasterStandardizationUtil().checkUnique("com.infotech.sgsy.master.village.VillageDAO", "com.infotech.sgsy.master.villageCorrection.VillageCorrectionDAO", grampanchayatCode, name);
					if(flag == true) {
						isDeleted = new GrampanchayatCorrectionDAO().isGrampanchayatDeleted(grampanchayatCode);
						if(isDeleted == false) {
							flag = util.addVillage(locationVO.getStateCode(), locationVO.getDistrictCode(), locationVO.getBlockCode(), grampanchayatCode, name, request);
						}
						else if(isDeleted == true) {
							out.write("Cannot add a village inside a deleted Grampanchayat.");
							return mapping.findForward(null);
						}
					}
					else {
						out.write("Village already exists. Please try again with new name.");
						return mapping.findForward(null);
					}
				}
			}
			if(flag == true) {
				out.write("true");
			}
			else if(flag == false) {
				out.write("false");
			}
		}
		catch(Exception e) {
			log.error("Error saving. " + e.getMessage());
		}
		
		return mapping.findForward(null);
	}
	
//	public ActionForward showAllPrisInDistrict(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
//		LocationVO location = (LocationVO)request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);
//		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
//		//request.setAttribute("blockNames", location.getBlockName());
//		request.setAttribute("allVillageList", new  LocationMasterStandardizationUtil().getAllVillageList(location.getDistrictCode()));
//		return mapping.findForward("showAllVillages");
//	}
	
	public ActionForward createPDF(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LocationVO location = (LocationVO)request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
		//HttpSession session=request.getSession();
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("TRACKERID", request.getParameter("reqtrack"));
		//String TRACKERID=request.getParameter("tokenParameter");
		//request.setAttribute("TRACKERID", TRACKERID);
		//System.out.print(tid);
		//Random generator = new Random();
		//int random = generator.nextInt( 19580427 );
		String  pdf_file_path = "BlockGpVillageDirectory" + location.getDistrictName() + ".pdf";
		Document document=new Document();
		//PdfWriter.getInstance(document,new FileOutputStream(getServlet().getServletContext().getRealPath("")+"/FileFolder/"+pdf_file_path));
		response.addHeader("content-disposition", "attachment; filename=BlockGPVillageDirectoryOf-" + location.getDistrictName() + ".pdf");
		PdfWriter.getInstance(document,response.getOutputStream());
		PdfWriter.getInstance(document,new FileOutputStream("BlockGPVillageDirectoryOf" + location.getDistrictName()));
		document.open();
		List<VillageVO> allPrisList=new VillageDAO().getVillageListForDistrictCode(location.getDistrictCode());
		
		Paragraph Heading = new Paragraph("NRLM LOCATION MASTER STANDARDIZATION", HEADER_FONT_TOP);
		Paragraph SUB_Heading = new Paragraph("BLOCK, GRAM PANCHAYAT AND VILLAGE DIRECTORY OF " + location.getDistrictName(), SUB_HEADER_FONT);
		Heading.setAlignment(Element.ALIGN_CENTER);
		Heading.setSpacingAfter(10f);
		document.add(Heading);
		
		SUB_Heading.setAlignment(Element.ALIGN_CENTER);
		SUB_Heading.setSpacingAfter(10f);
		document.add(SUB_Heading);
		
		PdfPTable table=new PdfPTable(4);
		float[] columnWidths = new float[] {10f, 30f, 30f, 30f};
		table.setWidths(columnWidths);
		PdfPCell cell = new PdfPCell (new Paragraph ("Block, Gram Panchayat and Village Directory of "+ location.getDistrictName()));
		/*cell.setColspan (4);
		cell.setHorizontalAlignment (Element.ALIGN_CENTER);
		cell.setBackgroundColor (HEADER_BACKGROUND);
		table.addCell (cell);*/
		
		cell = new PdfPCell (new Paragraph("S.NO.", TABLE_HEADER_FONT));
		cell.setHorizontalAlignment (Element.ALIGN_LEFT);
		cell.setBackgroundColor (TABLE_HEADER_BACKGROUND);
		table.addCell(cell);
		
		cell = new PdfPCell (new Paragraph ("BLOCK", TABLE_HEADER_FONT));
		cell.setHorizontalAlignment (Element.ALIGN_LEFT);
		cell.setBackgroundColor (TABLE_HEADER_BACKGROUND);
		table.addCell (cell);
		
		cell = new PdfPCell (new Paragraph("GRAM PANCHAYAT", TABLE_HEADER_FONT));
		cell.setHorizontalAlignment (Element.ALIGN_LEFT);
		cell.setBackgroundColor (TABLE_HEADER_BACKGROUND);
		table.addCell (cell);
		
		cell = new PdfPCell (new Paragraph("VILLAGE", TABLE_HEADER_FONT));
		cell.setHorizontalAlignment (Element.ALIGN_LEFT);
		cell.setBackgroundColor (TABLE_HEADER_BACKGROUND);
		table.addCell (cell);
		
		int cntr=1;
		for (int i=0;i<allPrisList.size();i++)
		{
			VillageVO v = allPrisList.get(i);
			
			cell = new PdfPCell (new Paragraph(Integer.toString(cntr++), BODY_FONT));
			cell.setHorizontalAlignment (Element.ALIGN_LEFT);
			cell.setBackgroundColor (BODY_BACKGROUND);
			table.addCell(cell);
			
			cell = new PdfPCell (new Paragraph (v.getBlockName(), BODY_FONT));
			cell.setHorizontalAlignment (Element.ALIGN_LEFT);
			cell.setBackgroundColor (BODY_BACKGROUND);
			table.addCell (cell);
			
			cell = new PdfPCell (new Paragraph(v.getGramPanchayatName(), BODY_FONT));
			cell.setHorizontalAlignment (Element.ALIGN_LEFT);
			cell.setBackgroundColor (BODY_BACKGROUND);
			table.addCell (cell);
			
			cell = new PdfPCell (new Paragraph(v.getVillageName(), BODY_FONT));
			cell.setHorizontalAlignment (Element.ALIGN_LEFT);
			cell.setBackgroundColor (BODY_BACKGROUND);
			table.addCell (cell);
		}
		document.add(table);
		document.close();
		return null;
	}
	
	@SuppressWarnings("deprecation")
	public ActionForward createExcel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
			LocationVO location = (LocationVO) request.getSession().getAttribute(SGSYConstants.SGSY_LOCATIONVO);
			LoginVO loginVO = (LoginVO) request.getSession().getAttribute("loginVO");

			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("TRACKERID", request.getParameter("reqtrack"));

			String[] titles = { "S.No", "BLOCK NAME", "GRAM PANCHAYAT NAME", "VILLAGE NAME" };
			String code = null;
			boolean xlsx = false;
			Workbook hwb = xlsx ? new XSSFWorkbook() : new HSSFWorkbook();
			Sheet sheet = hwb.createSheet("locationMasterStandardization");
			sheet.setFitToPage(true);
			sheet.setHorizontallyCenter(true);
			sheet.setColumnWidth(1,(35*256));
			sheet.setColumnWidth(2,(35*256));
			sheet.setColumnWidth(3,(35*256));
			int cellnum = 0;
			int rownum = 0;
			try {
					CellStyle style;
					style = hwb.createCellStyle();			
					style.setAlignment(CellStyle.ALIGN_CENTER);			
					style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);										
					style.setWrapText(true);
					Map<String, CellStyle> styles = new HashMap<String, CellStyle>();	
					styles.put("header", style);
					Row row = sheet.createRow(rownum++);
					row.setHeightInPoints(40);
					Cell cell = row.createCell(0);					
					cell.setCellValue("LOCATION MASTER STANDARDIZATION OF DISTRICT "+location.getDistrictName());
					cell.setCellStyle(styles.get("header"));
					sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$D$1"));
							
					
					row = sheet.createRow(rownum++);
					row.setHeightInPoints(20);
					for (int i = 0; i < titles.length; i++) {
						cell = row.createCell(i);
						cell.setCellValue(titles[i]);
						cell.setCellStyle(styles.get("header"));
					}
					List<VillageVO> allPrisList=new VillageDAO().getVillageListForDistrictCode(location.getDistrictCode());
					int index=1;
					for (VillageVO locationMaster: allPrisList) {
						row = sheet.createRow(rownum++);
						cell = row.createCell(0);
						cell.setCellValue(index++);
						cell = row.createCell(1);
						cell.setCellValue(locationMaster.getBlockName());
						cell = row.createCell(2);
						cell.setCellValue(locationMaster.getGramPanchayatName());
						cell = row.createCell(3);
						cell.setCellValue(locationMaster.getVillageName());		
					}
			response.setHeader("Content-Disposition", "attachment; filename=locationMasterStandardization.xls");
			ServletOutputStream out = response.getOutputStream();
			hwb.write(out);
			out.flush();
			out.close();
			} catch (Exception e) {
				log.error("Exception while bank linkage list Page. "+ e.getMessage());
			}
			return null;
			}

}

