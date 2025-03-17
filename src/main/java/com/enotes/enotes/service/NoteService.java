package com.enotes.enotes.service;

// import java.util.List;

import org.springframework.data.domain.Page;

import com.enotes.enotes.entity.Notes;
import com.enotes.enotes.entity.User;

public interface NoteService {
    public Notes savNotes(Notes notes);
    public Notes getNotesById(int id);
    // public List<Notes> getNotesByUser(User user);
    public Page<Notes> getNotesByUser(User user,int pageNo);
    public Notes updateNotes(Notes notes);
    public boolean deleteNotes(int id);
    
}
