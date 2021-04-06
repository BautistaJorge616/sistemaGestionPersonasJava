package mx.com.gm.sga.cliente.ciclovidajpa;

import javax.persistence.*;
import mx.com.gm.sga.domain.Persona;
import org.apache.logging.log4j.*;

public class EncontrarObjetoJPA {

    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersonaPU");
        EntityManager em = emf.createEntityManager();

        //Inicia la transaccion
        //Paso 1. Iniciar transaccion
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //Paso 2. Ejecuta SQL de tipo "select"
        //Encontrar eñ objeto con el iD = 1
        Persona persona1 = em.find(Persona.class, 1);
        
        //Paso 3. termina la transaccion
        tx.commit();
        
        //Objeto en estado de "detached"
        log.debug("Objeto recuperado: " + persona1);
        
        //Cerramos el entity manager
        em.close();

    }
}
