/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Davide
 */
@Entity
@Table(name = "regalo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Regalo.findAll", query = "SELECT r FROM Regalo r"),
    @NamedQuery(name = "Regalo.findById", query = "SELECT r FROM Regalo r WHERE r.id = :id"),
    @NamedQuery(name = "Regalo.findByIdAmico", query = "SELECT r FROM Regalo r WHERE r.idAmico = :idAmico"),
    @NamedQuery(name = "Regalo.findByNome", query = "SELECT r FROM Regalo r WHERE r.nome = :nome"),
    @NamedQuery(name = "Regalo.findByUrlArticolo", query = "SELECT r FROM Regalo r WHERE r.urlArticolo = :urlArticolo"),
    @NamedQuery(name = "Regalo.findByDescrizione", query = "SELECT r FROM Regalo r WHERE r.descrizione = :descrizione")})
public class Regalo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idAmico")
    private int idAmico;
    @Size(max = 50)
    @Column(name = "nome")
    private String nome;
    @Size(max = 200)
    @Column(name = "urlArticolo")
    private String urlArticolo;
    @Size(max = 100)
    @Column(name = "descrizione")
    private String descrizione;

    public Regalo() {
    }

    public Regalo(Integer id) {
        this.id = id;
    }

    public Regalo(Integer id, int idAmico) {
        this.id = id;
        this.idAmico = idAmico;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdAmico() {
        return idAmico;
    }

    public void setIdAmico(int idAmico) {
        this.idAmico = idAmico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrlArticolo() {
        return urlArticolo;
    }

    public void setUrlArticolo(String urlArticolo) {
        this.urlArticolo = urlArticolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
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
        if (!(object instanceof Regalo)) {
            return false;
        }
        Regalo other = (Regalo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Regalo[ id=" + id + " ]";
    }
    
}
