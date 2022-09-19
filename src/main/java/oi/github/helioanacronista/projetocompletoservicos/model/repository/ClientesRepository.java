package oi.github.helioanacronista.projetocompletoservicos.model.repository;

import oi.github.helioanacronista.projetocompletoservicos.model.entitiy.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientesRepository extends JpaRepository<Cliente, Integer> {
}
