/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.example.REST_API.entities.Person;
import java.util.List;
//import java.util.Optional;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repositories.IPersonRepository;

/**
 *
 * @author SAMSUNG
 */


@RestController
@RequestMapping(value="/v1/person")
public class PersonController {
    
    @Autowired
    IPersonRepository persRep;
    
     @PostMapping (value="/create")
    public Person createPerson (@RequestBody Person person){
        
        return persRep.save(person);
    }
    
    @PutMapping(value="/update/{id}")
      public Person updatePerson (@PathVariable(value="id") Long id,@RequestBody Person person){
          if (id!=null)
          {
            //Optional<Person> p=persRep.findById(id);
            if (persRep.findById(id)!=null)
            {
                person.setId(id);
            return persRep.save(person);
            }
            
          }
          return  null;
      }
      
      
      @DeleteMapping(value="/delete/{id}")
      public void deletePerson (@PathVariable(value="id") Long id){
          if (id!=null)
          {
            //Optional<Person> p=persRep.findById(id);
            if (persRep.findById(id)!=null)
            persRep.deleteById(id);
          }
         
      }
      
      @GetMapping (value="/all")
       public List<Person> allPerson (){
         
            return persRep.findAll();
          
         
      }
       
       @GetMapping (value="/byname/{name}")
       public List<Person> allPerson_byName (@PathVariable(value="name") String nom){
         
            return persRep.getByNom(nom);
          
         
      }
    
}
