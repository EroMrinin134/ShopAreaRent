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

    public boolean IsAlreadyRentedOut(LeaseContract newLeaseContract) {
        List<LeaseContract> leaseContracts = dataManager.load(LeaseContract.class).all().list();
        ShopArea shopArea = newLeaseContract.getShopArea();
        LocalDate dateStart = newLeaseContract.getDateStart(), dateEnd = newLeaseContract.getDateEnd();

        for (LeaseContract leaseContract : leaseContracts) {
            if(!leaseContract.getShopArea().equals(shopArea) ||
                    newLeaseContract.getId().equals(leaseContract.getId())) // We need to skip old version of entity.
                continue;

            if (DateComparator.isBetween(dateStart, dateEnd,
                    leaseContract.getDateStart(), leaseContract.getDateEnd())) {
                return true;
            }
        }
        return false;
    }
}