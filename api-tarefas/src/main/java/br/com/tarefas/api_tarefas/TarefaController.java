package br.com.tarefas.api_tarefas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Define que esta classe é um Controller REST
@RequestMapping("/api/tarefas") 
public class TarefaController {

    @Autowired 
    private TarefaRepository tarefaRepository;

    
    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa tarefa) {
        Tarefa novaTarefa = tarefaRepository.save(tarefa);
        return new ResponseEntity<>(novaTarefa, HttpStatus.CREATED);
    }

    
    @GetMapping
    public List<Tarefa> listarTodasAsTarefas() {
        return tarefaRepository.findAll();
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarTarefaPorId(@PathVariable Long id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        if (tarefa.isPresent()) {
            return new ResponseEntity<>(tarefa.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefaDetalhes) {
        Optional<Tarefa> tarefaOptional = tarefaRepository.findById(id);
        if (tarefaOptional.isPresent()) {
            Tarefa tarefaExistente = tarefaOptional.get();
            tarefaExistente.setNome(tarefaDetalhes.getNome());
            tarefaExistente.setDataEntrega(tarefaDetalhes.getDataEntrega());
            tarefaExistente.setResponsavel(tarefaDetalhes.getResponsavel());
            Tarefa tarefaAtualizada = tarefaRepository.save(tarefaExistente);
            return new ResponseEntity<>(tarefaAtualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 5. Remover uma tarefa (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id) {
        if (tarefaRepository.existsById(id)) {
            tarefaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}