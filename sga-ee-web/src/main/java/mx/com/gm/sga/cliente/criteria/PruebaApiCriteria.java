package mx.com.gm.sga.cliente.criteria;

import java.util.*;
import javax.persistence.*;
import javax.persistence.criteria.*;
import mx.com.gm.sga.domain.Persona;
import org.apache.logging.log4j.*;

public class PruebaApiCriteria {

    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersonaPU");
        EntityManager em = emf.createEntityManager();

        //Paso 1
        CriteriaBuilder cb = null;
        CriteriaQuery<Persona> criteriaQuery = null;
        Root<Persona> fromPersona = null;
        TypedQuery<Persona> query = null;
        Persona persona = null;
        List<Persona> personas = null;

        //Query utilizando el API de Criteria
        //1. Consulta todas las personas 
//        
//        //Paso 1. El objeto EntityManger crea instancia CriteriaBuilde
//        cb = em.getCriteriaBuilder();
//        
//        //Paso 2. Se crea un objeto CriteriaQuery
//        criteriaQuery = cb.createQuery(Persona.class);
//        
//        //Paso 3. Creamos el objeto raiz de query
//        fromPersona = criteriaQuery.from(Persona.class);
//        
//        //Paso 4. Seleccionamos lo necesario del from
//        criteriaQuery.select(fromPersona);
//        
//        //Paso 5. Creamos el query TypedSafe
//        query = em.createQuery(criteriaQuery);
//        
//        //Paso 6. Ejecutamos la consulta
//        personas = query.getResultList();
//        
//        mostrarPersonas(personas);

        //2. Consulta de la persona con id = 1
//        cb = em.getCriteriaBuilder();
//        criteriaQuery = cb.createQuery(Persona.class);
//        fromPersona = criteriaQuery.from(Persona.class);
//        criteriaQuery.select(fromPersona).where(cb.equal(fromPersona.get("idPersona"),1));
//        persona = em.createQuery(criteriaQuery).getSingleResult();
//        log.debug(persona);
        
        
        //2-b Consulta de la persona con id = 1
        cb = em.getCriteriaBuilder();
        criteriaQuery = cb.createQuery(Persona.class);
        fromPersona = criteriaQuery.from(Persona.class);
        criteriaQuery.select(fromPersona);
        
        //La clase "Predicate" nos permite agregar varios criterios dinamicamente
        List<Predicate> criterios = new ArrayList<Predicate>();
        
        //Verificamos si tenemos criterios que agregar
        Integer idPersonaParam = 1;
        ParameterExpression<Integer> parameter =cb.parameter(Integer.class,"idPersona");
        criterios.add(cb.equal(fromPersona.get("idPersona"), parameter));
        
        //Se agregan los criterios
        if(criterios.isEmpty()){
            throw new RuntimeException("Sin criterios");
        }
        else if(criterios.size() == 1){
            criteriaQuery.where(criterios.get(0));
        }
        else{
            criteriaQuery.where(cb.and(criterios.toArray(new Predicate[0])));
        }
        
        query = em.createQuery(criteriaQuery);
        query.setParameter("idPersona", idPersonaParam);
        
        //Ejecutamos el query
        persona = query.getSingleResult();
        log.debug(persona);
        
    }

    private static void mostrarPersonas(List<Persona> personas) {
        for (Persona p : personas) {
            log.debug(p);
        }
    }

}
