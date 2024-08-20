package org.infinity.models;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MUser;
import org.compiere.util.EMail;

public class MBuyer extends X_C_Buyer {

    public MBuyer(Properties ctx, int C_Buyer_ID, String trxName, String[] virtualColumns) {
        super(ctx, C_Buyer_ID, trxName, virtualColumns);
    }

    public MBuyer(Properties ctx, int C_Buyer_ID, String trxName) {
        super(ctx, C_Buyer_ID, trxName);
    }

    public MBuyer(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    public MBuyer(Properties ctx, String C_Buyer_UU, String trxName, String... virtualColumns) {
        super(ctx, C_Buyer_UU, trxName, virtualColumns);
    }

    public MBuyer(Properties ctx, String C_Buyer_UU, String trxName) {
        super(ctx, C_Buyer_UU, trxName);
    }

    @Override
    protected boolean beforeSave(boolean newRecord) {
    	
    	
    	if(newRecord)
    		setAD_User_ID(0);
    	
        String email = getEMail();
        String nationalCode = getNationalCode();
        
        if (email == null || email.trim().isEmpty() || !EMail.validate(email)) {
            throw new AdempiereException("Email is required in proper format!");
        }
        if (nationalCode == null || nationalCode.trim().isEmpty()) {
            throw new AdempiereException("National Code is required");
        }

       
        MUser user = null;
        if(getAD_User_ID()>0)
        	user = new MUser(this.getCtx(), getAD_User_ID(), this.get_TrxName());
        else 
        	user = new MUser(getCtx(), 0, get_TrxName());

            user.setDescription(getName());
            user.setEMail(email);
            user.setPassword(nationalCode);
            user.setName(getName());
            user.setBirthday(getBirthday());
            user.setPhone(getPhone());
            user.setPhone2(getPhone2());
            user.setAD_Image_ID(getAD_Image_ID());
            user.saveEx();
            setAD_User_ID(user.getAD_User_ID());
           
        

        return super.beforeSave(newRecord);
    }
    
    
}
