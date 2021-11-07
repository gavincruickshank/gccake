package co.uk.gcsample.cakes.repository;

import co.uk.gcsample.cakes.entity.Cake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CakeRepository extends JpaRepository<Cake, String> {
    Cake findByTitle(String title);
}
