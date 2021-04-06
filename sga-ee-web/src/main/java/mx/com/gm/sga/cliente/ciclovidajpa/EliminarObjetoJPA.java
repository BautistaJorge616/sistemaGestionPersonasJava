package mx.com.gm.sga.cliente.ciclovidajpa;

import javax.persistence.*;
import mx.com.gm.sga.domain.Persona;
import org.apache.logging.log4j.*;

public class EliminarObjetoJPA {

    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersonaPU");
        EntityManager em = emf.createEntityManager();

        //Inicia la transaccion
        //Paso 1. Iniciar transaccion
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //Paso 2. Ejecutamos un SQL de tipo select
        Persona persona1 = em.find(Persona.class, 5);

        //Paso 3. Cerramos la transaccion 1
        tx.commit();

        //Objeto en estado detached
        log.debug("Objeto recuperado " + persona1);

        //Paso 4. Iniciamos la transaccion 2
        EntityTransaction tx2 = em.getTransaction();
        tx2.begin();

        //Paso 5. Ejecutamos el "delete"
        em.remove(em.merge(persona1));

        //Paso 6. Terminamos la transaccion 2
        tx2.commit();

        //Objeto en estado detached ya eliminado
        log.debug("Objeto ya eliminado " + persona1);
        
        //Cerramos el entity manager
        em.close();
    }
}
