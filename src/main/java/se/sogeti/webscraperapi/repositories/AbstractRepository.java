package se.sogeti.webscraperapi.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AbstractRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

}
