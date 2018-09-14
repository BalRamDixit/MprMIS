package com.infotech.sgsy.common;

import org.apache.struts.upload.FormFile;
import java.io.Serializable;

public class uploadCircularVO implements Serializable {

	private Integer id;
	private String uploadCircularCode;
	private String uploadCircularCodeHindi;
	private String circularScheme;
	private String circularScheme_code;

	public String getCircularScheme_code() {
		return circularScheme_code;
	}

	public void setCircularScheme_code(String circularSchemeCode) {
		circularScheme_code = circularSchemeCode;
	}

	private String uploadCircularName;
	private String uploadCircularDate;
	private String serverDate;
	private FormFile UploadCircularFile;
	private String UploadCircularFileName;
	private String UploadCircularFileNameHindi;
	private String show;

	public String getUploadCircularFileName() {
		return UploadCircularFileName;
	}

	public void setUploadCircularFileName(String uploadCircularFileName) {
		UploadCircularFileName = uploadCircularFileName;
	}

	private FormFile meetingAgendaFile;

	public String getUploadCircularCode() {
		return uploadCircularCode;
	}

	public void setUploadCircularCode(String uploadCircularCode) {
		this.uploadCircularCode = uploadCircularCode;
	}

	public String getCircularScheme() {
		return circularScheme;
	}

	public void setCircularScheme(String circularScheme) {
		this.circularScheme = circularScheme;
	}

	public String getUploadCircularName() {
		return uploadCircularName;
	}

	public void setUploadCircularName(String uploadCircularName) {
		this.uploadCircularName = uploadCircularName;
	}

	public String getUploadCircularDate() {
		return uploadCircularDate;
	}

	public void setUploadCircularDate(String uploadCircularDate) {
		this.uploadCircularDate = uploadCircularDate;
	}

	public String getServerDate() {
		return serverDate;
	}

	public void setServerDate(String serverDate) {
		this.serverDate = serverDate;
	}

	public FormFile getUploadCircularFile() {
		return UploadCircularFile;
	}

	public void setUploadCircularFile(FormFile uploadCircularFile) {
		UploadCircularFile = uploadCircularFile;
	}

	public FormFile getMeetingAgendaFile() {
		return meetingAgendaFile;
	}

	public void setMeetingAgendaFile(FormFile meetingAgendaFile) {
		this.meetingAgendaFile = meetingAgendaFile;
	}

	public String getUploadCircularFileNameHindi() {
		return UploadCircularFileNameHindi;
	}

	public void setUploadCircularFileNameHindi(
			String uploadCircularFileNameHindi) {
		UploadCircularFileNameHindi = uploadCircularFileNameHindi;
	}

	public String getUploadCircularCodeHindi() {
		return uploadCircularCodeHindi;
	}

	public void setUploadCircularCodeHindi(String uploadCircularCodeHindi) {
		this.uploadCircularCodeHindi = uploadCircularCodeHindi;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

}
