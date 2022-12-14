package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity //genera una tabla con el nombre de la clase.
public class Client {

    @Id //anotacion que señala a la primary key
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native") //anotacion que asigna un id a medida que se guarda el objeto
    @GenericGenerator(name="native", strategy = "native")  //anotacion que indique que aumenta de 1 en uno el id, se encarga que no se repita
    private long id; //long es un dato numerico primitivo
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Account> accounts = new HashSet<>();/* Set guarda una coleccion de account, y se asocia a la propiedad client de account*/
                                /*Creo un lugar en memoria*/

    @OneToMany(mappedBy = "client",fetch = FetchType.EAGER)
    private Set<ClientLoan> clientLoans = new HashSet<>();



    @OneToMany(mappedBy = "client",fetch = FetchType.EAGER)
    private Set<Card> cards = new HashSet<>();






    public Client() { //sirve para cuando cargue la aplicacion hace una prueba con esté constructor
    }
    public Client(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password= password;
    }

    //Metodos accesores
    public long getId() {
        return id;
    }
    public String getFirstName() { //accede a los valores del constructor
        return firstName;
    }
    public void setFirstName(String firstName) { //Podemos cambiar datos del una propiedad
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }
    public Set<ClientLoan> getClientLoans() {
        return clientLoans;
    }
    public void setClientLoans(Set<ClientLoan> clientLoans) {
        this.clientLoans = clientLoans;
    }

    public Set<Card> getCards() {
        return cards;
    }
    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {  //Conviete mi objeto en un texto plano
        return "Cliente{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    public void addAcconts(Account account){
        account.setClient(this);
        accounts.add(account);
    }

}
