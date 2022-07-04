package com.company.shoparearent.screen.shoparea;

import io.jmix.ui.screen.*;
import com.company.shoparearent.entity.ShopArea;

@UiController("ShopArea.rentedOut")
@UiDescriptor("shop-area-rented-out.xml")
@LookupComponent("shopAreasTable")
public class ShopAreaRentedOut extends StandardLookup<ShopArea> {
}