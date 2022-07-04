package com.company.shoparearent.screen.client;

import io.jmix.ui.screen.*;
import com.company.shoparearent.entity.Client;

@UiController("Client.edit")
@UiDescriptor("client-edit.xml")
@EditedEntityContainer("clientDc")
public class ClientEdit extends StandardEditor<Client> {
}