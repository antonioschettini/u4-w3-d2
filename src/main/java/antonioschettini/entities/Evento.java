package antonioschettini.entities;

import antonioschettini.enums.TipoEvento;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
// instanzio l'entity in modo tale che all'avvio del programma hibernate veda questa istanza e ne crei la tabella
@Table(name = "eventi") // nome della tabella
public class Evento {
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

    @Column(name = "numero_max_partecipanti", nullable = false)
    private int numeroMassimoPartecipanti;

    //Costruttori
    public Evento() {
    } // Il costruttore vuoto obbligatorio entities; per permettere ad Hibernate di funzionare

    //Costrutture che utilizzero per instanziare l'oggetto
    public Evento(String titolo, LocalDate dataEvento, String descrizione, TipoEvento tipoEvento, int numeroMassimoPartecipanti) {
        this.titolo = titolo;
        this.dataEvento = dataEvento;
        this.descrizione = descrizione;
        this.tipoEvento = tipoEvento;
        this.numeroMassimoPartecipanti = numeroMassimoPartecipanti;
    }

    //Implemento getter setter e override del toostring

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

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", titolo='" + titolo + '\'' +
                ", dataEvento=" + dataEvento +
                ", descrizione='" + descrizione + '\'' +
                ", tipoEvento=" + tipoEvento +
                ", numeroMassimoPartecipanti=" + numeroMassimoPartecipanti +
                '}';
    }
}
