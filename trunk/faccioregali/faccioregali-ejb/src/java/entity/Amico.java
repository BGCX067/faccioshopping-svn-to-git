/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Davide
 */
@Entity
@Table(name = "amico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Amico.findAll", query = "SELECT a FROM Amico a"),
    @NamedQuery(name = "Amico.findById", query = "SELECT a FROM Amico a WHERE a.id = :id"),
    @NamedQuery(name = "Amico.findByEmail", query = "SELECT a FROM Amico a WHERE a.email = :email"),
    @NamedQuery(name = "Amico.findByNome", query = "SELECT a FROM Amico a WHERE a.nome = :nome"),
    @NamedQuery(name = "Amico.findByCognome", query = "SELECT a FROM Amico a WHERE a.cognome = :cognome"),
    @NamedQuery(name = "Amico.findByPassword", query = "SELECT a FROM Amico a WHERE a.password = :password"),
    @NamedQuery(name = "Amico.findByDataRegistrazione", query = "SELECT a FROM Amico a WHERE a.dataRegistrazione = :dataRegistrazione")})
public class Amico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "cognome")
    private String cognome;
    @Size(max = 100)
    @Column(name = "password")
    private String password;
    @Column(name = "dataRegistrazione")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRegistrazione;

    public Amico() {
    }

    public Amico(Integer id) {
        this.id = id;
    }

    public Amico(Integer id, String email, String nome, String cognome) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDataRegistrazione() {
        return dataRegistrazione;
    }

    public void setDataRegistrazione(Date dataRegistrazione) {
        this.dataRegistrazione = dataRegistrazione;
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
        if (!(object instanceof Amico)) {
            return false;
        }
        Amico other = (Amico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Amico[ id=" + id + " ]";
    }
    
}
