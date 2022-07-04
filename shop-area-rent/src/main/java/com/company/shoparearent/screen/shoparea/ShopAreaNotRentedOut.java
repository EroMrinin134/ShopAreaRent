package com.company.shoparearent.screen.shoparea;

import io.jmix.ui.screen.*;
import com.company.shoparearent.entity.ShopArea;

@UiController("ShopArea.notRentedOut")
@UiDescriptor("shop-area-not-rented-out.xml")
@LookupComponent("shopAreasTable")
public class ShopAreaNotRentedOut extends StandardLookup<ShopArea> {
}