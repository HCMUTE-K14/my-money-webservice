package com.vn.hcmute.team.cortana.mymoney.data.service.debtloan;

import com.vn.hcmute.team.cortana.mymoney.bean.DebtLoan;
import java.util.List;

public interface DebtLoanService {
    
    List<DebtLoan> getDebtLoanByType(String wallet_id, String type);
    
    List<DebtLoan> getDebtLoan(String wallet_id);
    
    void addDebtLoan(DebtLoan debtLoan);
    
    void updateDebtLoan(DebtLoan debtLoan, String wallet_id);
    
    void deleteDebtLoan(DebtLoan debtLoan);
    
}
