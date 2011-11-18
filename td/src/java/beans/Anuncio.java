/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import Dao.conexion;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import org.postgresql.util.PSQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author luis
 */
@Entity
@Table(name = "anuncio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Anuncio.findAll", query = "SELECT a FROM Anuncio a"),
    @NamedQuery(name = "Anuncio.findByCodanuncio", query = "SELECT a FROM Anuncio a WHERE a.codanuncio = :codanuncio"),
    @NamedQuery(name = "Anuncio.findByFecha", query = "SELECT a FROM Anuncio a WHERE a.fecha = :fecha"),
    @NamedQuery(name = "Anuncio.findByDescripcion", query = "SELECT a FROM Anuncio a WHERE a.descripcion = :descripcion")})
public class Anuncio implements Serializable, RowMapper {
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    //private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator="anuncio_codanuncio_seq",strategy=GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "codanuncio")
    private Integer codanuncio;
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "codvehiculo", referencedColumnName = "placa")
    @ManyToOne
    private Vehiculo codvehiculo;
    @JoinColumn(name = "codusuario", referencedColumnName = "codusuario")
    @ManyToOne
    private Usuario codusuario;
    
    @Transient
    private String membresia;

    public String getMembresia() {
        return membresia;
    }

    public void setMembresia(String membresia) {
        this.membresia = membresia;
    }

    public Anuncio() {
    }

    public Anuncio(Integer codanuncio) {
        this.codanuncio = codanuncio;
    }

    public Integer getCodanuncio() {
        return codanuncio;
    }

    public void setCodanuncio(Integer codanuncio) {
        this.codanuncio = codanuncio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Vehiculo getCodvehiculo() {
        return codvehiculo;
    }

    public void setCodvehiculo(Vehiculo codvehiculo) {
        this.codvehiculo = codvehiculo;
    }

    public Usuario getCodusuario() {
        return codusuario;
    }

    public void setCodusuario(Usuario codusuario) {
        this.codusuario = codusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codanuncio != null ? codanuncio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Anuncio)) {
            return false;
        }
        Anuncio other = (Anuncio) object;
        if ((this.codanuncio == null && other.codanuncio != null) || (this.codanuncio != null && !this.codanuncio.equals(other.codanuncio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Anuncio[ codanuncio=" + codanuncio + " ]";
    }

     public Object mapRow(ResultSet rs, int i) throws SQLException {
        Anuncio u  = new Anuncio();
        
        u.setCodanuncio(rs.getInt("codanuncio"));
        u.buscarAnuncio();
        
        int col = -1; 
       try{
           if((col =rs.findColumn("Gr.Memb.")) >1){
               u.setMembresia(rs.getString(col));
           }
       }catch(PSQLException e){
           u.setMembresia("");
          // System.out.println("Consulta no difusa");
       }
        /*
        //u.codusuario
        //u.setCodvehiculo(codvehiculo);
       Usuario user = new Usuario();
        //user.buscarUsuario(1);
         user.setCodusuario(rs.getInt("codusuario"));
         user.buscarUsuario();
        
       // System.out.println("\n\n\n"+rs.getInt("codusuario"));
        u.setCodusuario(user);
        
       Vehiculo veh = new Vehiculo();
       veh.setPlaca(rs.getString("codvehiculo"));
       veh.buscarVehiculo();
       u.setCodvehiculo(veh);
        
        u.setDescripcion(rs.getString("descripcion"));
        u.setFecha(rs.getDate("fecha"));*/
        
        //u.set
        
        return u;
    }

     
     /*  public void crearAnuncio(){
       conexion c = new conexion();
       JdbcTemplate j = c.getJdbcTemplate();
       SimpleDateFormat sd=new SimpleDateFormat("dd-MM-yyyy");
       String fechaStr=sd.format(fecha);
       j.execute("insert into anuncio (fecha,descripcion,codusuario,codvehiculo) values('"+fechaStr+"','"+descripcion+"',"+codusuario.getCodusuario()+",'"+codvehiculo.getPlaca()+"')");
    
    }

    public Anuncio buscarAnuncio(int id) {
       conexion c = new conexion();
       JdbcTemplate j = c.getJdbcTemplate();
       String sql = "select * from anuncio where codanuncio="+id;
       
       Anuncio m = (Anuncio)j.queryForObject(sql,this);
       this.setFecha(m.getFecha());
       this.setDescripcion(m.getDescripcion());
       this.setCodusuario(m.getCodusuario());
       this.setCodvehiculo(m.getCodvehiculo());
       this.setCodanuncio(m.getCodanuncio());
       return  this;
    }

    public void modificarAnuncio() {
       conexion c = new conexion();
       JdbcTemplate j = c.getJdbcTemplate();
       // j.execute("insert into modelo values('bla','bla',1924)");
        j.update("update anuncio set fecha = ?,descripcion=?,codusuario=?,codvehiculo=? where codanuncio=? ",
            new Object[]{fecha,descripcion,codusuario.getCodusuario(),codvehiculo.getPlaca(),codanuncio});

    }
    
       public void eliminarAnuncio(){
       conexion c = new conexion();
       JdbcTemplate j = c.getJdbcTemplate();
       j.update("delete from anuncio where codanuncio=? ",new Object[]{codanuncio});
    }*/
     
      public List buscarTodosAnuncios(){
       conexion c = new conexion();
        EntityManager em = c.getEm();
        Query q = em.createNamedQuery("Anuncio.findAll");
        //System.out.println("*********PASA POR AQUI");
        //System.out.println(q.getResultList());
        //SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
        List list = q.getResultList();
        
        /*
        for (Object anun: list) {
            Anuncio an = (Anuncio) anun;
            an.fechaS = sd.format(an.fecha);
        }
         * 
         */
        return list;
    }
     
     

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
    
    public void buscarAnuncio(){
        conexion c = new conexion();
        EntityManager em = c.getEm();
        em.getTransaction().begin();
        Anuncio mm = em.find(Anuncio.class, this.codanuncio);
        em.getTransaction().commit();
        em.close();
        if(mm!= null){
           this.codusuario = mm.getCodusuario();
           this.codvehiculo = mm.getCodvehiculo();
           this.descripcion = mm.getDescripcion();
           this.fecha = mm.getFecha();
        }
     }
    
    
    public boolean actualizarAnuncio(){
       conexion c = new conexion();
       EntityManager em = c.getEm();
       em.getTransaction().begin();
       
       Anuncio a = em.find(Anuncio.class, this.codanuncio);
       if(a == null){
           em.getTransaction().commit();
           em.close();
           return false;
       }
       em.merge(this);
       em.getTransaction().commit();
       em.close();
       return true;
    }
    
    
     public void crearAnuncio(){
       conexion c = new conexion();
       EntityManager em = c.getEm();
       em.getTransaction().begin();
       em.persist(this);
       em.getTransaction().commit();
       em.close();
    
    }
        public void eliminarAnuncio(){
       conexion c = new conexion();
       EntityManager em = c.getEm();
       em.getTransaction().begin();
       Anuncio m = em.find(Anuncio.class, this.getCodanuncio());
       em.remove(m);
       em.getTransaction().commit();
       em.close();
    }
        public boolean VerificarPlaca(){
       conexion c = new conexion();
       JdbcTemplate j = c.getJdbcTemplate();
       int numero=j.queryForInt("select count(*) from anuncio where codvehiculo='"+codvehiculo.getPlaca()+"'");
       return (numero==0);
    }
    
        
        
        
    public List ejecutarQuery(String q){
     conexion c = new conexion();
     JdbcTemplate j = c.getJdbcTemplate();
     List l = j.query(q,this);
     return l;    
   }
  
}
