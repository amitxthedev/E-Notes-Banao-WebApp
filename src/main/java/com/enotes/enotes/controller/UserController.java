package com.enotes.enotes.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.enotes.enotes.entity.Notes;
import com.enotes.enotes.entity.User;
import com.enotes.enotes.repository.UserRepository;
import com.enotes.enotes.service.NoteService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")

// after login to show logout button insted of login/register buttons
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NoteService noteService;
    

    @ModelAttribute
    public User getUser(Principal principal,Model model)
    {
        String email = principal.getName();
        User user = userRepository.findByEmail(email);
        
        model.addAttribute("user", user);

        return user;
    }

    @GetMapping("/addNotes")
    public String addNotes()
    {
        return "add_notes";
    }

    @GetMapping("/viewNotes")
    public String viewNotes(Model model,Principal principal,@RequestParam(defaultValue = "0") Integer pageNo)
    {
        User user = getUser(principal, model);
        Page<Notes> allnotes = noteService.getNotesByUser(user,pageNo);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalElements",allnotes.getTotalElements() );
        model.addAttribute("totalPages", allnotes.getTotalPages());
        model.addAttribute("noteslist", allnotes.getContent());
        return "view_notes";
    }

    @GetMapping("/editNotes/{id}")
    public String editNotes(@PathVariable int id,Model model)
    {
        Notes notes = noteService.getNotesById(id);
        model.addAttribute("n", notes);
        return "edit_notes";
    }

    @PostMapping("/saveNotes")
    public String saveNotes(@ModelAttribute Notes notes,HttpSession session,Principal principal,Model model)
    {
        notes.setDate(LocalDate.now());
        notes.setUser(getUser(principal, model));
        Notes savNotes = noteService.savNotes(notes);

        if(savNotes != null)
            {
                //ERROR MESSAGE
                session.setAttribute("msg", "Notes Added Successfull");
            }else
            {
                session.setAttribute("msg", "Something went Wrong on Server");
            }
            return "redirect:/user/addNotes";
    }
    @PostMapping("/updateNotes")
    public String updateNotes(@ModelAttribute Notes notes,HttpSession session,Principal principal,Model model)
    {
        notes.setDate(LocalDate.now());
        notes.setUser(getUser(principal, model));
        Notes savNotes = noteService.savNotes(notes);

        if(savNotes != null)
            {
                //ERROR MESSAGE
                session.setAttribute("msg", "Notes Updated Successfull");
            }else
            {
                session.setAttribute("msg", "Something went Wrong on Server");
            }
            return "redirect:/user/viewNotes";
    }

    @GetMapping("/deleteNotes/{id}")
    public String deleteNotes(@PathVariable int id, HttpSession session) {
        boolean isDeleted = noteService.deleteNotes(id);

        if (isDeleted) {
            session.setAttribute("msg", "Notes Deleted Successfully");
        } else {
            session.setAttribute("msg", "Something went Wrong on Server");
        }
        return "redirect:/user/viewNotes";
    }
}
