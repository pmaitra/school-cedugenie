package com.qts.icam.model.common;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

@Entry(objectClasses = {"top","person","organizationalPerson","inetOrgPerson"})
public class LdapInfo {
	@Id
    private Name dn;
	
	@Attribute(name="cn")
	@DnAttribute(value="cn", index=0)
	private String commonName;
	
	@Attribute(name="uid")
	private String userId;
	
	@Attribute(name="userPassword")
	private String password;
	
	@Attribute(name="ou")
	@DnAttribute(value="ou", index=1)
	private String organizationalUnit;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Name getDn() {
		return dn;
	}
	public void setDn(Name dn) {
		this.dn = dn;
	}
	public String getCommonName() {
		return commonName;
	}
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	public String getOrganizationalUnit() {
		return organizationalUnit;
	}
	public void setOrganizationalUnit(String organizationalUnit) {
		this.organizationalUnit = organizationalUnit;
	}
	
}
