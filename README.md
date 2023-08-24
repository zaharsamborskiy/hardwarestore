REST-сервис для хранения данных о витринах товаров в магазинах бытовой техники.

Структура данных:
Витрина
-----------------------------------------
Идентификатор (UUID)
Название витрины
Адрес (помещения где находится витрина)
Тип
Дата создания
Дата последней актуализации

Товар
-----------------------------------------
Идентификатор
Идентификатор витрины на которой выставлен товар
Позиция товара на витрине
Наименование
Тип товара
Цена
Дата добавления
Дата изменения

REST-сервис должен предоставляет следующие методы:

-@Getmapping("/showcases) getAllShowcases()
Получить все витрины
-@GetMapping("/showcases/type") getAllShowcasesByType()
 возможность фильтрации по типу
-@GetMapping("/showcases/address") getAllShowcasesByAddress()
возможность получить витрины по адресу
-@GetMapping("/showcases/createAt") getAllShowcasesByCreateDate()
возможность получить витрины за период по дате создания
-@GetMapping("/showcases/updateAt") getAllShowcasesByLastUpdatedDate()
возможность получить витрины за период по дате последней актуализации
-@GetMapping("/products/{showcase_id}") getAllProductsOnShowcase()
Получить все товары витрины
-@GetMapping("/product/type") getAllProductsByType()
возможность фильтрации по типу товара
-@GetMapping("/product/greaterPrice") getAllProductsByGreaterPrice()
-@GetMapping("/product/lessPrice") getAllProductsByLessPrice()
-@GetMapping("/product/price") getAllProductsByPrice()
возможность фильтрации по диапозону цен
-@PostMapping("/showcase") createShowcase()
Добавить витрину
-@PostMapping("/product") addProductOnShowcase()
Добавить товар на витрину
-@PatchMapping("/showcase/{id}") updateShowcase()
Изменение данных витрины
-@PatchMapping("/product/{id}") updateProduct()
Изменение данных товара
-@DeleteMapping("/showcase/{id}")  deleteShowcase()
Удаление витрины
-@DeleteMapping("/product/{id}") deleteProduct()
Удаление товар

Сервис при первом запуске самостоятельно создает необходимые объекты в БД с помощью Liquibase.
База данных используется PostgreSQL.

Сервис реализован с помошью: Java 8, Maven, Spring Boot, Hibernate, PostgreSQL, Liquibase.
