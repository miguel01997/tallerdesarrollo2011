/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import Dao.conexion;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.postgresql.util.PSQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author luis
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByCodusuario", query = "SELECT u FROM Usuario u WHERE u.codusuario = :codusuario"),
    @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Usuario.findByApellido", query = "SELECT u FROM Usuario u WHERE u.apellido = :apellido"),
    @NamedQuery(name = "Usuario.findByEdad", query = "SELECT u FROM Usuario u WHERE u.edad = :edad"),
    @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email"),
    @NamedQuery(name = "Usuario.findByTipo", query = "SELECT u FROM Usuario u WHERE u.tipo = :tipo")})
    public class Usuario implements Serializable, RowMapper {
  //  private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator="usuario_codusuario_seq",strategy=GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "codusuario")
    private Integer codusuario;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "edad")
    private Integer edad;
    @Column(name = "email")
    private String email;
    @Column(name = "tipo")
    private String tipo;
    @OneToMany(mappedBy = "codusuario")
   private Collection<Anuncio> publica;
   
   
    @Transient
    private String membresia;

    public String getMembresia() {
        return membresia;
    }

    public void setMembresia(String membresia) {
        this.membresia = membresia;
    }

    public Usuario() {
    }

    public Usuario(Integer codusuario) {
        this.codusuario = codusuario;
    }

    public Integer getCodusuario() {
        return codusuario;
    }

    public void setCodusuario(Integer codusuario) {
        this.codusuario = codusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getEdad() {
        return edad;
    }
    
    public String getEdad_a() {
        return String.valueOf(edad);
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public Collection<Anuncio> getPublica() {
        return publica;
    }

    public void setPublica(Collection<Anuncio> publica) {
        this.publica = publica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codusuario != null ? codusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.codusuario == null && other.codusuario != null) || (this.codusuario != null && !this.codusuario.equals(other.codusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.Usuario[ codusuario=" + codusuario + " ]";
    }

    public Object mapRow(ResultSet rs, int i) throws SQLException {
       Usuario u = new Usuario();
        //u.setApellido(rs.getString("apellido"));
        u.setCodusuario(rs.getInt("codusuario"));
        //u.setEdad(rs.getInt("edad"));
        //u.setEmail(rs.getString("email"));
        //u.setNombre(rs.getString("nombre"));
        //u.setTipo(rs.getString("tipo"));
        //falta colgar la coleccion de anuncios
        u.buscarUsuario();
       int col = -1; 
       try{
           if((col =rs.findColumn("Gr.Memb.")) >1){
               u.setMembresia(rs.getString(col));
           }
       }catch(PSQLException e){
           u.setMembresia("");
       }
        return u;
    }
    
      public List buscarTodosUsuarios(){
       conexion c = new conexion();
        EntityManager em = c.getEm();
        Query q = em.createNamedQuery("Usuario.findAll");
        //System.out.println("*********PASA POR AQUI");
        //System.out.println(q.getResultList());
        return q.getResultList();
        
    }
    
    public void  buscarUsuario(){
        conexion c = new conexion();
        EntityManager em = c.getEm();
        em.getTransaction().begin();
        Usuario mm = em.find(Usuario.class, this.codusuario);
        em.getTransaction().commit();
        em.close();
        if(mm!= null){
           
           this.publica = mm.getPublica();
           this.apellido = mm.getApellido();
           this.edad = mm.getEdad();
           this.email = mm.getEmail();
           this.nombre = mm.getNombre();
           this.tipo = mm.getTipo();
           
        }
       //return new Modelo().setCodmodelo(122);
    }
    
    public void actualizarUsuario(){
       conexion c = new conexion();
       EntityManager em = c.getEm();
       em.getTransaction().begin();
       em.merge(this);
       em.getTransaction().commit();
       em.close();
    }
    
    public void eliminarUsuario(){
      conexion c = new conexion();
       EntityManager em = c.getEm();
       em.getTransaction().begin();
      Usuario m = em.find(Usuario.class, this.getCodusuario());
       em.remove(m);
       em.getTransaction().commit();
       em.close();
    }
    
    public void crearUsuario(){
       conexion c = new conexion();
       EntityManager em = c.getEm();
       em.getTransaction().begin();
       
       em.persist(this);
       em.getTransaction().commit();
       em.close();
    
    }

    

    
    /* public void crearUsuario(){
       conexion c = new conexion();
       JdbcTemplate j = c.getJdbcTemplate();
       j.update("insert into usuario (nombre,apellido,edad, email) values "+
                "(?,?,?,?)",new Object[]{nombre,apellido,edad, email}
                );
    
    }
     
      public List buscarTodosUsuarios(){
       conexion c = new conexion();
       JdbcTemplate select = c.getJdbcTemplate();
      return select.query("select * from usuario",new  Usuario());
    }

    public void modificarUsuario() {
        conexion c = new conexion();
       JdbcTemplate j = c.getJdbcTemplate();
       j.update("update usuario set nombre = ?,apellido=?,edad=?,email=? where codusuario=?  ",
               new Object[]{nombre,apellido,edad, email, codusuario});
       
       
    }
  public Usuario buscarUsuario(int id){
       conexion c = new conexion();
       JdbcTemplate j = c.getJdbcTemplate();
       String sql = "select * from usuario where codusuario="+id;
       Usuario m = (Usuario)j.queryForObject(sql,this);
       this.setNombre(m.getNombre());
       this.setApellido(m.getApellido());
       this.setEmail(m.getEmail());
       this.setEdad(m.getEdad());
       this.setCodusuario(m.getCodusuario());
       return  this;
       //return new Modelo().setCodmodelo(122);
    }

    public void eliminarUsuario() {
          conexion c = new conexion();
       JdbcTemplate j = c.getJdbcTemplate();
       j.update("delete from usuario where codusuario=? ",new Object[]{codusuario});
    }*/
    
      public boolean VerificarAnuncio(){
        
       conexion c = new conexion();
       JdbcTemplate j = c.getJdbcTemplate();
       int numero=j.queryForInt("select count(*) from anuncio where codusuario="+codusuario);
       
       return (numero==0);
        }

      
   public List ejecutarQuery(String query){
     conexion c = new conexion();
     JdbcTemplate j = c.getJdbcTemplate();
     List l = j.query(query,this);
     return l;    
   }
      
}