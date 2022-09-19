package oi.github.helioanacronista.projetocompletoservicos.model.repository;

import oi.github.helioanacronista.projetocompletoservicos.model.entitiy.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {
}
