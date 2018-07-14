package com.qts.icam.utility.ldap;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import javax.naming.Name;
import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.support.LdapNameBuilder;

import com.qts.icam.model.common.LdapInfo;
import com.qts.icam.model.login.LoginForm;


public class Ldap {
	public static Logger logger = Logger.getLogger(Ldap.class);
	
	@Autowired
	private LdapTemplate ldapTemplate;
	/**
	 * this method is used to authenticate userId and password in LDAP for a user
	 */
	
	public boolean authenticate(LoginForm login) {
		logger.info("authenticate(LoginForm login) method in Utility");
		boolean authenticateStatus=false;
		try{
			AndFilter filter = new AndFilter();
			filter.and(new EqualsFilter("objectClass", "person")).and(new EqualsFilter("uid", login.getUserId().toLowerCase()));
			authenticateStatus = ldapTemplate.authenticate("",filter.toString(), login.getPassword());
		}catch(Exception e){
			logger.error("Exception in authenticate(LoginForm login) method in Utility EXCEPTION: ",e);
			authenticateStatus=false;
		}
	return authenticateStatus;
	}
	
	
	/**
	 * this method is used to check existing user in LDAP.
	 */
	public String getUserLdapStatus(String resourceId) {
		String userLdapStatus="notExist";
		try{
			logger.info("getUserLdapStatus(String resourceId) method in Utility");
			LdapInfo ldapInfo = ldapTemplate.findOne(query().where("uid").is(resourceId.toLowerCase()), LdapInfo.class);
			if(ldapInfo!=null){
				userLdapStatus="exist";
			}
		}catch(EmptyResultDataAccessException e){
			userLdapStatus="notExist";
		}catch(Exception e){
			userLdapStatus="notExist";
			logger.error("Exception in getUserLdapStatus(String resourceId) method in Utility for LDAP operation EXCEPTION: ",e);
		}
		return userLdapStatus;
	}

	/**
	 * this method is used to create or configure new user and password into LDAP
	 */
	public String createPassword(LoginForm login) {
		String status="fail";
		try{
		  logger.info("createPassword(LoginForm login) method in LoginDaoImpl");
		 String surname ="";
		 if(login.getName()!=null){
			  surname = login.getName().substring(login.getName().lastIndexOf(' ')+1);
			  if(surname!=null)surname=surname.trim();
			  Name dn = buildDn(login);
		      DirContextAdapter context = new DirContextAdapter(dn);
		      context.setAttributeValues("objectclass", new String[] {"organizationalPerson","person","inetOrgPerson","top"});
		      context.setAttributeValue("cn", login.getName());
		      context.setAttributeValue("sn", surname);
		      context.setAttributeValue("uid", login.getUserId().toLowerCase());
		      context.setAttributeValue("userPassword", login.getNewPassword().trim());
		      ldapTemplate.bind(context);
		      status="success";
		 }
		}catch(Exception e){
			logger.error("Exception in createPassword(LoginForm login) method in Utility for LDAP operation EXCEPTION: ",e);
			 status="fail";
		}
		return status;
	}

	/**
	 * this method is used to build dn for an entry in LDAP
	 * @param login
	 * @return Name
	 */
	 protected Name buildDn(LoginForm login) {
			return LdapNameBuilder.newInstance("")		    
			   .add("ou", "users")
			   .add("uid", login.getUserId().toLowerCase())
			   .build();
	}

		/**
		 * this method is used to update password into LDAP for a user.
		 */
		public String updatePassword(LoginForm login) {
			String passwordStatus="fail";
			try{
				logger.info("updatePassword(LoginForm login) method in Utility");
				LdapInfo ldapInfo = ldapTemplate.findOne(query().where("uid").is(login.getUserId().toLowerCase()), LdapInfo.class);
				if(ldapInfo!=null && ldapInfo.getDn()!=null){
					Attribute newattr = new BasicAttribute("userPassword", login.getNewPassword());
					ModificationItem modificationItem = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, newattr);
					ldapTemplate.modifyAttributes(ldapInfo.getDn().toString(), new ModificationItem[] { modificationItem });
					passwordStatus="success";
				}
			}catch(Exception e){
				logger.error("Exception in updatePassword(LoginForm login) method in Utility for LDAP operation EXCEPTION: ",e);
				passwordStatus="fail";
			}
			return passwordStatus;
		}

		/**
		 * this method is used to delete an user from LDAP
		 */
		
		public String deletePassword(LoginForm login) {
			String status="fail";
			logger.info("deletePassword(LoginForm login) method in Utility");
			try{
				ldapTemplate.unbind(buildDn(login));
				status="success";
			}catch(Exception e){
				logger.error("Exception in deletePassword(LoginForm login) method in Utility for LDAP operation EXCEPTION: ",e);
				status="fail";
			}
			return status;
		}
	
	

}
