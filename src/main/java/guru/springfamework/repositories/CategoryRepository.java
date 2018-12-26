package guru.springfamework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import guru.springfamework.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
