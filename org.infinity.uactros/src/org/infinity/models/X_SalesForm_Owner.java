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
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for SalesForm_Owner
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="SalesForm_Owner")
public class X_SalesForm_Owner extends PO implements I_SalesForm_Owner, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20240527L;

    /** Standard Constructor */
    public X_SalesForm_Owner (Properties ctx, int SalesForm_Owner_ID, String trxName)
    {
      super (ctx, SalesForm_Owner_ID, trxName);
      /** if (SalesForm_Owner_ID == 0)
        {
			setCurrent_Owner (false);
// N
        } */
    }

    /** Standard Constructor */
    public X_SalesForm_Owner (Properties ctx, int SalesForm_Owner_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, SalesForm_Owner_ID, trxName, virtualColumns);
      /** if (SalesForm_Owner_ID == 0)
        {
			setCurrent_Owner (false);
// N
        } */
    }

    /** Standard Constructor */
    public X_SalesForm_Owner (Properties ctx, String SalesForm_Owner_UU, String trxName)
    {
      super (ctx, SalesForm_Owner_UU, trxName);
      /** if (SalesForm_Owner_UU == null)
        {
			setCurrent_Owner (false);
// N
        } */
    }

    /** Standard Constructor */
    public X_SalesForm_Owner (Properties ctx, String SalesForm_Owner_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, SalesForm_Owner_UU, trxName, virtualColumns);
      /** if (SalesForm_Owner_UU == null)
        {
			setCurrent_Owner (false);
// N
        } */
    }

    /** Load Constructor */
    public X_SalesForm_Owner (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_SalesForm_Owner[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_Buyer getC_Buyer() throws RuntimeException
	{
		return (I_C_Buyer)MTable.get(getCtx(), I_C_Buyer.Table_ID)
			.getPO(getC_Buyer_ID(), get_TrxName());
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

	/** Set Current_Owner.
		@param Current_Owner Who is the current own er of the Sales Form
	*/
	public void setCurrent_Owner (boolean Current_Owner)
	{
		set_Value (COLUMNNAME_Current_Owner, Boolean.valueOf(Current_Owner));
	}

	/** Get Current_Owner.
		@return Who is the current own er of the Sales Form
	  */
	public boolean isCurrent_Owner()
	{
		Object oo = get_Value(COLUMNNAME_Current_Owner);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	public I_SalesForm getSalesForm() throws RuntimeException
	{
		return (I_SalesForm)MTable.get(getCtx(), I_SalesForm.Table_ID)
			.getPO(getSalesForm_ID(), get_TrxName());
	}

	/** Set SalesForm.
		@param SalesForm_ID SalesForm
	*/
	public void setSalesForm_ID (int SalesForm_ID)
	{
		if (SalesForm_ID < 1)
			set_Value (COLUMNNAME_SalesForm_ID, null);
		else
			set_Value (COLUMNNAME_SalesForm_ID, Integer.valueOf(SalesForm_ID));
	}

	/** Get SalesForm.
		@return SalesForm	  */
	public int getSalesForm_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SalesForm_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sales Form Owner.
		@param SalesForm_Owner_ID Who Own the Sales Form
	*/
	public void setSalesForm_Owner_ID (int SalesForm_Owner_ID)
	{
		if (SalesForm_Owner_ID < 1)
			set_ValueNoCheck (COLUMNNAME_SalesForm_Owner_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_SalesForm_Owner_ID, Integer.valueOf(SalesForm_Owner_ID));
	}

	/** Get Sales Form Owner.
		@return Who Own the Sales Form
	  */
	public int getSalesForm_Owner_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SalesForm_Owner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}