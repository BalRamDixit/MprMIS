package com.infotech.sgsy.util;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import org.apache.struts.upload.FormFile;
import org.hibernate.Transaction;
import com.infotech.sgsy.common.LocationVO;
import com.infotech.sgsy.configuration.Persister;
import com.infotech.sgsy.configuration.PersisterImpl;

public class CommonUtils {
	public static String CENSUS_POPULATION;

	public String populateValueFor(String controlName, Collection objectValues,
			String requestedObjectName, boolean isIE) throws Exception {
		String outputText = "", InterneExplorerSource = "", OtherBrowserSource = "<option value=0>-Select-</option>"; // OtherBrowserSource
		// =
		// "document.forms[0]."+controlName+".innerHTML
		// =
		// \"";
		try {
			if (objectValues != null) {
				Iterator itr = objectValues.iterator();
				while (itr.hasNext()) {
					// StringTokenizer detailsToken = new
					// StringTokenizer(detailMapArray[i].toString(),"$");
					// detailsToken.nextToken();
					// String objValue=detailsToken.nextToken();
					// String objText =
					// (String)objectValues.get(detailMapArray[i].toString());
					PropertyModel model = (PropertyModel) itr.next();
					// if(isIE) {
					// String value =
					// model.getPropertyValue().replaceAll("'","\'");

					if (!model.getPropertyCode().equals("")) {
						String value = model.getPropertyValue().replaceAll(
								"\"", "\\\\\"");
						InterneExplorerSource += "O= document.createElement('option');";
						InterneExplorerSource += "O.text  = replaceSingleQuote(\""
								+ value + "\");";
						InterneExplorerSource += "O.value = '"
								+ model.getPropertyCode() + "';";
						InterneExplorerSource += "document.getElementsByName('"
								+ controlName + "')[0].options.add(O); ";

					}
				}
				// OtherBrowserSource += "\"; ";
				// outputText = isIE? InterneExplorerSource : OtherBrowserSource
				// ;
				outputText = /*
							 * "O= document.createElement('option'); O.text ='Select';O.value=''; "
							 * + "document.getElementsByName('"+ controlName +
							 * "')[0].options.add(O); " +
							 */InterneExplorerSource;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outputText;
	}

	public static void main(String ss[]) throws Exception {
		new CommonUtils().populateValueFor("", new ArrayList(), "", false);
	}

	public static void closeDatabaseUtil(Statement stmt, Connection con,
			ResultSet rs) throws SQLException {

		try {
			if (rs != null) {
				rs.close();
				rs = null;

			}
			if (stmt != null) {

				stmt.close();
				stmt = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}

		} catch (SQLException e) {
			throw e;
		}
	}

	public static int checkUnique(String tableName, String columnName,
			String checkValue, LocationVO vo) throws SQLException {

		List results = null;

		try {
			Persister persister = PersisterImpl.getPersister();
			Transaction transaction = persister.getTransaction();
			transaction.begin();
			String query = "select * from " + tableName + " where "
					+ columnName + " = '" + checkValue.trim() + "' "
					+ " and block_code  = '" + vo.getBlockCode().trim() + "'";
			results = persister.executeNativeQuery(query);
			transaction.commit();

		} catch (Exception e) {
			System.out.println("error" + e);
		}

		if (results == null || results.size() == 0) {
			return SGSYConstants.VALUE_ABSENT;
		} else {
			return SGSYConstants.VALUE_PRESENT;
		}

	}

	public static int checkUnique(String tableName, String columnName,
			String checkValue) throws SQLException {

		List results = null;

		try {
			Persister persister = PersisterImpl.getPersister();
			Transaction transaction = persister.getTransaction();
			transaction.begin();
			String query = "select * from " + tableName + " where "
					+ columnName + " = '" + checkValue.trim().toUpperCase()
					+ "'";
			results = persister.executeNativeQuery(query);
			transaction.commit();

		} catch (Exception e) {
			System.out.println("error" + e);
		}

		if (results == null || results.size() == 0) {
			return SGSYConstants.VALUE_ABSENT;
		} else {
			return SGSYConstants.VALUE_PRESENT;
		}

	}

	/**
	 * @author 53414
	 * @param tableName
	 * @param columnName
	 * @param checkValue
	 * @return
	 * @throws SQLException
	 */
	public static int checkUnique(String tableName, String columnName1,
			String checkValue1, String columnName2, String checkValue2)
			throws SQLException {

		List results = null;

		try {
			Persister persister = PersisterImpl.getPersister();
			Transaction transaction = persister.getTransaction();
			transaction.begin();
			String query = "select * from " + tableName + " where "
					+ columnName1 + " = '" + checkValue1.trim() + "' and "
					+ columnName2 + " = '" + checkValue2.trim() + "' ";
			results = persister.executeNativeQuery(query);
			transaction.commit();

		} catch (Exception e) {
			System.out.println("error" + e);
		}finally{
			
		}

		if (results == null || results.size() == 0) {
			return SGSYConstants.VALUE_ABSENT;
		} else {
			return SGSYConstants.VALUE_PRESENT;
		}

	}

	public static synchronized String getCurrentDate(String dateFormat) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(cal.getTime());
	}

	/**
	 * @author 53414
	 * @param controlName
	 * @param objectValues
	 * @param requestedObjectName
	 * @param isIE
	 * @return
	 * @throws Exception
	 */
	public String populateValueForTextBox(String controlName,
			Collection objectValues, String requestedObjectName, boolean isIE)
			throws Exception {

		String outputText = "", InterneExplorerSource = "", OtherBrowserSource = "<option value=0>-Select-</option>"; // OtherBrowserSource
		// =
		// "document.forms[0]."+controlName+".innerHTML
		// =
		// \"";
		try {
			if (objectValues != null) {
				Iterator itr = objectValues.iterator();
				while (itr.hasNext()) {

					PropertyModel model = (PropertyModel) itr.next();
					String code = model.getPropertyCode();
					String value = model.getPropertyValue().replaceAll("\"",
							"\\\\\"");
					InterneExplorerSource += "O= document.getElementsByName('"
							+ controlName + "')[0];";
					InterneExplorerSource += "O.value=replaceSingleQuote(\""
							+ value + "\");";

				}
				// OtherBrowserSource += "\"; ";
				// outputText = isIE? InterneExplorerSource : OtherBrowserSource
				// ;
				outputText = /*
							 * "O= document.createElement('option'); O.text ='Select';O.value=''; "
							 * + "document.getElementsByName('"+ controlName +
							 * "')[0].options.add(O); " +
							 */InterneExplorerSource;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outputText;
	}

	public static boolean checkFileType(FormFile file) throws Exception {
		boolean result = false;
		String contenttype = file.getContentType();
		String fileName = file.getFileName();
		// Getting the file extension
		String extension;
		// Applying check when file name is not present . This means no file is
		// uploaded.
		if (!fileName.equals("")) {
			int dotPos = fileName.lastIndexOf(".");
			extension = fileName.substring(dotPos);

			if ((contenttype.equals(".pdf") && extension
					.equalsIgnoreCase(".pdfE"))
					|| (contenttype.equals(".doc") && extension
							.equalsIgnoreCase(".docE"))
					|| (contenttype.equals(".jpg") && extension
							.equalsIgnoreCase(".jpgE"))
					|| (contenttype.equals(".xls") && extension
							.equalsIgnoreCase(".xlsE")))
				result = true;
		} else
			result = true;
		return result;
	}

	

	
	public static String getDistrictShortName(String districtCode)
			throws Exception {
		String districtShortName = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT district_short_name FROM mst_district WHERE district_code = ? ";
		
		try {
			con = PersisterImpl.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, districtCode);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				districtShortName = rs.getString("district_short_name");
			}
			con.commit();
			con.close();
			return districtShortName;
		} catch (Exception e) {
			// log.error("error in getInstitue "+e);
			throw e;
		}finally{
			
		}
	}

	
	public static String getStateShortName(String stateId) throws Exception {
		String stateShortName = "";
		Connection con=null;
 	    PreparedStatement pstmt=null;
	 	ResultSet rs=null;
		List finalList = new ArrayList();
		String query="SELECT state_short_name FROM mst_state WHERE state_code =?";			
		try
		{
			con=PersisterImpl.getConnection();						
			pstmt=con.prepareStatement(query);		
			pstmt.setString(1, stateId);
			rs=pstmt.executeQuery();		
			while(rs.next()){
				stateShortName = rs.getString("state_short_name");				
			}
			con.commit();
			con.close();
			return 	stateShortName;
		}catch (Exception e) {
			//log.error("error in getInstitue "+e);
			throw e;
		}finally{
			
		}		
	}

	public static String getQuarterwiseDate(String quarterNo, String finYear)
			throws Exception {
		String year = getFinYear(finYear);
		String date1 = "";

		try {

			if (quarterNo.equals("1")) {
				date1 = "01-04-" + year.split("-")[0] + "#" + "30-06-"
						+ year.split("-")[0];
			}
			if (quarterNo.equals("2")) {
				date1 = "01-07-" + year.split("-")[0] + "#" + "30-09-"
						+ year.split("-")[0];
			}
			if (quarterNo.equals("3")) {
				date1 = "01-10-" + year.split("-")[0] + "#" + "31-12-"
						+ year.split("-")[0];
			}
			if (quarterNo.equals("4")) {
				date1 = "01-01-" + year.split("-")[1] + "#" + "31-03-"
						+ year.split("-")[1];
			}

			return date1;
		} catch (Exception e) {
			// log.error("error in getInstitue "+e);
			throw e;
		}

	}

	public static String getFinYear(String finYear) throws Exception {
		String finYearName = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT finyeartodisplay FROM mst_fin_year WHERE finyear_code = ?";

		try {
			con = PersisterImpl.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, finYear);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				finYearName = rs.getString("finyeartodisplay");
			}
			con.commit();
			con.close();

			return finYearName;
		} catch (Exception e) {
			// log.error("error in getInstitue "+e);
			throw e;
		}
	}

	public static List getQuarter() throws Exception {
		List quarterList = new ArrayList();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PropertyModel pm = new PropertyModel();
		String query = "SELECT quarter_no,quarter_name FROM mst_quarter ";
		try {
			con = PersisterImpl.getConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pm = new PropertyModel();
				pm.setPropertyCode(rs.getString("quarter_no"));
				pm.setPropertyValue(rs.getString("quarter_name"));
				quarterList.add(pm);
			}
			con.commit();
			con.close();
			return quarterList;
		} catch (Exception e) {
			throw e;
		}
	}
}
