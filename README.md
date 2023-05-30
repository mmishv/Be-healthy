# Be healthy
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)
	![CSS3](https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white)
![Bootstrap](https://img.shields.io/badge/bootstrap-%238511FA.svg?style=for-the-badge&logo=bootstrap&logoColor=white)
![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E)
## Описание
Проект разрабатывался студентами 2 курса ФПМИ БГУ в рамках изучения курса “Технологии программирования”.
Проект предназначен для помощи в организации рациона питания пользователей в зависимости от их параметров, уровня физической активности, а также поставленных целей (поддержание, набор или уменьшение веса). Каждый пользователь имеет доступ к расчету нормы КБЖУ, базе данных с энергетической ценностью отдельных продуктов и готовых блюд, базе рецептов и статей по теме здорового образа жизни, микшеру рецептов для составления своих блюд. Авторизированные пользователи могут вести дневник питания, устанавливать лимит по КБЖУ и добавлять собственные статьи и рецепты. Администратор имеет права редактирования всего контента, размещенного на сайте.

## Содержание
- [Технологии](#технологии)
- [Макеты](#макеты)
- [Диаграмма классов](#диаграмма-классов)
- [ER-модель](#er-модель)
- [Deployment Diagram](#deployment-diagram)
- [Package Diagram](#package-diagram)
- [Component Diagram](#component-diagram)
- [Use Case Diagram](#use-case-diagram)
- [Sequence Diagrams examples](#sequence-diagrams-examples)

## Технологии
- Java 19
- Maven - инструмент для управления зависимостями, сборки проекта и управления жизненным циклом проекта
- Apache Tomcat - контейнер сервлетов Java для развертывания веб-приложений
- PostgreSQL - основная СУБД проекта
- Zaxxer HikariCP - пул соединений (Connection Pool) для управления подключениями к базе данных.
- Apache Logging Log4j2 - библиотека для логирования 
- JUnit - фреймворк для модульного тестирования
- JSTL -  библиотека тегов для разработки веб-приложений с использованием технологии JSP
- GlassFish Jersey - реализация Java API для RESTful веб-сервисов (JAX-RS)
- io.swagger - набор инструментов и библиотек, предназначенных для работы со Swagger
- mindrot.jbcrypt - библиотека для хеширования паролей с использованием алгоритма bcrypt
- HTML
- CSS
- Bootstrap
- JavaScript

## [Макеты](https://www.figma.com/file/a8Vm9e39FOtfJCJrxQBAOm/Untitled?type=design&node-id=107-325&t=x0xGjGnVzfAfOoRA-0)
**Список шаблонов:**
1. Главная страница проекта.
2. Страница для регистрации/авторизации.
3. Страница дневника питания для авторизованного пользователя.
4. Страница с базой продуктов и микшером рецептов.
5. Страница статьи.
6. Страница с рецептами, всплывающее окно с рецптом.
7. Страница добавления рецепта для авторизированного пользователя.
8. Страница добавления статьи для авторизированного пользователя.
9. Страница профиля "Обо мне" для авторизованного пользователя.
10. Страница профиля "Мои рецепты" для авторизованного пользователя.
11. Страница профиля "Мои статьи" для авторизованного пользователя.
12. Страница "Список пользователей", доступная в режиме администратора.
13. Страница "База продуктов", доступная в режиме администратора.
14. Страницы "Список рецептов"/"Список статей", доступные в режиме администратора.
15. Страницы "Список категорий рецептов"/"Список категорий статей", доступные в режиме администратора.


 
**[Вернуться к содержанию](#содержание)**
## Диаграмма классов
![graphviz](https://github.com/mmishv/Be-healthy/assets/108868223/4fe4d417-3ff1-4a95-b5cc-abe08a157946)

**[Вернуться к содержанию](#содержание)**
## ER-модель
![бд](https://github.com/mmishv/Be-healthy/assets/108868223/15b26bc8-2951-4358-8681-cc8ba7956fa3)

**[Вернуться к содержанию](#содержание)**
## Deployment Diagram
![deployment](https://github.com/mmishv/Be-healthy/assets/108868223/abdd1f67-3176-415e-86ee-9f0008747405)

**[Вернуться к содержанию](#содержание)**
## Package Diagram
![package](https://github.com/mmishv/Be-healthy/assets/108416605/a2ec4567-66fa-43e1-96e1-c85c18570638)

**[Вернуться к содержанию](#содержание)**
## Component Diagram
![components](https://github.com/mmishv/Be-healthy/assets/108416605/21ced2f1-a3e4-4b5c-9d43-e6b74c9ee999)

**[Вернуться к содержанию](#содержание)**
## Use Case Diagram
![роли](https://github.com/mmishv/Be-healthy/assets/108868223/101b1853-8ecc-422d-969d-84a8401acbf2)

**[Вернуться к содержанию](#содержание)**
## Sequence Diagrams examples
![recipe](https://github.com/mmishv/Be-healthy/assets/108868223/e6026a7e-81e5-40e4-a1e8-ac5bc0c337ac)
![meal](https://github.com/mmishv/Be-healthy/assets/108868223/deaccfae-56b7-4597-bfe9-bad683a35bc1)
![reg](https://github.com/mmishv/Be-healthy/assets/108868223/a84deb22-c9a0-40a7-b8d5-e84f10f3a1e6)

**[Вернуться к содержанию](#содержание)**






