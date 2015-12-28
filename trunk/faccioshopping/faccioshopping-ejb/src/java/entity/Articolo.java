/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity Articolo
 * @author Davide
 */
@Entity
@Table(name = "articolo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Articolo.findAll", query = "SELECT a FROM Articolo a"),
    @NamedQuery(name = "Articolo.findById", query = "SELECT a FROM Articolo a WHERE a.id = :id"),
    @NamedQuery(name = "Articolo.findByImmagineURI", query = "SELECT a FROM Articolo a WHERE a.immagineURI = :immagineURI"),
    @NamedQuery(name = "Articolo.findByNome", query = "SELECT a FROM Articolo a WHERE a.nome = :nome"),
    @NamedQuery(name = "Articolo.findByDescrizione", query = "SELECT a FROM Articolo a WHERE a.descrizione = :descrizione"),
    @NamedQuery(name = "Articolo.findByPrezzo", query = "SELECT a FROM Articolo a WHERE a.prezzo = :prezzo")})
public class Articolo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 200)
    @Column(name = "immagineURI")
    private String immagineURI;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "descrizione")
    private String descrizione;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "prezzo")
    private BigDecimal prezzo;

    
    @JoinColumn(name = "idCategoria", referencedColumnName = "id")
    @ManyToOne
    private Categoria idCategoria;

    public Articolo() {
    }

    public Articolo(Integer id) {
        this.id = id;
    }

    public Articolo(Integer id, String nome, String descrizione, BigDecimal prezzo) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImmagineURI() {
        return immagineURI;
    }

    public void setImmagineURI(String immagineURI) {
        this.immagineURI = immagineURI;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }
    
    public Categoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categoria idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Articolo)) {
            return false;
        }
        Articolo other = (Articolo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Articolo[ id=" + id + " ]";
    }
}
