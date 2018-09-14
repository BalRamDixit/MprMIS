package com.infotech.sgsy.uploadcircular;


import java.util.Date;

import org.apache.struts.upload.FormFile;

import com.infotech.sgsy.common.MasterForm;

public class UploadCircularForm extends MasterForm{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	String uploadCircularCode;
	String circularScheme;
	String otherCircular;
	public String getOtherCircular() {
		return otherCircular;
	}

	public void setOtherCircular(String otherCircular) {
		this.otherCircular = otherCircular;
	}

	public String getCircularScheme() {
		return circularScheme;
	}

	public void setCircularScheme(String circularScheme) {
		this.circularScheme = circularScheme;
	}

	String uploadCircularName;
	String uploadCircularDate;
	String serverDate;
	FormFile UploadCircularFile=null;
	FormFile meetingAgendaFileEnglish=null;
	FormFile meetingAgendaFileHindi=null;
	String next;
	String UploadCircularFileName;
	String UploadCircularFileNameHindi;
	String show;
	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public FormFile getMeetingAgendaFileEnglish() {
		return meetingAgendaFileEnglish;
	}

	public void setMeetingAgendaFileEnglish(FormFile meetingAgendaFileEnglish) {
		this.meetingAgendaFileEnglish = meetingAgendaFileEnglish;
	}

	public void reset() {	
		super.reset();		
		uploadCircularCode="";
		uploadCircularName="";
		uploadCircularDate="";
		serverDate="";
		UploadCircularFile=null;
		UploadCircularFileName = null;
		UploadCircularFileNameHindi = null;
		show = null;
		meetingAgendaFileEnglish=null;
		meetingAgendaFileHindi=null;
		circularScheme = null;
	}

	public String getUploadCircularCode() {
		return uploadCircularCode;
	}

	public void setUploadCircularCode(String uploadCircularCode) {
		this.uploadCircularCode = uploadCircularCode;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public FormFile getMeetingAgendaFileHindi() {
		return meetingAgendaFileHindi;
	}

	public void setMeetingAgendaFileHindi(FormFile meetingAgendaFileHindi) {
		this.meetingAgendaFileHindi = meetingAgendaFileHindi;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUploadCircularFileName() {
		return UploadCircularFileName;
	}

	public void setUploadCircularFileName(String uploadCircularFileName) {
		UploadCircularFileName = uploadCircularFileName;
	}

	public String getUploadCircularFileNameHindi() {
		return UploadCircularFileNameHindi;
	}

	public void setUploadCircularFileNameHindi(String uploadCircularFileNameHindi) {
		UploadCircularFileNameHindi = uploadCircularFileNameHindi;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}
	
}