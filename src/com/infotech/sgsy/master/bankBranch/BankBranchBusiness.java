package com.infotech.sgsy.master.bankBranch;

import com.infotech.sgsy.common.BusinessMaster;
import com.infotech.sgsy.common.MasterDAOFactory;

import com.infotech.sgsy.util.CommonUtils;
import com.infotech.sgsy.util.Constants;
import com.infotech.sgsy.util.SGSYConstants;

public class BankBranchBusiness extends BusinessMaster {

	// USED TO SAVE THE BRANCH RECORD
	public int save(BankBranchVO bankBranchVO) throws Exception {
		BankBranchDAO bankBranchDAO = (BankBranchDAO) MasterDAOFactory
				.getInstance(Constants.GET_BANK_BRANCH_DAO);
		int i = 1;
		if (bankBranchVO.getEntityCode().length() == 1) {
			i = CommonUtils.checkUnique("mst_bank_branch", "ifsc_code",
					bankBranchVO.getIfscCode());
		}
		if (i == SGSYConstants.VALUE_ABSENT) {
			return bankBranchDAO.save(bankBranchVO);
		} else {
			return Constants.SHORTNAME_FOUND;
		}
	}

	public static int update(BankBranchVO bankBranchVO) throws Exception {
		BankBranchDAO bankBranchDAO = (BankBranchDAO) MasterDAOFactory.getInstance(Constants.GET_BANK_BRANCH_DAO);
		int i = 1;
		if (bankBranchVO.getEntityCode().length() == 1){
			i = CommonUtils.checkUnique("mst_bank_branch", "ifsc_code",
					bankBranchVO.getIfscCode());
		}
		if (i == SGSYConstants.VALUE_ABSENT) {
			return bankBranchDAO.update(bankBranchVO);
		} else {
			return Constants.SHORTNAME_FOUND;
		}
	}

}