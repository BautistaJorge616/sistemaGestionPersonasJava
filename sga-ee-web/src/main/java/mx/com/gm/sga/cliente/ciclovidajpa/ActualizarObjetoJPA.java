package mx.com.gm.sga.cliente.ciclovidajpa;

import javax.persistence.*;
import mx.com.gm.sga.domain.Persona;
import org.apache.logging.log4j.*;

public class ActualizarObjetoJPA {

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

        //Paso 3. termina la transaccion 1
        tx.commit();

        //Objeto en estado de "detached"
        log.debug("Objeto recuperado: " + persona1);

        //Paso 4. Modificamos los valores del objeto que se recupero
        persona1.setApellido("Skywalker");

        //Paso 5. Iniciamos una nueva transaccion
        EntityTransaction tx2 = em.getTransaction();
        tx2.begin();

        //Paso 6. Modificamos el objeto
        em.merge(persona1);

        //Paso 7.Terminamos la transaccion 2
        tx2.commit();

        //Objeto en estado de "detached" ya modificado
        log.debug("Objeto recuperado modificado: " + persona1);

        //Cerramos el entity manager
        em.close();

    }
}
