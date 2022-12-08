package com.seppna.springbootnotebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//Домашнее задание:   CRUD приложение.
//Представьте, что мы делаем бесплатный сервис для скачивания книг.
//Пользователь должен видеть список всех книг, просматривать информацию о конкретной книге,
//добавлять свою книгу и изменять существующие книги.

//Разработайте CRUD приложение, которое будет уметь работать с книгами. Книга должна содержать следующие поля:
//• id (целое число, первичный ключ, автоинкремент)
//• Автор (строка)
//• Название книги (строка)
//• Жанр (строка)
//Храним книгу в базе данных sqlite. База данных будет состоять из одной таблицы Book.

@SpringBootApplication
public class SpringBootNotebookApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootNotebookApplication.class, args);
    }

}
