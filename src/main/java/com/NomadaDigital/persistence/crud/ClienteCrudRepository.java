package com.NomadaDigital.persistence.crud;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.NomadaDigital.persistence.entity.Cliente;

public interface ClienteCrudRepository extends CrudRepository<Cliente, Long>  {

    Optional<Cliente> findByEmail(String email);
}
