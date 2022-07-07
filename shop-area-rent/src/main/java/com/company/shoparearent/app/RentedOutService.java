package com.company.shoparearent.app;

import com.company.shoparearent.entity.Client;
import com.company.shoparearent.entity.LeaseContract;
import com.company.shoparearent.entity.ShopArea;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;

@Service
public class RentedOutService {

    public static final int DAYS_IN_MONTH = 28;

    @Autowired
    private DataManager dataManager;

    private Double totalRent = null;

    public Double getTotalRent() {
        return totalRent;
    }

    public List<ShopArea> loadShopAreas(Client selectedClient, LocalDate selectedDate) {
        List<ShopArea> list = new LinkedList<>();
        totalRent = 0.0;

        if (selectedClient == null || selectedDate == null)
            return list;

        int count = 0;
        List<LeaseContract> leaseContracts = dataManager.load(LeaseContract.class).all().list();

        for (LeaseContract lc : leaseContracts) {
            ShopArea sa = lc.getShopArea();
            if (lc.getClient().equals(selectedClient) && DateComparator.isBetween(selectedDate, lc.getDateStart(), lc.getDateEnd())) {
                list.add(sa);
                totalRent += sa.getRent() * (ChronoUnit.DAYS.between(lc.getDateStart(), lc.getDateEnd().plusDays(1)));
                ++count;
            }
        }

        if (count > 0)
            totalRent /= count * DAYS_IN_MONTH;

        return list;
    }
}