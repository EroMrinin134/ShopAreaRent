package com.company.shoparearent.app;

import com.company.shoparearent.entity.LeaseContract;
import com.company.shoparearent.entity.ShopArea;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Service
public class NotRentedOutService {

    @Autowired
    private DataManager dataManager;

    public List<ShopArea> loadShopAreas(LocalDate selectedDate) {
        List<ShopArea> list = dataManager.load(ShopArea.class).all().list();

        if (selectedDate == null)
            return list;

        List<LeaseContract> leaseContracts = dataManager.load(LeaseContract.class).all().list();

        for (LeaseContract lc : leaseContracts) {
            ShopArea sa = lc.getShopArea();
            if (list.contains(sa) && DateComparator.isBetween(selectedDate, lc.getDateStart(), lc.getDateEnd()))
                list.remove(sa);
        }
        return list;
    }
}