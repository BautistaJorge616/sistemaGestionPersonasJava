
package mx.com.sga.cliente.cascade;

import javax.persistence.*;
import mx.com.gm.sga.domain.*;
import org.apache.logging.log4j.*;


public class PersistenciaCascadaJPA {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersonaPU");
        EntityManager em = emf.createEntityManager();
        
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        // 1. Crear un objeto en estado transitivo
        Persona persona1 = new Persona("Fer", "Zarate", "fer@mail.com", "12");
        
        Usuario usuario1 = new Usuario("Ferminator", "1", persona1);
        
        //Persistimos solo el objeto usuario
        em.persist(usuario1);
        
        tx.commit();
        
        //Objetos en estado "detached"
        log.debug("objeto persona " + persona1);
        log.debug("Objeto usuario " + usuario1);
        
        em.close();
        
    }
}
