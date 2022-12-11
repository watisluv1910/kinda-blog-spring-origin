package com.myFirstSpringProgect.kindaBlog.repo;

// Для управления каждой моделью необходим отдельный репозиторий
// Репозиторий может быть только интерфейсом

import com.myFirstSpringProgect.kindaBlog.models.Post; // Модель, с которой будет работать данный репозиторий
import org.springframework.data.repository.CrudRepository; // Встроенный интерфейс, имеющий базовые функции

public interface PostRepository extends CrudRepository<Post, Long> { // Здесь Long - тип данных id

}
