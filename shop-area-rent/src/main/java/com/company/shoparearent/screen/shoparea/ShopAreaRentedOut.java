package com.company.shoparearent.screen.shoparea;

import com.company.shoparearent.app.DateConverter;
import com.company.shoparearent.app.NotRentedOutService;
import com.company.shoparearent.app.RentedOutService;
import com.company.shoparearent.entity.Client;
import io.jmix.core.LoadContext;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.component.TextField;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import com.company.shoparearent.entity.ShopArea;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@UiController("ShopArea.rentedOut")
@UiDescriptor("shop-area-rented-out.xml")
@LookupComponent("shopAreasTable")
public class ShopAreaRentedOut extends StandardLookup<ShopArea> {

    @Autowired
    private RentedOutService rentedOutService;

    @Named("shopAreasDl")
    private CollectionLoader<ShopArea> shopAreasDl;

    @Named("totalRentField")
    private TextField<Double> totalRentField;

    private Client selectedClient= null;

    private LocalDate selectedDate = null;

    @Install(to = "shopAreasDl", target = Target.DATA_LOADER)
    private List<ShopArea> shopAreasDlLoadDelegate(LoadContext<ShopArea> loadContext) {
        return rentedOutService.loadShopAreas(selectedClient, selectedDate);
    }

    @Subscribe("selectedClientField")
    public void onSelectedClientFieldValueChange(HasValue.ValueChangeEvent<Client> event) {
        selectedClient = event.getValue();
        shopAreasDl.load();
        totalRentField.setValue(rentedOutService.getTotalRent());
    }

    @Subscribe("selectedDateField")
    public void onSelectedDateFieldValueChange(HasValue.ValueChangeEvent<Date> event) {
        selectedDate = DateConverter.convertToLocalDate(event.getValue());
        shopAreasDl.load();
        totalRentField.setValue(rentedOutService.getTotalRent());
    }
}