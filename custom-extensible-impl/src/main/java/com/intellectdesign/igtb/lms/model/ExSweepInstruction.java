package com.intellectdesign.igtb.lms.model;

import java.io.Serializable;

public class ExSweepInstruction implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long structureId;
	private Long instructionId;
	private String extField1;
	private String extField2;
	private String extField3;

	public ExSweepInstruction() {
	}

	public Long getStructureId() {
		return structureId;
	}

	public Long getInstructionId() {
		return instructionId;
	}

	public void setInstructionId(Long instructionId) {
		this.instructionId = instructionId;
	}

	public void setStructureId(Long structureId) {
		this.structureId = structureId;
	}

	public String getExtField1() {
		return extField1;
	}

	public void setExtField1(String extField1) {
		this.extField1 = extField1;
	}

	public String getExtField2() {
		return extField2;
	}

	public void setExtField2(String extField2) {
		this.extField2 = extField2;
	}

	public String getExtField3() {
		return extField3;
	}

	public void setExtField3(String extField3) {
		this.extField3 = extField3;
	}

}
