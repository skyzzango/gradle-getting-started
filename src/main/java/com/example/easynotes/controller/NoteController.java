package com.example.easynotes.controller;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Note;
import com.example.easynotes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController // combination of Spring’s @Controller and @ResponseBody annotations.
// indicate that the return value of a method should be used as the response body of the request.
@RequestMapping("/api") // declares that the url for all the apis in this controller will start with /api.
public class NoteController {

	private NoteRepository noteRepository;

	@Autowired
	public NoteController(NoteRepository noteRepository) {
		this.noteRepository = noteRepository;
	}

	// Get All Notes
	@GetMapping("/notes")
	public List<Note> getAllNotes() {

		// findAll() method to retrieve all the notes from the database and returns the entire list.
		return noteRepository.findAll();
	}

	// Create a new Note
	@PostMapping("/notes")
	public Note createNote(@Valid @RequestBody Note note) {
		// The @RequestBody annotation is used to bind the request body with a method parameter.
		// The @Valid annotation makes sure that the request body is valid.
		// Remember, we had marked Note’s title and content with @NotBlank annotation in the Note model?

		return noteRepository.save(note);
	}

	// Get a Single Note
	@GetMapping("/notes/{id}")
	public Note getNoteById(@PathVariable(value = "id") Long noteId) {
		// The @PathVariable annotation, as the name suggests, is used to bind a path variable with a method parameter.

		// In the above method, we are throwing a ResourceNotFoundException whenever a Note with the given id is not found.
		return noteRepository.findById(noteId)
				.orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
	}

	// Update a Note
	@PutMapping("/notes/{id}")
	public Note updateNote(@PathVariable(value = "id") Long noteId, @Valid @RequestBody Note noteDetails) {

		Note note = noteRepository.findById(noteId)
				.orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

		note.setTitle(noteDetails.getTitle());
		note.setContent(noteDetails.getContent());

		return noteRepository.save(note);
	}

	// Delete a Note
	@DeleteMapping("/notes/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {

		Note note = noteRepository.findById(noteId)
				.orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

		noteRepository.delete(note);

		return ResponseEntity.ok().build();
	}
}
