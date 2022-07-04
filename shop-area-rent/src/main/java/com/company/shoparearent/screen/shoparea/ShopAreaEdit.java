package com.company.shoparearent.screen.shoparea;

import io.jmix.ui.screen.*;
import com.company.shoparearent.entity.ShopArea;

@UiController("ShopArea.edit")
@UiDescriptor("shop-area-edit.xml")
@EditedEntityContainer("shopAreaDc")
public class ShopAreaEdit extends StandardEditor<ShopArea> {
}