package br.com.tarefas.api_tarefas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Anotação que indica que esta é uma classe de repositório
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    // A mágica acontece aqui!
    // Não precisamos escrever nenhum método. JpaRepository já nos fornece:
    // save(), findAll(), findById(), deleteById(), etc.
}
