package com.company.shoparearent.screen.shoparea;

import com.company.shoparearent.app.DateConverter;
import com.company.shoparearent.app.NotRentedOutService;
import io.jmix.core.LoadContext;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import com.company.shoparearent.entity.ShopArea;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@UiController("ShopArea.notRentedOut")
@UiDescriptor("shop-area-not-rented-out.xml")
@LookupComponent("shopAreasTable")
public class ShopAreaNotRentedOut extends StandardLookup<ShopArea> {

    @Autowired
    private NotRentedOutService notRentedOutService;

    @Named("shopAreasDl")
    private CollectionLoader<ShopArea> shopAreasDl;

    private LocalDate selectedDate = null;

    @Install(to = "shopAreasDl", target = Target.DATA_LOADER)
    private List<ShopArea> shopAreasDlLoadDelegate(LoadContext<ShopArea> loadContext) {
        return notRentedOutService.loadShopAreas(selectedDate);
    }

    @Subscribe("selectedDateField")
    public void onSelectedDateFieldValueChange(HasValue.ValueChangeEvent<Date> event) {
        selectedDate = DateConverter.convertToLocalDate(event.getValue());
        shopAreasDl.load();
    }

}