# Дипломный проект професси тестировщик
> Выполнил студент - Сафиуллин Айдар Азгарович.


### Инструкция по запуску проекта
1. Скачать проект с [репозитория](https://github.com/Aisafa/Diplom.git)
2. Выполнить в корневом каталоге <br> `docker-compose up -d`.
3. Включить `SUT` командой <br> `java -jar aqa-shop.jar --spring.datasource.url=jdbc:mysql://localhost:3306/app`.
4. Запустить автотесты.

### Необходимое окружение
- [x] [**Docker Desktop**](https://www.docker.com/products/docker-desktop)
- [x] [**AdoptOpenJDK**](https://adoptopenjdk.net/index.html)
- [x] [**IntelleJ IDEA**](https://www.jetbrains.com/ru-ru/idea/download/#section=windows)