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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for S_AssignResource
 *  @author iDempiere (generated)
 *  @version Release 11 - $Id$ */
@org.adempiere.base.Model(table="S_AssignResource")
public class X_S_AssignResource extends PO implements I_S_AssignResource, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250303L;

    /** Standard Constructor */
    public X_S_AssignResource (Properties ctx, int S_AssignResource_ID, String trxName)
    {
      super (ctx, S_AssignResource_ID, trxName);
      /** if (S_AssignResource_ID == 0)
        {
			setAssignDateFrom (new Timestamp( System.currentTimeMillis() ));
			setIsConfirmed (false);
// N
			setS_AssignResource_ID (0);
			setS_Resource_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_S_AssignResource (Properties ctx, int S_AssignResource_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, S_AssignResource_ID, trxName, virtualColumns);
      /** if (S_AssignResource_ID == 0)
        {
			setAssignDateFrom (new Timestamp( System.currentTimeMillis() ));
			setIsConfirmed (false);
// N
			setS_AssignResource_ID (0);
			setS_Resource_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_S_AssignResource (Properties ctx, String S_AssignResource_UU, String trxName)
    {
      super (ctx, S_AssignResource_UU, trxName);
      /** if (S_AssignResource_UU == null)
        {
			setAssignDateFrom (new Timestamp( System.currentTimeMillis() ));
			setIsConfirmed (false);
// N
			setS_AssignResource_ID (0);
			setS_Resource_ID (0);
        } */
    }

    /** Standard Constructor */
    public X_S_AssignResource (Properties ctx, String S_AssignResource_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, S_AssignResource_UU, trxName, virtualColumns);
      /** if (S_AssignResource_UU == null)
        {
			setAssignDateFrom (new Timestamp( System.currentTimeMillis() ));
			setIsConfirmed (false);
// N
			setS_AssignResource_ID (0);
			setS_Resource_ID (0);
        } */
    }

    /** Load Constructor */
    public X_S_AssignResource (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_S_AssignResource[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Assign From.
		@param AssignDateFrom Assign resource from
	*/
	public void setAssignDateFrom (Timestamp AssignDateFrom)
	{
		set_ValueNoCheck (COLUMNNAME_AssignDateFrom, AssignDateFrom);
	}

	/** Get Assign From.
		@return Assign resource from
	  */
	public Timestamp getAssignDateFrom()
	{
		return (Timestamp)get_Value(COLUMNNAME_AssignDateFrom);
	}

	/** Set Assign To.
		@param AssignDateTo Assign resource until
	*/
	public void setAssignDateTo (Timestamp AssignDateTo)
	{
		set_ValueNoCheck (COLUMNNAME_AssignDateTo, AssignDateTo);
	}

	/** Get Assign To.
		@return Assign resource until
	  */
	public Timestamp getAssignDateTo()
	{
		return (Timestamp)get_Value(COLUMNNAME_AssignDateTo);
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
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
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

	/** Set Description.
		@param Description Optional short description of the record
	*/
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription()
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Confirmed.
		@param IsConfirmed Assignment is confirmed
	*/
	public void setIsConfirmed (boolean IsConfirmed)
	{
		set_Value (COLUMNNAME_IsConfirmed, Boolean.valueOf(IsConfirmed));
	}

	/** Get Confirmed.
		@return Assignment is confirmed
	  */
	public boolean isConfirmed()
	{
		Object oo = get_Value(COLUMNNAME_IsConfirmed);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Quantity.
		@param Qty Quantity
	*/
	public void setQty (BigDecimal Qty)
	{
		set_Value (COLUMNNAME_Qty, Qty);
	}

	/** Get Quantity.
		@return Quantity
	  */
	public BigDecimal getQty()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set S_AssignResource.
		@param S_AssignResource_ID S_AssignResource
	*/
	public void setS_AssignResource_ID (int S_AssignResource_ID)
	{
		if (S_AssignResource_ID < 1)
			set_ValueNoCheck (COLUMNNAME_S_AssignResource_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_S_AssignResource_ID, Integer.valueOf(S_AssignResource_ID));
	}

	/** Get S_AssignResource.
		@return S_AssignResource	  */
	public int getS_AssignResource_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_S_AssignResource_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set S_AssignResource_UU.
		@param S_AssignResource_UU S_AssignResource_UU
	*/
	public void setS_AssignResource_UU (String S_AssignResource_UU)
	{
		set_ValueNoCheck (COLUMNNAME_S_AssignResource_UU, S_AssignResource_UU);
	}

	/** Get S_AssignResource_UU.
		@return S_AssignResource_UU	  */
	public String getS_AssignResource_UU()
	{
		return (String)get_Value(COLUMNNAME_S_AssignResource_UU);
	}

	public org.compiere.model.I_S_Resource getS_Resource() throws RuntimeException
	{
		return (org.compiere.model.I_S_Resource)MTable.get(getCtx(), org.compiere.model.I_S_Resource.Table_ID)
			.getPO(getS_Resource_ID(), get_TrxName());
	}

	/** Set Resource.
		@param S_Resource_ID Resource
	*/
	public void setS_Resource_ID (int S_Resource_ID)
	{
		if (S_Resource_ID < 1)
			set_ValueNoCheck (COLUMNNAME_S_Resource_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_S_Resource_ID, Integer.valueOf(S_Resource_ID));
	}

	/** Get Resource.
		@return Resource
	  */
	public int getS_Resource_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_S_Resource_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}