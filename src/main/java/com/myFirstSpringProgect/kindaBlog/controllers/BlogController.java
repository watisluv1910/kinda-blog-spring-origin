package com.myFirstSpringProgect.kindaBlog.controllers;

import com.myFirstSpringProgect.kindaBlog.models.Post;
import com.myFirstSpringProgect.kindaBlog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/blog")
    public String blogMain(Model model) {
        Iterable<Post> posts = postRepository.findAll(); // Получение записей из таблицы в массив
        model.addAttribute("posts", posts); // Передача полученных записей в шаблон
        return "blog-main";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "blog-add";
    }

    @PostMapping("/blog/add") // PostMapping используется для получения данных из формы (метод post, Ex:blog-add.html s.13)
    public String blogPostAdd(@RequestParam String title, @RequestParam String announcement, @RequestParam String fullText, Model model) {
        Post post = new Post(title, announcement, fullText); // Создание объекта статьи
        postRepository.save(post); // Сохранение объекта в БД (добавление новой статьи в таблицу)
        return "redirect:/blog"; // Переадресация на главную страницу блога
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id); // В post хранится информация (из БД) о статье с полученным из URL id
        ArrayList<Post> res = new ArrayList<>(); // Использование ArrayList вместо Optional необходимо для упрощения работы и избежания ошибок
        post.ifPresent(res::add); // Теперь объект res содержит в основе post
        model.addAttribute("post", res);
        return "blog-details";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id); // В post хранится информация (из БД) о статье с полученным из URL id
        ArrayList<Post> res = new ArrayList<>(); // Использование ArrayList вместо Optional необходимо для упрощения работы и избежания ошибок
        post.ifPresent(res::add); // Теперь объект res содержит в основе post
        model.addAttribute("post", res);
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit") // PostMapping используется для получения данных из формы (метод post, Ex:blog-add.html s.13)
    public String blogPostEdit(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String announcement, @RequestParam String fullText, Model model) {
        Post post = postRepository.findById(id).orElseThrow(); // orElseThrow выбрасывает исключение, если запись не была найдена (обязателен)
        post.setTitle(title);
        post.setAnnouncement(announcement);
        post.setFullText(fullText);
        postRepository.save(post); // Метод save() обновляет уже существующий объект
        return "redirect:/blog"; // Переадресация на главную страницу блога
    }

    @PostMapping("/blog/{id}/delete") // PostMapping используется для получения данных из формы (метод post, Ex:blog-add.html s.13)
    public String blogPostDelete(@PathVariable(value = "id") long id, Model model) {
        Post post = postRepository.findById(id).orElseThrow(); // orElseThrow выбрасывает исключение, если запись не была найдена (обязателен)
        postRepository.delete(post);
        return "redirect:/blog"; // Переадресация на главную страницу блога
    }
}
