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

/** Generated Model for SalesForm_Hdr
 *  @author iDempiere (generated)
 *  @version Release 11 - $Id$ */
@org.adempiere.base.Model(table="SalesForm_Hdr")
public class X_SalesForm_Hdr extends PO implements I_SalesForm_Hdr, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20240308L;

    /** Standard Constructor */
    public X_SalesForm_Hdr (Properties ctx, int SalesForm_Hdr_ID, String trxName)
    {
      super (ctx, SalesForm_Hdr_ID, trxName);
      /** if (SalesForm_Hdr_ID == 0)
        {
			setC_Project_ID (0);
			setName (null);
			setSalesForm_Hdr_ID (0);
			setValue (null);
        } */
    }

    /** Standard Constructor */
    public X_SalesForm_Hdr (Properties ctx, int SalesForm_Hdr_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, SalesForm_Hdr_ID, trxName, virtualColumns);
      /** if (SalesForm_Hdr_ID == 0)
        {
			setC_Project_ID (0);
			setName (null);
			setSalesForm_Hdr_ID (0);
			setValue (null);
        } */
    }

    /** Standard Constructor */
    public X_SalesForm_Hdr (Properties ctx, String SalesForm_Hdr_UU, String trxName)
    {
      super (ctx, SalesForm_Hdr_UU, trxName);
      /** if (SalesForm_Hdr_UU == null)
        {
			setC_Project_ID (0);
			setName (null);
			setSalesForm_Hdr_ID (0);
			setValue (null);
        } */
    }

    /** Standard Constructor */
    public X_SalesForm_Hdr (Properties ctx, String SalesForm_Hdr_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, SalesForm_Hdr_UU, trxName, virtualColumns);
      /** if (SalesForm_Hdr_UU == null)
        {
			setC_Project_ID (0);
			setName (null);
			setSalesForm_Hdr_ID (0);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_SalesForm_Hdr (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_SalesForm_Hdr[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_Project getC_Project() throws RuntimeException
	{
		return (org.compiere.model.I_C_Project)MTable.get(getCtx(), org.compiere.model.I_C_Project.Table_ID)
			.getPO(getC_Project_ID(), get_TrxName());
	}

	/** Set Project.
		@param C_Project_ID Financial Project
	*/
	public void setC_Project_ID (int C_Project_ID)
	{
		if (C_Project_ID < 1)
			set_ValueNoCheck (COLUMNNAME_C_Project_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_C_Project_ID, Integer.valueOf(C_Project_ID));
	}

	/** Get Project.
		@return Financial Project
	  */
	public int getC_Project_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Project_ID);
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

	/** Set GenerateForms.
		@param GenerateForms GenerateForms
	*/
	public void setGenerateForms (String GenerateForms)
	{
		set_Value (COLUMNNAME_GenerateForms, GenerateForms);
	}

	/** Get GenerateForms.
		@return GenerateForms	  */
	public String getGenerateForms()
	{
		return (String)get_Value(COLUMNNAME_GenerateForms);
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

	/** Set NumericLength.
		@param NumericLength NumericLength
	*/
	public void setNumericLength (int NumericLength)
	{
		set_Value (COLUMNNAME_NumericLength, Integer.valueOf(NumericLength));
	}

	/** Get NumericLength.
		@return NumericLength	  */
	public int getNumericLength()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NumericLength);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Prefix.
		@param Prefix Prefix before the sequence number
	*/
	public void setPrefix (String Prefix)
	{
		set_Value (COLUMNNAME_Prefix, Prefix);
	}

	/** Get Prefix.
		@return Prefix before the sequence number
	  */
	public String getPrefix()
	{
		return (String)get_Value(COLUMNNAME_Prefix);
	}

	/** Set Range_End.
		@param Range_End Range_End
	*/
	public void setRange_End (int Range_End)
	{
		set_Value (COLUMNNAME_Range_End, Integer.valueOf(Range_End));
	}

	/** Get Range_End.
		@return Range_End	  */
	public int getRange_End()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Range_End);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Range_Start.
		@param Range_Start Range_Start
	*/
	public void setRange_Start (int Range_Start)
	{
		set_Value (COLUMNNAME_Range_Start, Integer.valueOf(Range_Start));
	}

	/** Get Range_Start.
		@return Range_Start	  */
	public int getRange_Start()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Range_Start);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set SalesForm_Hdr.
		@param SalesForm_Hdr_ID SalesForm_Hdr
	*/
	public void setSalesForm_Hdr_ID (int SalesForm_Hdr_ID)
	{
		if (SalesForm_Hdr_ID < 1)
			set_ValueNoCheck (COLUMNNAME_SalesForm_Hdr_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_SalesForm_Hdr_ID, Integer.valueOf(SalesForm_Hdr_ID));
	}

	/** Get SalesForm_Hdr.
		@return SalesForm_Hdr	  */
	public int getSalesForm_Hdr_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SalesForm_Hdr_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set SalesForm_Hdr_UU.
		@param SalesForm_Hdr_UU SalesForm_Hdr_UU
	*/
	public void setSalesForm_Hdr_UU (String SalesForm_Hdr_UU)
	{
		set_ValueNoCheck (COLUMNNAME_SalesForm_Hdr_UU, SalesForm_Hdr_UU);
	}

	/** Get SalesForm_Hdr_UU.
		@return SalesForm_Hdr_UU	  */
	public String getSalesForm_Hdr_UU()
	{
		return (String)get_Value(COLUMNNAME_SalesForm_Hdr_UU);
	}

	/** Set Search Key.
		@param Value Search key for the record in the format required - must be unique
	*/
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue()
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}