package tn.esprit.pidev4sae2back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.Billing;
import tn.esprit.pidev4sae2back.repositories.BillingRepository;

import java.util.List;

@Service
public class BillingServiceImp implements BillingServiceI{
    @Autowired
    BillingRepository br;

    @Override
    public Billing addBilling(Billing billing){
        return br.save(billing);
    }

    @Override
    public Billing retrieveBilling(Long idBilling) {
        return br.findById(idBilling).orElse(null);
    }
    @Override
    public List<Billing> retrieveAllBillings() {
        return br.findAll();
    }

    @Override
    public Billing updateBilling(Billing billing) {
        return br.save(billing);
    }

    @Override
    public void removebilling(Long idBilling) {
        br.deleteById(idBilling);
    }
}
