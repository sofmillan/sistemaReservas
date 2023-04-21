package com.example.workshopLHotelAshir.repository;
import com.example.workshopLHotelAshir.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
}
