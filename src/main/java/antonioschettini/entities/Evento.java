package antonioschettini.entities;

import antonioschettini.enums.TipoEvento;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
// instanzio l'entity in modo tale che all'avvio del programma hibernate veda questa istanza e ne crei la tabella
@Table(name = "eventi") // nome della tabella
// Aggiungo l'inheritance
@Inheritance(strategy = InheritanceType.JOINED) // effettuo una singletable
//Personalizzo il nome della colonna che ospiterà partita concerto o gara per il tipo
//@DiscriminatorColumn(name = "tipo_struttura_evento", discriminatorType = DiscriminatorType.STRING)
@NamedQuery(
        name = "Evento.soldOut",
        query = "SELECT e FROM Evento e WHERE SIZE(e.partecipazioni) >= e.numeroMassimoPartecipanti"
)
public abstract class Evento {
    //Attributi
    @Id // obbligatorio per dichiarare la chiave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // per generare automanticamente l'id
    @Column(name = "evento_id")
    private long id;

    @Column(name = "titolo", nullable = false, length = 30)
    private String titolo;

    @Column(name = "data_evento", nullable = false)
    private LocalDate dataEvento;

    @Column(name = "descrizione", nullable = false, length = 50)
    private String descrizione;

    @Column(name = "tipo_evento", nullable = false)
    @Enumerated(EnumType.STRING)  // per settare l'enums in postgre ed assegnarlo a valore stringa
    private TipoEvento tipoEvento;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.REMOVE)
    private List<Partecipazione> partecipazioni;

    @Column(name = "numero_max_partecipanti", nullable = false)
    private int numeroMassimoPartecipanti;

    //Aggiungo il manutoOne molti eventi possono svolgersi nella stessa location
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    //Costruttori
    public Evento() {
    } // Il costruttore vuoto obbligatorio entities; per permettere ad Hibernate di funzionare

    //Costrutture che utilizzero per instanziare l'oggetto
    public Evento(String titolo, LocalDate dataEvento, String descrizione, TipoEvento tipoEvento, int numeroMassimoPartecipanti, Location location) {
        this.titolo = titolo;
        this.dataEvento = dataEvento;
        this.descrizione = descrizione;
        this.tipoEvento = tipoEvento;
        this.numeroMassimoPartecipanti = numeroMassimoPartecipanti;
        this.location = location;
    }

    //Implemento getter setter e override del toostring

    public long getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public int getNumeroMassimoPartecipanti() {
        return numeroMassimoPartecipanti;
    }

    public void setNumeroMassimoPartecipanti(int numeroMassimoPartecipanti) {
        this.numeroMassimoPartecipanti = numeroMassimoPartecipanti;
    }

    public List<Partecipazione> getPartecipazioni() {
        return partecipazioni;
    }

    public void setPartecipazioni(List<Partecipazione> partecipazioni) {
        this.partecipazioni = partecipazioni;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", titolo='" + titolo + '\'' +
                ", dataEvento=" + dataEvento +
                ", descrizione='" + descrizione + '\'' +
                ", tipoEvento=" + tipoEvento +
                ", numeroMassimoPartecipanti=" + numeroMassimoPartecipanti +
                ", location=" + (location != null ? location.getNome() : "Nessuna") + //ternario per la location se c'è prendo il nome se non c'è mostro nessuna
                '}';
    }
}
