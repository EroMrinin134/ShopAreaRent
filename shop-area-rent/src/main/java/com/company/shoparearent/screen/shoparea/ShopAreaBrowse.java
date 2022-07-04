package com.company.shoparearent.screen.shoparea;

import io.jmix.ui.screen.*;
import com.company.shoparearent.entity.ShopArea;

@UiController("ShopArea.browse")
@UiDescriptor("shop-area-browse.xml")
@LookupComponent("shopAreasTable")
public class ShopAreaBrowse extends StandardLookup<ShopArea> {
}