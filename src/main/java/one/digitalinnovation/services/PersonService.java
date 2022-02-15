package one.digitalinnovation.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import one.digitalinnovation.exceptions.PersonNotFoundException;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entities.Person;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.IPersonRepository;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {
	
	private IPersonRepository personRepository;
	
	private final PersonMapper personMapper = PersonMapper.INSTANCE;
	
	public MessageResponseDTO createPerson(PersonDTO personDTO) {
		Person personToSave = personMapper.toModel(personDTO);		

		Person savedPerson = personRepository.save(personToSave);
		return createMessageResponse(savedPerson.getId(), "Updated person with ID ");
	}

	public List<PersonDTO> listAll() {
		List<Person> allPeople = personRepository.findAll();
		return allPeople.stream()
				.map(personMapper::toDTO)
				.collect(Collectors.toList());		
	}

	public PersonDTO findById(Long id) throws PersonNotFoundException {
		Person person = verifyIfExists(id);
		return personMapper.toDTO(person);
	}

	public MessageResponseDTO updatebyId(Long id, PersonDTO personDTO) throws PersonNotFoundException {
		verifyIfExists(id);
		
		Person personToUpdate = personMapper.toModel(personDTO);		

		Person updatedPerson = personRepository.save(personToUpdate);
		return createMessageResponse(updatedPerson.getId(), "Updated person with ID ");
	}
	
	public void delete(Long id) throws PersonNotFoundException {
		verifyIfExists(id);
		personRepository.deleteById(id);		
	}

	private Person verifyIfExists(Long id) throws PersonNotFoundException {
		return personRepository.findById(id)
				.orElseThrow(() -> new PersonNotFoundException(id));
	}
	
	private MessageResponseDTO createMessageResponse(Long id, String s) {
		return MessageResponseDTO
				.builder()
				.message(s + id)
				.build();
	}

}
