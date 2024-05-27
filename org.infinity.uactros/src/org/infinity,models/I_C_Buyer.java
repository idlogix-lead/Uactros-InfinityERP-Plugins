/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.infinity,models;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_Buyer
 *  @author iDempiere (generated) 
 *  @version Release 12
 */
@SuppressWarnings("all")
public interface I_C_Buyer 
{

    /** TableName=C_Buyer */
    public static final String Table_Name = "C_Buyer";

    /** AD_Table_ID=1000035 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Tenant.
	  * Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Image_ID */
    public static final String COLUMNNAME_AD_Image_ID = "AD_Image_ID";

	/** Set Image.
	  * Image or Icon
	  */
	public void setAD_Image_ID (int AD_Image_ID);

	/** Get Image.
	  * Image or Icon
	  */
	public int getAD_Image_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within tenant
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within tenant
	  */
	public int getAD_Org_ID();

    /** Column name Birthday */
    public static final String COLUMNNAME_Birthday = "Birthday";

	/** Set Birthday.
	  * Birthday or Anniversary day
	  */
	public void setBirthday (Timestamp Birthday);

	/** Get Birthday.
	  * Birthday or Anniversary day
	  */
	public Timestamp getBirthday();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner.
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner.
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_Buyer_ID */
    public static final String COLUMNNAME_C_Buyer_ID = "C_Buyer_ID";

	/** Set Buyer.
	  * Define Buyer Profile
	  */
	public void setC_Buyer_ID (int C_Buyer_ID);

	/** Get Buyer.
	  * Define Buyer Profile
	  */
	public int getC_Buyer_ID();

    /** Column name C_City_ID */
    public static final String COLUMNNAME_C_City_ID = "C_City_ID";

	/** Set City.
	  * City
	  */
	public void setC_City_ID (int C_City_ID);

	/** Get City.
	  * City
	  */
	public int getC_City_ID();

	public org.compiere.model.I_C_City getC_City() throws RuntimeException;

    /** Column name C_Country_ID */
    public static final String COLUMNNAME_C_Country_ID = "C_Country_ID";

	/** Set Country.
	  * Country 
	  */
	public void setC_Country_ID (int C_Country_ID);

	/** Get Country.
	  * Country 
	  */
	public int getC_Country_ID();

	public org.compiere.model.I_C_Country getC_Country() throws RuntimeException;

    /** Column name City */
    public static final String COLUMNNAME_City = "City";

	/** Set City.
	  * Identifies a City
	  */
	public void setCity (String City);

	/** Get City.
	  * Identifies a City
	  */
	public String getCity();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name EMail */
    public static final String COLUMNNAME_EMail = "EMail";

	/** Set EMail Address.
	  * Electronic Mail Address
	  */
	public void setEMail (String EMail);

	/** Get EMail Address.
	  * Electronic Mail Address
	  */
	public String getEMail();

    /** Column name Fh_Name */
    public static final String COLUMNNAME_Fh_Name = "Fh_Name";

	/** Set Father/Husband Name	  */
	public void setFh_Name (String Fh_Name);

	/** Get Father/Husband Name	  */
	public String getFh_Name();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name Job */
    public static final String COLUMNNAME_Job = "Job";

	/** Set Job	  */
	public void setJob (String Job);

	/** Get Job	  */
	public String getJob();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name NationalCode */
    public static final String COLUMNNAME_NationalCode = "NationalCode";

	/** Set National Code	  */
	public void setNationalCode (String NationalCode);

	/** Get National Code	  */
	public String getNationalCode();

    /** Column name Nationality */
    public static final String COLUMNNAME_Nationality = "Nationality";

	/** Set Nationality	  */
	public void setNationality (String Nationality);

	/** Get Nationality	  */
	public String getNationality();

    /** Column name Nominee_Address1 */
    public static final String COLUMNNAME_Nominee_Address1 = "Nominee_Address1";

	/** Set Nominee Address 1	  */
	public void setNominee_Address1 (String Nominee_Address1);

	/** Get Nominee Address 1	  */
	public String getNominee_Address1();

    /** Column name Nominee_Address2 */
    public static final String COLUMNNAME_Nominee_Address2 = "Nominee_Address2";

	/** Set Nominee Address 2	  */
	public void setNominee_Address2 (String Nominee_Address2);

	/** Get Nominee Address 2	  */
	public String getNominee_Address2();

    /** Column name Nominee_Birthday */
    public static final String COLUMNNAME_Nominee_Birthday = "Nominee_Birthday";

	/** Set Nominee Birthday	  */
	public void setNominee_Birthday (Timestamp Nominee_Birthday);

	/** Get Nominee Birthday	  */
	public Timestamp getNominee_Birthday();

    /** Column name Nominee_City */
    public static final String COLUMNNAME_Nominee_City = "Nominee_City";

	/** Set Nominee_City	  */
	public void setNominee_City (String Nominee_City);

	/** Get Nominee_City	  */
	public String getNominee_City();

    /** Column name Nominee_Country */
    public static final String COLUMNNAME_Nominee_Country = "Nominee_Country";

	/** Set Nominee_Country	  */
	public void setNominee_Country (int Nominee_Country);

	/** Get Nominee_Country	  */
	public int getNominee_Country();

    /** Column name Nominee_EMail */
    public static final String COLUMNNAME_Nominee_EMail = "Nominee_EMail";

	/** Set Nominee Email.
	  * Electronic Mail Address
	  */
	public void setNominee_EMail (String Nominee_EMail);

	/** Get Nominee Email.
	  * Electronic Mail Address
	  */
	public String getNominee_EMail();

    /** Column name Nominee_Job */
    public static final String COLUMNNAME_Nominee_Job = "Nominee_Job";

	/** Set Nominee_Job	  */
	public void setNominee_Job (String Nominee_Job);

	/** Get Nominee_Job	  */
	public String getNominee_Job();

    /** Column name Nominee_Name */
    public static final String COLUMNNAME_Nominee_Name = "Nominee_Name";

	/** Set Nominee Name	  */
	public void setNominee_Name (String Nominee_Name);

	/** Get Nominee Name	  */
	public String getNominee_Name();

    /** Column name Nominee_NationalCode */
    public static final String COLUMNNAME_Nominee_NationalCode = "Nominee_NationalCode";

	/** Set Nominee National Code	  */
	public void setNominee_NationalCode (String Nominee_NationalCode);

	/** Get Nominee National Code	  */
	public String getNominee_NationalCode();

    /** Column name Nominee_Nationality */
    public static final String COLUMNNAME_Nominee_Nationality = "Nominee_Nationality";

	/** Set Nominee Nationality	  */
	public void setNominee_Nationality (String Nominee_Nationality);

	/** Get Nominee Nationality	  */
	public String getNominee_Nationality();

    /** Column name Nominee_Phone */
    public static final String COLUMNNAME_Nominee_Phone = "Nominee_Phone";

	/** Set Nominee_Phone.
	  * Identifies a telephone number
	  */
	public void setNominee_Phone (String Nominee_Phone);

	/** Get Nominee_Phone.
	  * Identifies a telephone number
	  */
	public String getNominee_Phone();

    /** Column name Nominee_Phone2 */
    public static final String COLUMNNAME_Nominee_Phone2 = "Nominee_Phone2";

	/** Set Nominee_Phone2.
	  * Identifies an alternate telephone number.
	  */
	public void setNominee_Phone2 (String Nominee_Phone2);

	/** Get Nominee_Phone2.
	  * Identifies an alternate telephone number.
	  */
	public String getNominee_Phone2();

    /** Column name Nominee_Relation */
    public static final String COLUMNNAME_Nominee_Relation = "Nominee_Relation";

	/** Set Nominee Relation	  */
	public void setNominee_Relation (String Nominee_Relation);

	/** Get Nominee Relation	  */
	public String getNominee_Relation();

    /** Column name NomineeFh_Name */
    public static final String COLUMNNAME_NomineeFh_Name = "NomineeFh_Name";

	/** Set NomineeFh_Name	  */
	public void setNomineeFh_Name (String NomineeFh_Name);

	/** Get NomineeFh_Name	  */
	public String getNomineeFh_Name();

    /** Column name Phone */
    public static final String COLUMNNAME_Phone = "Phone";

	/** Set Phone.
	  * Identifies a telephone number
	  */
	public void setPhone (String Phone);

	/** Get Phone.
	  * Identifies a telephone number
	  */
	public String getPhone();

    /** Column name Phone2 */
    public static final String COLUMNNAME_Phone2 = "Phone2";

	/** Set 2nd Phone.
	  * Identifies an alternate telephone number.
	  */
	public void setPhone2 (String Phone2);

	/** Get 2nd Phone.
	  * Identifies an alternate telephone number.
	  */
	public String getPhone2();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();

    /** Column name UserAddress1 */
    public static final String COLUMNNAME_UserAddress1 = "UserAddress1";

	/** Set User Address 1	  */
	public void setUserAddress1 (String UserAddress1);

	/** Get User Address 1	  */
	public String getUserAddress1();

    /** Column name UserAddress2 */
    public static final String COLUMNNAME_UserAddress2 = "UserAddress2";

	/** Set User Address 2	  */
	public void setUserAddress2 (String UserAddress2);

	/** Get User Address 2	  */
	public String getUserAddress2();

    /** Column name UserID */
    public static final String COLUMNNAME_UserID = "UserID";

	/** Set User ID.
	  * User ID or account number
	  */
	public void setUserID (String UserID);

	/** Get User ID.
	  * User ID or account number
	  */
	public String getUserID();
}
