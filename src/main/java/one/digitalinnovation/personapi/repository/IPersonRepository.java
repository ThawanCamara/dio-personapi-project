package one.digitalinnovation.personapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import one.digitalinnovation.personapi.entities.Person;

public interface IPersonRepository extends JpaRepository<Person, Long>{
	
}
