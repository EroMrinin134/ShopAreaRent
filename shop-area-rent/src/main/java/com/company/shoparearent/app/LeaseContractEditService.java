package com.company.shoparearent.app;

import com.company.shoparearent.entity.LeaseContract;
import com.company.shoparearent.entity.ShopArea;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LeaseContractEditService {

    @Autowired
    private DataManager dataManager;

    public boolean IsCorrectDates(LocalDate dateStart, LocalDate dateEnd) {
        return !dateStart.isAfter(dateEnd);
    }

    public boolean IsAlreadyRentedOut(ShopArea shopArea, LocalDate dateStart, LocalDate dateEnd) {
        List<LeaseContract> leaseContracts = dataManager.load(LeaseContract.class)
                .all()
                .list();
        for (LeaseContract leaseContract : leaseContracts) {
            if(!leaseContract.getShopArea().getNumber().equals(shopArea.getNumber()))
                continue;

            if ((!leaseContract.getDateStart().isBefore(dateStart) &&
                !leaseContract.getDateStart().isAfter(dateEnd)) ||
                (!leaseContract.getDateEnd().isBefore(dateStart) &&
                !leaseContract.getDateEnd().isAfter(dateEnd))
                )
                return true;
        }
        return false;
    }
}