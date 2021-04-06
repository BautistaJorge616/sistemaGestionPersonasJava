
package mx.com.sga.cliente.asociaciones;

import java.util.*;
import javax.persistence.*;
import mx.com.gm.sga.domain.*;
import org.apache.logging.log4j.*;


public class ClienteAsociacionesJPA {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersonaPU");
        EntityManager em = emf.createEntityManager();   
        
        List<Persona> personas = em.createNamedQuery("Persona.findAll").getResultList();
        
        //Cerramos la conexion
        em.close();
        
        //Mostrat todos los objetos
        for(Persona persona: personas){
            log.debug("Persona " + persona);
            
            //Recuperamos todos los usuarios que tiene la persona
            for(Usuario usuario: persona.getUsuarioList()){
                log.debug("Usuario " + usuario);
            }
        }
        
        
        
    }
}
