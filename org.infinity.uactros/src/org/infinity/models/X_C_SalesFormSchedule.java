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

/** Generated Model for C_SalesFormSchedule
 *  @author iDempiere (generated)
 *  @version Release 11 - $Id$ */
@org.adempiere.base.Model(table="C_SalesFormSchedule")
public class X_C_SalesFormSchedule extends PO implements I_C_SalesFormSchedule, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20250303L;

    /** Standard Constructor */
    public X_C_SalesFormSchedule (Properties ctx, int C_SalesFormSchedule_ID, String trxName)
    {
      super (ctx, C_SalesFormSchedule_ID, trxName);
      /** if (C_SalesFormSchedule_ID == 0)
        {
			setC_SalesFormSchedule_ID (0);
			setDiscountAmt (Env.ZERO);
			setDiscountDate (new Timestamp( System.currentTimeMillis() ));
			setDueAmt (Env.ZERO);
			setDueDate (new Timestamp( System.currentTimeMillis() ));
			setIsValid (false);
// N
			setProcessed (false);
// N
        } */
    }

    /** Standard Constructor */
    public X_C_SalesFormSchedule (Properties ctx, int C_SalesFormSchedule_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, C_SalesFormSchedule_ID, trxName, virtualColumns);
      /** if (C_SalesFormSchedule_ID == 0)
        {
			setC_SalesFormSchedule_ID (0);
			setDiscountAmt (Env.ZERO);
			setDiscountDate (new Timestamp( System.currentTimeMillis() ));
			setDueAmt (Env.ZERO);
			setDueDate (new Timestamp( System.currentTimeMillis() ));
			setIsValid (false);
// N
			setProcessed (false);
// N
        } */
    }

    /** Standard Constructor */
    public X_C_SalesFormSchedule (Properties ctx, String C_SalesFormSchedule_UU, String trxName)
    {
      super (ctx, C_SalesFormSchedule_UU, trxName);
      /** if (C_SalesFormSchedule_UU == null)
        {
			setC_SalesFormSchedule_ID (0);
			setDiscountAmt (Env.ZERO);
			setDiscountDate (new Timestamp( System.currentTimeMillis() ));
			setDueAmt (Env.ZERO);
			setDueDate (new Timestamp( System.currentTimeMillis() ));
			setIsValid (false);
// N
			setProcessed (false);
// N
        } */
    }

    /** Standard Constructor */
    public X_C_SalesFormSchedule (Properties ctx, String C_SalesFormSchedule_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, C_SalesFormSchedule_UU, trxName, virtualColumns);
      /** if (C_SalesFormSchedule_UU == null)
        {
			setC_SalesFormSchedule_ID (0);
			setDiscountAmt (Env.ZERO);
			setDiscountDate (new Timestamp( System.currentTimeMillis() ));
			setDueAmt (Env.ZERO);
			setDueDate (new Timestamp( System.currentTimeMillis() ));
			setIsValid (false);
// N
			setProcessed (false);
// N
        } */
    }

    /** Load Constructor */
    public X_C_SalesFormSchedule (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_C_SalesFormSchedule[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_PaySchedule getC_PaySchedule() throws RuntimeException
	{
		return (org.compiere.model.I_C_PaySchedule)MTable.get(getCtx(), org.compiere.model.I_C_PaySchedule.Table_ID)
			.getPO(getC_PaySchedule_ID(), get_TrxName());
	}

	/** Set Payment Schedule.
		@param C_PaySchedule_ID Payment Schedule Template
	*/
	public void setC_PaySchedule_ID (int C_PaySchedule_ID)
	{
		if (C_PaySchedule_ID < 1)
			set_ValueNoCheck (COLUMNNAME_C_PaySchedule_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_C_PaySchedule_ID, Integer.valueOf(C_PaySchedule_ID));
	}

	/** Get Payment Schedule.
		@return Payment Schedule Template
	  */
	public int getC_PaySchedule_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_PaySchedule_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sales Form Schedule.
		@param C_SalesFormSchedule_ID Sales Form Schedule
	*/
	public void setC_SalesFormSchedule_ID (int C_SalesFormSchedule_ID)
	{
		if (C_SalesFormSchedule_ID < 1)
			set_ValueNoCheck (COLUMNNAME_C_SalesFormSchedule_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_C_SalesFormSchedule_ID, Integer.valueOf(C_SalesFormSchedule_ID));
	}

	/** Get Sales Form Schedule.
		@return Sales Form Schedule	  */
	public int getC_SalesFormSchedule_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_SalesFormSchedule_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set C_SalesFormSchedule_UU.
		@param C_SalesFormSchedule_UU C_SalesFormSchedule_UU
	*/
	public void setC_SalesFormSchedule_UU (String C_SalesFormSchedule_UU)
	{
		set_ValueNoCheck (COLUMNNAME_C_SalesFormSchedule_UU, C_SalesFormSchedule_UU);
	}

	/** Get C_SalesFormSchedule_UU.
		@return C_SalesFormSchedule_UU	  */
	public String getC_SalesFormSchedule_UU()
	{
		return (String)get_Value(COLUMNNAME_C_SalesFormSchedule_UU);
	}

	/** Set Discount Amount.
		@param DiscountAmt Calculated amount of discount
	*/
	public void setDiscountAmt (BigDecimal DiscountAmt)
	{
		set_ValueNoCheck (COLUMNNAME_DiscountAmt, DiscountAmt);
	}

	/** Get Discount Amount.
		@return Calculated amount of discount
	  */
	public BigDecimal getDiscountAmt()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DiscountAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Discount Date.
		@param DiscountDate Last Date for payments with discount
	*/
	public void setDiscountDate (Timestamp DiscountDate)
	{
		set_Value (COLUMNNAME_DiscountDate, DiscountDate);
	}

	/** Get Discount Date.
		@return Last Date for payments with discount
	  */
	public Timestamp getDiscountDate()
	{
		return (Timestamp)get_Value(COLUMNNAME_DiscountDate);
	}

	/** Set Amount due.
		@param DueAmt Amount of the payment due
	*/
	public void setDueAmt (BigDecimal DueAmt)
	{
		set_Value (COLUMNNAME_DueAmt, DueAmt);
	}

	/** Get Amount due.
		@return Amount of the payment due
	  */
	public BigDecimal getDueAmt()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DueAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Due Date.
		@param DueDate Date when the payment is due
	*/
	public void setDueDate (Timestamp DueDate)
	{
		set_Value (COLUMNNAME_DueDate, DueDate);
	}

	/** Get Due Date.
		@return Date when the payment is due
	  */
	public Timestamp getDueDate()
	{
		return (Timestamp)get_Value(COLUMNNAME_DueDate);
	}

	/** Set Valid.
		@param IsValid Element is valid
	*/
	public void setIsValid (boolean IsValid)
	{
		set_Value (COLUMNNAME_IsValid, Boolean.valueOf(IsValid));
	}

	/** Get Valid.
		@return Element is valid
	  */
	public boolean isValid()
	{
		Object oo = get_Value(COLUMNNAME_IsValid);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Processed.
		@param Processed The document has been processed
	*/
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed()
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now
	*/
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing()
	{
		Object oo = get_Value(COLUMNNAME_Processing);
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
}