package com.example.ApiRestYounes.Controlador;

import com.example.ApiRestYounes.Model.CompaniaAerea;
import com.example.ApiRestYounes.Repository.CompaniaRepository;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ApiYounes")
public class CompaniaControlador {
    private CompaniaRepository companiaRepository;

    public CompaniaControlador(CompaniaRepository companiaRepository) {
        this.companiaRepository = companiaRepository;
    }

    @GetMapping("/companias")
    public List<CompaniaAerea> getAllCompania(){
        return companiaRepository.findAll();
    }

    @GetMapping("/compania")
    public CompaniaAerea getCompaniaById(@RequestParam ObjectId id){
        return companiaRepository.findById(id).orElse(null);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/guardar-compania")
    public void savePost(@RequestBody CompaniaAerea companiaAerea){
        companiaRepository.save(companiaAerea);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/actualizar-compania")
    public void updatePost(@RequestParam ObjectId id, @RequestBody CompaniaAerea companiaAerea){
        companiaRepository.save(companiaAerea);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/eliminar-compania/{id}")
    public void eliminarCompania(@PathVariable ObjectId id){
        companiaRepository.delete(companiaRepository.findById(id).orElse(null));
    }
}
