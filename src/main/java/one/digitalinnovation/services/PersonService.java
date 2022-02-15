package one.digitalinnovation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entities.Person;
import one.digitalinnovation.personapi.repository.IPersonRepository;

@Service
public class PersonService {
	
	private IPersonRepository personRepository;
	
	@Autowired
	public PersonService(IPersonRepository personRepository) {
		this.personRepository = personRepository; 
	}
	
	
	public MessageResponseDTO createPerson(Person person) {
		Person savedPerson = personRepository.save(person);
		return MessageResponseDTO
				.builder()
				.message("Created person with ID " + savedPerson.getId())
				.build();
	}
}
