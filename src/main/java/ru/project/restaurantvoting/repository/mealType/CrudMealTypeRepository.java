package ru.project.restaurantvoting.repository.mealType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.project.restaurantvoting.model.MealType;

@Repository
@Transactional(readOnly = true)
public interface CrudMealTypeRepository extends JpaRepository<MealType, Integer> {

    @Override
    MealType getOne(Integer id);
}
