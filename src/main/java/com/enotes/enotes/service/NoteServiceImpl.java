package com.enotes.enotes.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.enotes.enotes.entity.Notes;
import com.enotes.enotes.entity.User;
import com.enotes.enotes.repository.NotesRepository;

@Service
public class NoteServiceImpl implements NoteService{
    @Autowired
    private NotesRepository notesRepository;
    @Override
    public Notes savNotes(Notes notes) {
        return notesRepository.save(notes);
    }

    @Override
    public Notes getNotesById(int id) {
        return notesRepository.findById(id).get();
    }

    @Override
    public Page<Notes> getNotesByUser(User user,int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 5);
        return notesRepository.findByUser(user,pageable);
    }

    @Override
    public Notes updateNotes(Notes notes) {
        return notesRepository.save(notes);
    }

    @Override
    public boolean deleteNotes(int id) {
        Notes notes = notesRepository.findById(id).get();
        if (notes != null) {
            notesRepository.delete(notes);
            return true;
        }else{
            return false;
        }
        
    }

    
    
}
