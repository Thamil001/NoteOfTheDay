package noteOfTheDay.NoteOfTheDay.Conroller;

import noteOfTheDay.NoteOfTheDay.Entity.NotesEntity;
import noteOfTheDay.NoteOfTheDay.Repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping
public class Controller {
    @Autowired
    NotesRepository notesRepo;

    @GetMapping("/")
    public List<NotesEntity> getData() {
        return notesRepo.findAll();
    }

    @PostMapping("/addnotes")
    public ResponseEntity<?> postData(@RequestBody NotesEntity noteData) {

        if (noteData.getContent() == null || noteData.getTitle() == null || noteData.getAuthor() == null) {

            return ResponseEntity.badRequest().body("Notes Failed");
        }

        NotesEntity data = notesRepo.save(noteData);

        return ResponseEntity.ok("Notes Successfully stored");
    }


    @DeleteMapping("/deletenotes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable Long id) {
        System.out.println("Id :" +id);
        Optional<NotesEntity> found = notesRepo.findById(id);
        if(found.isEmpty() ) {
            return ResponseEntity.badRequest().body("Id :"+id+" is not Found");
        }

        notesRepo.deleteById(id);

        return ResponseEntity.ok("Id :"+id+" Successfully Deleted");
    }

}
