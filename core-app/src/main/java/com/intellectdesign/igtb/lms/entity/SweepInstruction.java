package com.intellectdesign.igtb.lms.entity;

import java.io.Serializable;

public class SweepInstruction extends ExtendedModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long structureId;
	private Long instructionId;
	private String suspendFlag;
	private String endFlag;
	private String crossCcyFlag;
	private String frequencyCode;
	private String sweepTypeFlag;
	private String cifSrcAcctCode;
	private String cifTgtAcctCode;
	private String nbrSrcAcct;
	private String reverseFlag;
	private Long nbrPriority;
	private String forceDrFlag;
	private String controlODLimitFag;
	private String contraODLimitFag;

	public SweepInstruction() {
		super();

	}

	public Long getStructureId() {
		return structureId;
	}

	public void setStructureId(Long structureId) {
		this.structureId = structureId;
	}

	public Long getInstructionId() {
		return instructionId;
	}

	public void setInstructionId(Long instructionId) {
		this.instructionId = instructionId;
	}

	public String getSuspendFlag() {
		return suspendFlag;
	}

	public void setSuspendFlag(String suspendFlag) {
		this.suspendFlag = suspendFlag;
	}

	public String getEndFlag() {
		return endFlag;
	}

	public void setEndFlag(String endFlag) {
		this.endFlag = endFlag;
	}

	public String getCrossCcyFlag() {
		return crossCcyFlag;
	}

	public void setCrossCcyFlag(String crossCcyFlag) {
		this.crossCcyFlag = crossCcyFlag;
	}

	public String getFrequencyCode() {
		return frequencyCode;
	}

	public void setFrequencyCode(String frequencyCode) {
		this.frequencyCode = frequencyCode;
	}

	public String getSweepTypeFlag() {
		return sweepTypeFlag;
	}

	public void setSweepTypeFlag(String sweepTypeFlag) {
		this.sweepTypeFlag = sweepTypeFlag;
	}

	public String getCifSrcAcctCode() {
		return cifSrcAcctCode;
	}

	public void setCifSrcAcctCode(String cifSrcAcctCode) {
		this.cifSrcAcctCode = cifSrcAcctCode;
	}

	public String getCifTgtAcctCode() {
		return cifTgtAcctCode;
	}

	public void setCifTgtAcctCode(String cifTgtAcctCode) {
		this.cifTgtAcctCode = cifTgtAcctCode;
	}

	public String getNbrSrcAcct() {
		return nbrSrcAcct;
	}

	public void setNbrSrcAcct(String nbrSrcAcct) {
		this.nbrSrcAcct = nbrSrcAcct;
	}

	public String getReverseFlag() {
		return reverseFlag;
	}

	public void setReverseFlag(String reverseFlag) {
		this.reverseFlag = reverseFlag;
	}

	public Long getNbrPriority() {
		return nbrPriority;
	}

	public void setNbrPriority(Long nbrPriority) {
		this.nbrPriority = nbrPriority;
	}

	public String getForceDrFlag() {
		return forceDrFlag;
	}

	public void setForceDrFlag(String forceDrFlag) {
		this.forceDrFlag = forceDrFlag;
	}

	public String getControlODLimitFag() {
		return controlODLimitFag;
	}

	public void setControlODLimitFag(String controlODLimitFag) {
		this.controlODLimitFag = controlODLimitFag;
	}

	public String getContraODLimitFag() {
		return contraODLimitFag;
	}

	public void setContraODLimitFag(String contraODLimitFag) {
		this.contraODLimitFag = contraODLimitFag;
	}

}
