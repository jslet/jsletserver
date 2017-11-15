package com.jslet.sample.user;

import com.jslet.dataset.record.AbstractStaticRecord;

public class User extends AbstractStaticRecord {
	private static final long serialVersionUID = -4144168507536812078L;

	private Integer userId;

	private Integer tenantId;

	private Integer userType;

	private String userCode;

	private String userName;

	private String password;

	private String branchCode;

	private String tel;

	private String remark;

	private Integer isEnabled;

	/**
	 * 获取用户自增长ID
	 *
	 * @return userId 用户自增长ID
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 设置用户自增长ID
	 *
	 * @param userId 用户自增长ID
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 获取公司ID
	 *
	 * @return tenantId 公司ID
	 */
	public Integer getTenantId() {
		return tenantId;
	}

	/**
	 * 设置公司ID
	 *
	 * @param tenantId 公司ID
	 */
	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * 获取用户类别
	 *
	 * @return userType [userType]
	 */
	public Integer getUserType() {
		return userType;
	}

	/**
	 * 设置用户类别
	 *
	 * @param userType [userType]
	 */
	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	/**
	 * 获取用户编码
	 *
	 * @return userCode 用户编码
	 */
	public String getUserCode() {
		return userCode;
	}

	/**
	 * 设置用户编码
	 *
	 * @param userCode 用户编码
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	/**
	 * 获取用户名
	 *
	 * @return userName 用户名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置用户名
	 *
	 * @param userName 用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 获取密码
	 *
	 * @return password 密码
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置密码
	 *
	 * @param password 密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取所属网点
	 *
	 * @return branchCode 所属网点
	 */
	public String getBranchCode() {
		return branchCode;
	}

	/**
	 * 设置所属网点
	 *
	 * @param branchCode 所属网点
	 */
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	/**
	 * 获取电话号码
	 *
	 * @return tel 电话号码
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * 设置电话号码
	 *
	 * @param tel 电话号码
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * 获取备注
	 *
	 * @return remark 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置备注
	 *
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取是否启用
	 *
	 * @return isEnabled 是否启用
	 */
	public Integer getIsEnabled() {
		return isEnabled;
	}

	/**
	 * 设置是否启用
	 *
	 * @param isEnabled 是否启用
	 */
	public void setIsEnabled(Integer isEnabled) {
		this.isEnabled = isEnabled;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", tenantId=" + tenantId + ", userCode=" + userCode + ", userName=" + userName + ", password=" + password
				+ ", branchCode=" + branchCode + ", tel=" + tel + ", remark=" + remark + ", isEnabled=" + isEnabled + "]";
	}
}
