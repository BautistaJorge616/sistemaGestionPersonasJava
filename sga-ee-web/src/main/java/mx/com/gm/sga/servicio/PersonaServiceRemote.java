
package mx.com.gm.sga.servicio;
import java.util.List;
import javax.ejb.Remote;
import mx.com.gm.sga.domain.Persona;

//Agregamos la notacion "@Remote" para acceder a esta interfazas de manera remota
@Remote
public interface PersonaServiceRemote {
    
    public List<Persona> listarPersonas();
    
    public Persona encontrarPersonaPorId(Persona persona);
    
    public Persona enontrarPersonaPorEmail(Persona persona);
    
    public void registrarPersona (Persona persona);
    
    public void modificarPersona(Persona persona);
    
    public void eliminarPersona(Persona persona);
    
}
