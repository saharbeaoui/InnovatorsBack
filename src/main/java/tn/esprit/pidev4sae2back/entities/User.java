package tn.esprit.pidev4sae2back.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser", nullable = false)
    private Long idUser;


    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_name")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "cin_user")
    private Long cinUser;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_num")
    private Long phoneNum;

    @Column(name = "nationality")
    private String nationality;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    private String resetPasswordToken;

    private boolean state;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private Sex sex;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_user")
    private TypeUser typeUser;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FMembership> fMemberships = new LinkedHashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<RMembership> rMembership = new LinkedHashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Thread> threads = new LinkedHashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Claim> claims = new LinkedHashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Claim> claimResponse = new LinkedHashSet<>();


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToOne(mappedBy = "user",cascade = CascadeType.REMOVE)
    private FidelityCard fidelityCard;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private WaitingList waitingList;

    public User() {
    }

    public User(String username, String email, String password, boolean state) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.state = state;
    }

    public User(String username, String email, String password, String firstName,String lastName,Long phone) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNum = phone;

    }

    public User(String username,String firstName,String lastName ,String email,Long phone, String password) {
        this.username = username;
        this.firstName=firstName;
        this.lastName=lastName;
        this.email = email;
        this.phoneNum=phone;
        this.password = password;
    }

    public User(String username, String email, String password, boolean state,String firstName,String lastName, Long phone) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.state = state;
        this.firstName=firstName;
        this.lastName=lastName;
        this.phoneNum=phone;

    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public boolean getState() {
        return state;
    }










