package tn.esprit.pidev4sae2back.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

@Table(name = "billing")
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBilling", nullable = false)
    private Long idBilling;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_membership")
    private TypeMembership typeMembership;

    @Enumerated(EnumType.STRING)
    @Column(name = "duration")
    private Duration duration;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_user")
    private TypeUser typeUser;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private Sex sex;

    @Enumerated(EnumType.STRING)
    @Column(name = "service_billng")
    private ServiceBillng serviceBillng;

    @Column(name = "total_cost", nullable = false)
    private float totalCost;

}