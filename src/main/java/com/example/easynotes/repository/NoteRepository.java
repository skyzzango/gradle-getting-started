package com.example.easynotes.repository;

import com.example.easynotes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // This tells Spring to bootstrap the repository during component scan.
public interface NoteRepository extends JpaRepository<Note, Long> {

	// That is all you have to do in the repository layer.
	// You will now be able to use JpaRepository’s methods like save(), findOne(), findAll(), count(), delete() etc.
}
