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
/** Generated Model - DO NOT CHANGE */
package org.infinity.models;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for C_Buyer
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="C_Buyer")
public class X_C_Buyer extends PO implements I_C_Buyer, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20240527L;

    /** Standard Constructor */
    public X_C_Buyer (Properties ctx, int C_Buyer_ID, String trxName)
    {
      super (ctx, C_Buyer_ID, trxName);
      /** if (C_Buyer_ID == 0)
        {
			setC_Buyer_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_C_Buyer (Properties ctx, int C_Buyer_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, C_Buyer_ID, trxName, virtualColumns);
      /** if (C_Buyer_ID == 0)
        {
			setC_Buyer_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_C_Buyer (Properties ctx, String C_Buyer_UU, String trxName)
    {
      super (ctx, C_Buyer_UU, trxName);
      /** if (C_Buyer_UU == null)
        {
			setC_Buyer_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_C_Buyer (Properties ctx, String C_Buyer_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, C_Buyer_UU, trxName, virtualColumns);
      /** if (C_Buyer_UU == null)
        {
			setC_Buyer_ID (0);
        } */
    }

    /** Load Constructor */
    public X_C_Buyer (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuilder sb = new StringBuilder ("X_C_Buyer[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	/** Set Image.
		@param AD_Image_ID Image or Icon
	*/
	public void setAD_Image_ID (int AD_Image_ID)
	{
		if (AD_Image_ID < 1)
			set_Value (COLUMNNAME_AD_Image_ID, null);
		else
			set_Value (COLUMNNAME_AD_Image_ID, Integer.valueOf(AD_Image_ID));
	}

	/** Get Image.
		@return Image or Icon
	  */
	public int getAD_Image_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Image_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException
	{
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_ID)
			.getPO(getAD_User_ID(), get_TrxName());
	}

	/** Set User/Contact.
		@param AD_User_ID User within the system - Internal or Business Partner Contact
	*/
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1)
			set_Value (COLUMNNAME_AD_User_ID, null);
		else
			set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Birthday.
		@param Birthday Birthday or Anniversary day
	*/
	public void setBirthday (Timestamp Birthday)
	{
		set_Value (COLUMNNAME_Birthday, Birthday);
	}

	/** Get Birthday.
		@return Birthday or Anniversary day
	  */
	public Timestamp getBirthday()
	{
		return (Timestamp)get_Value(COLUMNNAME_Birthday);
	}

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
	{
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_ID)
			.getPO(getC_BPartner_ID(), get_TrxName());
	}

	/** Set Business Partner.
		@param C_BPartner_ID Identifies a Business Partner
	*/
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1)
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner.
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Buyer.
		@param C_Buyer_ID Define Buyer Profile
	*/
	public void setC_Buyer_ID (int C_Buyer_ID)
	{
		if (C_Buyer_ID < 1)
			set_ValueNoCheck (COLUMNNAME_C_Buyer_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_C_Buyer_ID, Integer.valueOf(C_Buyer_ID));
	}

	/** Get Buyer.
		@return Define Buyer Profile
	  */
	public int getC_Buyer_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Buyer_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_City getC_City() throws RuntimeException
	{
		return (org.compiere.model.I_C_City)MTable.get(getCtx(), org.compiere.model.I_C_City.Table_ID)
			.getPO(getC_City_ID(), get_TrxName());
	}

	/** Set City.
		@param C_City_ID City
	*/
	public void setC_City_ID (int C_City_ID)
	{
		if (C_City_ID < 1)
			set_ValueNoCheck (COLUMNNAME_C_City_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_C_City_ID, Integer.valueOf(C_City_ID));
	}

	/** Get City.
		@return City
	  */
	public int getC_City_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_City_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Country getC_Country() throws RuntimeException
	{
		return (org.compiere.model.I_C_Country)MTable.get(getCtx(), org.compiere.model.I_C_Country.Table_ID)
			.getPO(getC_Country_ID(), get_TrxName());
	}

	/** Set Country.
		@param C_Country_ID Country 
	*/
	public void setC_Country_ID (int C_Country_ID)
	{
		if (C_Country_ID < 1)
			set_Value (COLUMNNAME_C_Country_ID, null);
		else
			set_Value (COLUMNNAME_C_Country_ID, Integer.valueOf(C_Country_ID));
	}

	/** Get Country.
		@return Country 
	  */
	public int getC_Country_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Country_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set City.
		@param City Identifies a City
	*/
	public void setCity (String City)
	{
		set_Value (COLUMNNAME_City, City);
	}

	/** Get City.
		@return Identifies a City
	  */
	public String getCity()
	{
		return (String)get_Value(COLUMNNAME_City);
	}

	/** Set EMail Address.
		@param EMail Electronic Mail Address
	*/
	public void setEMail (String EMail)
	{
		set_Value (COLUMNNAME_EMail, EMail);
	}

	/** Get EMail Address.
		@return Electronic Mail Address
	  */
	public String getEMail()
	{
		return (String)get_Value(COLUMNNAME_EMail);
	}

	/** Set Father/Husband Name.
		@param Fh_Name Father/Husband Name
	*/
	public void setFh_Name (String Fh_Name)
	{
		set_Value (COLUMNNAME_Fh_Name, Fh_Name);
	}

	/** Get Father/Husband Name.
		@return Father/Husband Name	  */
	public String getFh_Name()
	{
		return (String)get_Value(COLUMNNAME_Fh_Name);
	}

	/** Set Job.
		@param Job Job
	*/
	public void setJob (String Job)
	{
		set_Value (COLUMNNAME_Job, Job);
	}

	/** Get Job.
		@return Job	  */
	public String getJob()
	{
		return (String)get_Value(COLUMNNAME_Job);
	}

	/** Set Name.
		@param Name Alphanumeric identifier of the entity
	*/
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName()
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Set National Code.
		@param NationalCode National Code
	*/
	public void setNationalCode (String NationalCode)
	{
		set_Value (COLUMNNAME_NationalCode, NationalCode);
	}

	/** Get National Code.
		@return National Code	  */
	public String getNationalCode()
	{
		return (String)get_Value(COLUMNNAME_NationalCode);
	}

	/** Set Nationality.
		@param Nationality Nationality
	*/
	public void setNationality (String Nationality)
	{
		set_Value (COLUMNNAME_Nationality, Nationality);
	}

	/** Get Nationality.
		@return Nationality	  */
	public String getNationality()
	{
		return (String)get_Value(COLUMNNAME_Nationality);
	}

	/** Set Nominee Address 1.
		@param Nominee_Address1 Nominee Address 1
	*/
	public void setNominee_Address1 (String Nominee_Address1)
	{
		set_Value (COLUMNNAME_Nominee_Address1, Nominee_Address1);
	}

	/** Get Nominee Address 1.
		@return Nominee Address 1	  */
	public String getNominee_Address1()
	{
		return (String)get_Value(COLUMNNAME_Nominee_Address1);
	}

	/** Set Nominee Address 2.
		@param Nominee_Address2 Nominee Address 2
	*/
	public void setNominee_Address2 (String Nominee_Address2)
	{
		set_Value (COLUMNNAME_Nominee_Address2, Nominee_Address2);
	}

	/** Get Nominee Address 2.
		@return Nominee Address 2	  */
	public String getNominee_Address2()
	{
		return (String)get_Value(COLUMNNAME_Nominee_Address2);
	}

	/** Set Nominee Birthday.
		@param Nominee_Birthday Nominee Birthday
	*/
	public void setNominee_Birthday (Timestamp Nominee_Birthday)
	{
		set_Value (COLUMNNAME_Nominee_Birthday, Nominee_Birthday);
	}

	/** Get Nominee Birthday.
		@return Nominee Birthday	  */
	public Timestamp getNominee_Birthday()
	{
		return (Timestamp)get_Value(COLUMNNAME_Nominee_Birthday);
	}

	/** Set Nominee_City.
		@param Nominee_City Nominee_City
	*/
	public void setNominee_City (String Nominee_City)
	{
		set_Value (COLUMNNAME_Nominee_City, Nominee_City);
	}

	/** Get Nominee_City.
		@return Nominee_City	  */
	public String getNominee_City()
	{
		return (String)get_Value(COLUMNNAME_Nominee_City);
	}

	/** Set Nominee_Country.
		@param Nominee_Country Nominee_Country
	*/
	public void setNominee_Country (int Nominee_Country)
	{
		set_Value (COLUMNNAME_Nominee_Country, Integer.valueOf(Nominee_Country));
	}

	/** Get Nominee_Country.
		@return Nominee_Country	  */
	public int getNominee_Country()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Nominee_Country);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Nominee Email.
		@param Nominee_EMail Electronic Mail Address
	*/
	public void setNominee_EMail (String Nominee_EMail)
	{
		set_Value (COLUMNNAME_Nominee_EMail, Nominee_EMail);
	}

	/** Get Nominee Email.
		@return Electronic Mail Address
	  */
	public String getNominee_EMail()
	{
		return (String)get_Value(COLUMNNAME_Nominee_EMail);
	}

	/** Set Nominee_Job.
		@param Nominee_Job Nominee_Job
	*/
	public void setNominee_Job (String Nominee_Job)
	{
		set_Value (COLUMNNAME_Nominee_Job, Nominee_Job);
	}

	/** Get Nominee_Job.
		@return Nominee_Job	  */
	public String getNominee_Job()
	{
		return (String)get_Value(COLUMNNAME_Nominee_Job);
	}

	/** Set Nominee Name.
		@param Nominee_Name Nominee Name
	*/
	public void setNominee_Name (String Nominee_Name)
	{
		set_Value (COLUMNNAME_Nominee_Name, Nominee_Name);
	}

	/** Get Nominee Name.
		@return Nominee Name	  */
	public String getNominee_Name()
	{
		return (String)get_Value(COLUMNNAME_Nominee_Name);
	}

	/** Set Nominee National Code.
		@param Nominee_NationalCode Nominee National Code
	*/
	public void setNominee_NationalCode (String Nominee_NationalCode)
	{
		set_Value (COLUMNNAME_Nominee_NationalCode, Nominee_NationalCode);
	}

	/** Get Nominee National Code.
		@return Nominee National Code	  */
	public String getNominee_NationalCode()
	{
		return (String)get_Value(COLUMNNAME_Nominee_NationalCode);
	}

	/** Set Nominee Nationality.
		@param Nominee_Nationality Nominee Nationality
	*/
	public void setNominee_Nationality (String Nominee_Nationality)
	{
		set_Value (COLUMNNAME_Nominee_Nationality, Nominee_Nationality);
	}

	/** Get Nominee Nationality.
		@return Nominee Nationality	  */
	public String getNominee_Nationality()
	{
		return (String)get_Value(COLUMNNAME_Nominee_Nationality);
	}

	/** Set Nominee_Phone.
		@param Nominee_Phone Identifies a telephone number
	*/
	public void setNominee_Phone (String Nominee_Phone)
	{
		set_Value (COLUMNNAME_Nominee_Phone, Nominee_Phone);
	}

	/** Get Nominee_Phone.
		@return Identifies a telephone number
	  */
	public String getNominee_Phone()
	{
		return (String)get_Value(COLUMNNAME_Nominee_Phone);
	}

	/** Set Nominee_Phone2.
		@param Nominee_Phone2 Identifies an alternate telephone number.
	*/
	public void setNominee_Phone2 (String Nominee_Phone2)
	{
		set_Value (COLUMNNAME_Nominee_Phone2, Nominee_Phone2);
	}

	/** Get Nominee_Phone2.
		@return Identifies an alternate telephone number.
	  */
	public String getNominee_Phone2()
	{
		return (String)get_Value(COLUMNNAME_Nominee_Phone2);
	}

	/** Set Nominee Relation.
		@param Nominee_Relation Nominee Relation
	*/
	public void setNominee_Relation (String Nominee_Relation)
	{
		set_Value (COLUMNNAME_Nominee_Relation, Nominee_Relation);
	}

	/** Get Nominee Relation.
		@return Nominee Relation	  */
	public String getNominee_Relation()
	{
		return (String)get_Value(COLUMNNAME_Nominee_Relation);
	}

	/** Set NomineeFh_Name.
		@param NomineeFh_Name NomineeFh_Name
	*/
	public void setNomineeFh_Name (String NomineeFh_Name)
	{
		set_Value (COLUMNNAME_NomineeFh_Name, NomineeFh_Name);
	}

	/** Get NomineeFh_Name.
		@return NomineeFh_Name	  */
	public String getNomineeFh_Name()
	{
		return (String)get_Value(COLUMNNAME_NomineeFh_Name);
	}

	/** Set Phone.
		@param Phone Identifies a telephone number
	*/
	public void setPhone (String Phone)
	{
		set_Value (COLUMNNAME_Phone, Phone);
	}

	/** Get Phone.
		@return Identifies a telephone number
	  */
	public String getPhone()
	{
		return (String)get_Value(COLUMNNAME_Phone);
	}

	/** Set 2nd Phone.
		@param Phone2 Identifies an alternate telephone number.
	*/
	public void setPhone2 (String Phone2)
	{
		set_Value (COLUMNNAME_Phone2, Phone2);
	}

	/** Get 2nd Phone.
		@return Identifies an alternate telephone number.
	  */
	public String getPhone2()
	{
		return (String)get_Value(COLUMNNAME_Phone2);
	}

	/** Set User Address 1.
		@param UserAddress1 User Address 1
	*/
	public void setUserAddress1 (String UserAddress1)
	{
		set_Value (COLUMNNAME_UserAddress1, UserAddress1);
	}

	/** Get User Address 1.
		@return User Address 1	  */
	public String getUserAddress1()
	{
		return (String)get_Value(COLUMNNAME_UserAddress1);
	}

	/** Set User Address 2.
		@param UserAddress2 User Address 2
	*/
	public void setUserAddress2 (String UserAddress2)
	{
		set_Value (COLUMNNAME_UserAddress2, UserAddress2);
	}

	/** Get User Address 2.
		@return User Address 2	  */
	public String getUserAddress2()
	{
		return (String)get_Value(COLUMNNAME_UserAddress2);
	}
}