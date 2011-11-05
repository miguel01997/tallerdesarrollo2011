/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import Dao.conexion;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author luis
 */
@Entity
@Table(name = "vehiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vehiculo.findAll", query = "SELECT v FROM Vehiculo v"),
    @NamedQuery(name = "Vehiculo.findByPlaca", query = "SELECT v FROM Vehiculo v WHERE v.placa = :placa"),
    @NamedQuery(name = "Vehiculo.findByPrecio", query = "SELECT v FROM Vehiculo v WHERE v.precio = :precio"),
    @NamedQuery(name = "Vehiculo.findByMotor", query = "SELECT v FROM Vehiculo v WHERE v.motor = :motor"),
    @NamedQuery(name = "Vehiculo.findByFoto", query = "SELECT v FROM Vehiculo v WHERE v.foto = :foto")})
public class Vehiculo implements Serializable {
    @OneToOne(mappedBy = "codvehiculo")
    private Anuncio anuncio;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "placa")
    private String placa;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private BigDecimal precio;
    @Column(name = "motor")
    private Integer motor;
    @Column(name = "foto")
    private BigInteger foto;
    @OneToMany(mappedBy = "codvehiculo")
    private Collection<Anuncio> anuncioCollection;
    @JoinColumn(name = "codmodelo", referencedColumnName = "codmodelo")
    @ManyToOne(optional = false)
    private Modelo codmodelo;
    
    @Column(name = "color")
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Vehiculo() {
    }

    public Vehiculo(String placa) {
        this.placa = placa;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getMotor() {
        return motor;
    }

    public void setMotor(Integer motor) {
        this.motor = motor;
    }

    public BigInteger getFoto() {
        return foto;
    }

    public void setFoto(BigInteger foto) {
        this.foto = foto;
    }

    @XmlTransient
    public Collection<Anuncio> getAnuncioCollection() {
        return anuncioCollection;
    }

    public void setAnuncioCollection(Collection<Anuncio> anuncioCollection) {
        this.anuncioCollection = anuncioCollection;
    }

    public Modelo getCodmodelo() {
        return codmodelo;
    }

    public void setCodmodelo(Modelo codmodelo) {
        this.codmodelo = codmodelo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (placa != null ? placa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehiculo)) {
            return false;
        }
        Vehiculo other = (Vehiculo) object;
        if ((this.placa == null && other.placa != null) || (this.placa != null && !this.placa.equals(other.placa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Vehiculo[ placa=" + placa + " ]";
    }

  /*  public Object mapRow(ResultSet rs, int i) throws SQLException {
        Vehiculo v = new Vehiculo();
        Modelo m = new Modelo();
        //m.buscarModelo(rs.getInt("codmodelo"));
        v.setCodmodelo(m);
        
        v.setMotor(rs.getInt("motor"));
        v.setPlaca(rs.getString("placa"));
        v.setPrecio(new BigDecimal(rs.getString("precio")));
        v.setColor(rs.getString("color"));
        return v;
    }*/
    
   
    public List buscarTodosVehiculos(){
       conexion c = new conexion();
        EntityManager em = c.getEm();
        Query q = em.createNamedQuery("Vehiculo.findAll");
        //System.out.println("*********PASA POR AQUI");
        //System.out.println(q.getResultList());
        return q.getResultList();
        
    }
    
    
    /* public Vehiculo buscarVehiculo(String placa){
       conexion c = new conexion();
       JdbcTemplate j = c.getJdbcTemplate();
       String sql = "select * from vehiculo where placa=?";
       Vehiculo m = (Vehiculo)j.queryForObject(sql,this,new Object[]{placa});
       
       this.setCodmodelo(m.getCodmodelo());System.out.println("\n\n\n\n****"+m.toString()+" n "+m.getCodmodelo());
       this.setPlaca(placa);
       this.setColor(m.getColor());
       this.setMotor(m.getMotor());
       this.setPrecio(m.getPrecio());
       return  this;
       //return new Modelo().setCodmodelo(122);
    }*/
    
    public void  buscarVehiculo(){
        conexion c = new conexion();
        EntityManager em = c.getEm();
        em.getTransaction().begin();
        Vehiculo mm = em.find(Vehiculo.class, this.placa);
        em.getTransaction().commit();
        em.close();
        if(mm!= null){
           this.anuncioCollection = mm.getAnuncioCollection();
           this.codmodelo = mm.getCodmodelo();
           this.color=mm.getColor();
           this.foto=mm.getFoto();
           this.motor=mm.getMotor();
           this.placa=mm.getPlaca();
           this.precio=mm.getPrecio();
        }
       //return new Modelo().setCodmodelo(122);
    }
   
   /* public void buscarVehiculo(){
        conexion c = new conexion();
        EntityManager em = c.getEm();
        em.getTransaction().begin();
        Vehiculo mm = em.find(Modelo.class, this.getPlaca());
        em.getTransaction().commit();
        em.close();
        if(mm!= null){
           this.anio = mm.getAnio();
           this.marca = mm.getMarca();
           this.nombre=mm.getNombre();
           this.vehiculoCollection=mm.getVehiculoCollection();
        }
    }*/
    
    
    
    
    public void actualizarVehiculo(){
       conexion c = new conexion();
       EntityManager em = c.getEm();
       em.getTransaction().begin();
       em.merge(this);
       em.getTransaction().commit();
       em.close();
    }
    
      public boolean VerificarAnuncio(){
        
       conexion c = new conexion();
       JdbcTemplate j = c.getJdbcTemplate();
       int numero=j.queryForInt("select count(*) from anuncio where codvehiculo='"+placa+"'");
       
       return (numero==0);
     
    
    }
    
    public void eliminarVehiculo(){
      conexion c = new conexion();
       EntityManager em = c.getEm();
       em.getTransaction().begin();
      Vehiculo m = em.find(Vehiculo.class, this.getPlaca());
       em.remove(m);
       em.getTransaction().commit();
       em.close();
    }
    
    public void crearVehiculo(){
       conexion c = new conexion();
       EntityManager em = c.getEm();
       em.getTransaction().begin();
       em.persist(this);
       em.getTransaction().commit();
       em.close();
    
    }
    
    
    
    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    

   
    
    
}
