package com.company.shoparearent.screen.leasecontract;

import io.jmix.ui.screen.*;
import com.company.shoparearent.entity.LeaseContract;

@UiController("LeaseContract.browse")
@UiDescriptor("lease-contract-browse.xml")
@LookupComponent("leaseContractsTable")
public class LeaseContractBrowse extends StandardLookup<LeaseContract> {
}