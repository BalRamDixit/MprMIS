package com.infotech.sgsy.master.bank;

import java.util.List;

import com.infotech.sgsy.common.BusinessMaster;
import com.infotech.sgsy.common.MasterDAOFactory;
import com.infotech.sgsy.util.CommonUtils;
import com.infotech.sgsy.util.Constants;
import com.infotech.sgsy.util.SGSYConstants;

public class BankBusiness extends BusinessMaster {
	BankDAO bankDAO = null;

	public int save(BankVO bankVO) throws Exception {
		BankDAO bankDAO = (BankDAO) MasterDAOFactory.getInstance(Constants.GET_BANK_DAO);
		int i = CommonUtils.checkUnique("mst_bank", "bank_name", bankVO.getBankName());

		if (i == SGSYConstants.VALUE_ABSENT) {
			i = CommonUtils.checkUnique("mst_bank", "bank_shortname", bankVO.getBankShortName());
			if (i == SGSYConstants.VALUE_ABSENT) {
				return bankDAO.save(bankVO);
			} else {
				return Constants.SHORTNAME_FOUND;
			}
		} else {
			return Constants.NAME_FOUND;
		}
	}

	public List getBankformord() throws Exception {
		bankDAO = (BankDAO) MasterDAOFactory.getInstance(Constants.GET_BANK_DAO);
		return bankDAO.getBankListForMord();
	}

	public List getBankBranchListForMord(String bankCode) throws Exception {
		bankDAO = (BankDAO) MasterDAOFactory.getInstance(Constants.GET_BANK_DAO);
		return bankDAO.getBankBranchListForMord(bankCode);
	}

}
