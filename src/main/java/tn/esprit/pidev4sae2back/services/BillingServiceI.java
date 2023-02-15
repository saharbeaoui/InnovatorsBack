package tn.esprit.pidev4sae2back.services;

import tn.esprit.pidev4sae2back.entities.Billing;

import java.util.List;

public interface BillingServiceI {
    Billing addBilling(Billing billing);

    Billing retrieveBilling(Long idBilling);

    List<Billing> retrieveAllBillings();

    Billing updateBilling(Billing billing);

    void removebilling(Long idBilling);
}
