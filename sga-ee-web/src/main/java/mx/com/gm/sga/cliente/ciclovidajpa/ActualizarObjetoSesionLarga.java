package mx.com.gm.sga.cliente.ciclovidajpa;

import javax.persistence.*;
import mx.com.gm.sga.domain.Persona;
import org.apache.logging.log4j.*;

public class ActualizarObjetoSesionLarga {

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

        log.debug("Objeto recuperado: " + persona1);

        //Paso 3. Modificamos los valores del objeto que se recupero
        persona1.setEmail("jorgeSky@mail.com");
        persona1.setNombre("Jorgito BB");

        //Paso 4.Terminamos la transaccion 
        tx.commit();

        //Objeto en estado de "detached" ya modificado
        log.debug("Objeto recuperado modificado: " + persona1);

        //Cerramos el entity manager
        em.close();
    }
}