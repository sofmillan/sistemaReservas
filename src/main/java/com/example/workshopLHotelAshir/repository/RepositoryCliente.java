package com.example.workshopLHotelAshir.repository;
import com.example.workshopLHotelAshir.model.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryCliente extends CrudRepository<Cliente, Long> {
}
