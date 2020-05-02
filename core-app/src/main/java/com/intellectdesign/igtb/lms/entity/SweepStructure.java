package com.intellectdesign.igtb.lms.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class SweepStructure implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long structureId;
	private String subProductCode;
	private String productCode;
	private LocalDate effDate;
	private Long nbrPriority;
	private Long noOfInstructions;
	private Long nbrGroupId;
	private String structureType;
	private LocalDate setupDate;
	private String lockFlag;
	private Object extObject;

	public SweepStructure() {
		super();

	}

	public Long getStructureId() {
		return structureId;
	}

	public void setStructureId(Long structureId) {
		this.structureId = structureId;
	}

	public String getSubProductCode() {
		return subProductCode;
	}

	public void setSubProductCode(String subProductCode) {
		this.subProductCode = subProductCode;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public LocalDate getEffDate() {
		return effDate;
	}

	public void setEffDate(LocalDate effDate) {
		this.effDate = effDate;
	}

	public Long getNbrPriority() {
		return nbrPriority;
	}

	public void setNbrPriority(Long nbrPriority) {
		this.nbrPriority = nbrPriority;
	}

	public Long getNoOfInstructions() {
		return noOfInstructions;
	}

	public void setNoOfInstructions(Long noOfInstructions) {
		this.noOfInstructions = noOfInstructions;
	}

	public Long getNbrGroupId() {
		return nbrGroupId;
	}

	public void setNbrGroupId(Long nbrGroupId) {
		this.nbrGroupId = nbrGroupId;
	}

	public String getStructureType() {
		return structureType;
	}

	public void setStructureType(String structureType) {
		this.structureType = structureType;
	}

	public LocalDate getSetupDate() {
		return setupDate;
	}

	public void setSetupDate(LocalDate setupDate) {
		this.setupDate = setupDate;
	}

	public String getLockFlag() {
		return lockFlag;
	}

	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}

	public Object getExtObject() {
		return extObject;
	}

	public void setExtObject(Object extObject) {
		this.extObject = extObject;
	}

	@Override
	public String toString() {
		return "SweepStructure [structureId=" + structureId + ", subProductCode=" + subProductCode + ", productCode="
				+ productCode + ", effDate=" + effDate + ", nbrPriority=" + nbrPriority + ", noOfInstructions="
				+ noOfInstructions + ", nbrGroupId=" + nbrGroupId + ", structureType=" + structureType + ", setupDate="
				+ setupDate + ", lockFlag=" + lockFlag + ", extObject=" + extObject + "]";
	}

}
