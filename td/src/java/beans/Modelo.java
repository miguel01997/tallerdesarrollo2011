/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import Dao.conexion;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.postgresql.util.PSQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author luis
 */
@Entity
@Table(name = "modelo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modelo.findAll", query = "SELECT m FROM Modelo m"),
    @NamedQuery(name = "Modelo.findByCodmodelo", query = "SELECT m FROM Modelo m WHERE m.codmodelo = :codmodelo"),
    @NamedQuery(name = "Modelo.findByNombre", query = "SELECT m FROM Modelo m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Modelo.findByMarca", query = "SELECT m FROM Modelo m WHERE m.marca = :marca"),
    @NamedQuery(name = "Modelo.findByAnio", query = "SELECT m FROM Modelo m WHERE m.anio = :anio")})
    
    public class Modelo implements Serializable, RowMapper,Validator {
    //private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator="modelo_codmodelo_seq",strategy=GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "codmodelo")
    private Integer codmodelo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "marca")
    private String marca;
    @Column(name = "anio")
    private Integer anio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codmodelo")
    private Collection<Vehiculo> tiene;
    
    @Transient
    private String membresia;

    public String getMembresia() {
        return membresia;
    }

    public void setMembresia(String membresia) {
        this.membresia = membresia;
    }
    

    public Modelo() {
    }

    public Modelo(Integer codmodelo) {
        this.codmodelo = codmodelo;
    }

    public Integer getCodmodelo() {
        return codmodelo;
    }

    public void setCodmodelo(Integer codmodelo) {
        this.codmodelo = codmodelo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }
    
 

    @XmlTransient
    public Collection<Vehiculo> gettiene() {
        return tiene;
    }

    public void settiene(Collection<Vehiculo> tiene) {
        this.tiene = tiene;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codmodelo != null ? codmodelo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Modelo)) {
            return false;
        }
        Modelo other = (Modelo) object;
        if ((this.codmodelo == null && other.codmodelo != null) || (this.codmodelo != null && !this.codmodelo.equals(other.codmodelo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Modelo[ codmodelo=" + codmodelo + " ]";
    }
    
   
    
    
    
    
    
    //*********************METODOS AGREGADOS**************************//

    /* 
   public List buscarTodosModelos(){
        conexion c = new conexion();
        JdbcTemplate select = c.getJdbcTemplate();
      return select.query("select * from modelo",new  Modelo());
    }*/
    
   public Object mapRow(ResultSet rs, int i) throws SQLException {
     Modelo m = new Modelo();
     
     
     ArrayList<String> att = new ArrayList<String>();
     ResultSetMetaData metaData = rs.getMetaData();
     for(int o=1;o<metaData.getColumnCount()+1;o++){
         att.add(metaData.getColumnName(o));
     }
     
     if(att.contains("codmodelo"))
         m.setCodmodelo(rs.getInt("codmodelo"));
     
     if(att.contains("nombre"))
         m.setNombre(rs.getString("nombre"));
     if(att.contains("marca"))
         m.setMarca(rs.getString("marca"));
     if(att.contains("anio"))
         m.setAnio(rs.getInt("anio"));
     
        int col = -1; 
       try{
           if((col =rs.findColumn("Gr.Memb.")) >1){
               m.setMembresia(rs.getString(col));
           }
       }catch(PSQLException e){
           m.setMembresia("");
       }
     //Busca coleccion de vehiculos
       //Vehiculo v = new Vehiculo();
       //List l = v.ejecutarQuery("select * from vehiculo where codmodelo='"+m.getCodmodelo()+"'");
       //m.settiene(l);
     return m;
   }
   
   
   
   public List ejecutarQuery(String query){
     conexion c = new conexion();
     JdbcTemplate j = c.getJdbcTemplate();
     List l = j.query(query,this);
     return l;    
   }
    
    
    
  public List buscarTodosModelos(){
        conexion c = new conexion();
        EntityManager em = c.getEm();
        //System.out.println("*******************Paso por aqui");
        //Inicia transaccion
        Query q = em.createNamedQuery("Modelo.findAll");
        return q.getResultList();
        
    }
    
  
  
    
    
    
    /*public Modelo buscarModelo(int id){
       conexion c = new conexion();
       JdbcTemplate j = c.getJdbcTemplate();
       String sql = "select * from modelo where codmodelo="+id;
       Modelo m = (Modelo)j.queryForObject(sql,this);
       this.setAnio(m.getAnio());
       this.setCodmodelo(m.getCodmodelo());
       this.setMarca(m.getMarca());
       this.setNombre(m.getNombre());
       return  this;
       //return new Modelo().setCodmodelo(122);
    }*/
  
     
     public void buscarModelo(){
        conexion c = new conexion();
        EntityManager em = c.getEm();
        em.getTransaction().begin();
        Modelo mm = em.find(Modelo.class, this.getCodmodelo());
        em.getTransaction().commit();
        em.close();
        if(mm!= null){
           this.anio = mm.getAnio();
           this.marca = mm.getMarca();
           this.nombre=mm.getNombre();
           this.tiene=mm.gettiene();
        }
     }
  
    
    public void actualizarModelo(){
       conexion c = new conexion();
       EntityManager em = c.getEm();
       em.getTransaction().begin();
       em.merge(this);
       em.getTransaction().commit();
       em.close();
       
    }
    
   /* public void eliminarModelo(){
       conexion c = new conexion();
       JdbcTemplate j = c.getJdbcTemplate();
       j.update("delete from modelo where codmodelo=? ",new Object[]{codmodelo});
    }*/
    
    public void eliminarModelo(){
       conexion c = new conexion();
       EntityManager em = c.getEm();
       em.getTransaction().begin();
       Modelo m = em.find(Modelo.class, this.getCodmodelo());
       em.remove(m);
       em.getTransaction().commit();
       em.close();
    }
    
    
    
    /*public void crearModelo(){
       conexion c = new conexion();
       JdbcTemplate j = c.getJdbcTemplate();
       j.update("insert into modelo (nombre,marca,anio) values "+
                "(?,?,?)",new Object[]{nombre,marca,anio}
                );
    
    }*/
    
    public void crearModelo(){
       conexion c = new conexion();
       EntityManager em = c.getEm();
       em.getTransaction().begin();
       em.persist(this);
       em.getTransaction().commit();
       em.close();
    
    }
    
    
    
    
    /**PARA VALIDAR*/
    public boolean supports(Class type) {
        return type.equals(Modelo.class);
    }

    public void validate(Object o, Errors errors) {
        Modelo m = (Modelo) o;
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "error.nombre","Error en el nombre");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "error.nombre","Nombre es requerido");
    }
    
    
    public boolean VerificarVehiculo(){
        
       conexion c = new conexion();
       JdbcTemplate j = c.getJdbcTemplate();
       int numero=j.queryForInt("select count(*) from vehiculo where codmodelo="+codmodelo);
       return (numero==0);
    }

   /* public Object mapRow(ResultSet rs, int i) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }*/

    
    
    
    
    
    
}