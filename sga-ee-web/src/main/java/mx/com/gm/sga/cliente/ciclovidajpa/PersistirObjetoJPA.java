/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.gm.sga.cliente.ciclovidajpa;

import javax.persistence.*;
import mx.com.gm.sga.domain.Persona;
import org.apache.logging.log4j.*;

/**
 *
 * @author 52331
 */
public class PersistirObjetoJPA {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersonaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        //Inicia la transaccion
        
        //Paso 1. Crea nuevo objeto
        //Objeto en estado transitivo
        Persona persona1 = new Persona("Daniel", "Bautista", "dany123@mail.com", "3212");
        
        //Paso 2. Inicia transaccion
        tx.begin();
        
        //Paso 3. Ejecuta SQL
        em.persist(persona1);
        
        log.debug("Objeto persistido - aun sin commit" + persona1);
        
        //Paso 4. commit/rollback (Aqui se modifica la base de datos)               
        tx.commit();
        
        //Objeto en estado "detached"
        log.debug("Objeto persistido - estado detached " + persona1);
        
        //Cerramos el entity manager
        em.close();
        
        
        
        
    }
}
