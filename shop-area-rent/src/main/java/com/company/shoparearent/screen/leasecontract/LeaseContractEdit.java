package com.company.shoparearent.screen.leasecontract;

import com.company.shoparearent.app.LeaseContractEditService;
import io.jmix.ui.Notifications;
import io.jmix.ui.screen.*;
import com.company.shoparearent.entity.LeaseContract;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("LeaseContract.edit")
@UiDescriptor("lease-contract-edit.xml")
@EditedEntityContainer("leaseContractDc")
public class LeaseContractEdit extends StandardEditor<LeaseContract> {

    @Autowired
    private MessageBundle messageBundle;

    @Autowired
    private Notifications notifications;

    @Autowired
    private LeaseContractEditService leaseContractEditService;

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        LeaseContract lc = getEditedEntity();

        if(!leaseContractEditService.IsCorrectDates(lc.getDateStart(), lc.getDateEnd())) {
            event.preventCommit();
            notifications.create().withCaption(messageBundle.getMessage("leaseContractEdit.dateExceptionMessage")).show();
        }

        if(leaseContractEditService.IsAlreadyRentedOut(lc)) {
            event.preventCommit();
            notifications.create().withCaption(messageBundle.getMessage("leaseContractEdit.rentedOutExceptionMessage")).show();
        }
    }
}