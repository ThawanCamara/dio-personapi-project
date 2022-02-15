package one.digitalinnovation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entities.Person;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.IPersonRepository;

@Service
public class PersonService {
	
	private IPersonRepository personRepository;
	
	private final PersonMapper personMapper = PersonMapper.INSTANCE;
	
	@Autowired
	public PersonService(IPersonRepository personRepository) {
		this.personRepository = personRepository; 
	}	
	
	public MessageResponseDTO createPerson(PersonDTO personDTO) {
		Person personToSave = personMapper.toModel(personDTO);		

		Person savedPerson = personRepository.save(personToSave);
		return MessageResponseDTO
				.builder()
				.message("Created person with ID " + savedPerson.getId())
				.build();
	}
}
