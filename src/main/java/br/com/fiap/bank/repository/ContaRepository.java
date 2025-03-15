package br.com.fiap.bank.repository;

import br.com.fiap.bank.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}
