## Hexlet tests and linter status:
[![Actions Status](https://github.com/mrmelvin/java-project-78/workflows/hexlet-check/badge.svg)](https://github.com/mrmelvin/java-project-78/actions)

## CodeClimate

[![Maintainability](https://api.codeclimate.com/v1/badges/44df09e16c8f51e319ff/maintainability)](https://codeclimate.com/github/mrmelvin/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/44df09e16c8f51e319ff/test_coverage)](https://codeclimate.com/github/mrmelvin/java-project-78/test_coverage)

### Описание
Валидатор данных – библиотека, с помощью которой можно проверять корректность любых данных. 
Проверка может быть строгой и нет. Нестрогая проверка подразумевает под собой, что null также является валидным значением.
Строгая проверка включается добавлением метода required() в проверку.

### Поддерживаемые типы
+ Строка 
+ Целое число
+ Ассоциативный массив

### Примеры использования
#### Валидация строк
Используемые методы:

+ required() – требует, чтобы данные были любой непустой строкой
+ minLength() – требует, чтобы данные были строкой и имели длину равную или больше указанного значения
+ contains() – требует, чтобы данные содержали указанную подстроку

```java
import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;

Validator validator = new Validator();

StringSchema stringSchema = validator.string();

// Значения валидны, т.к. не добавлен метод required()
// true
stringSchema.isValid(null);
//true
stringSchema.isValid(""); 

stringSchema.required();

// Значения невалидны, т.к. был добавлен метод required()
// false
stringSchema.isValid(null);
stringSchema.isValid("");
stringSchema.isValid(9851);

//true
stringSchema.isValid("foobar"); 

```

#### Валидация целых чисел
Используемые методы:

+ required() – требует, чтобы данные были любым целым числом, включая ноль
+ positive() – требует, чтобы данные положительным числом
+ range() – требует, чтобы данные были в указанном диапазоне, включая граничные значения

```java
import hexlet.code.Validator;
import hexlet.code.schemas.NumberSchema;

Validator validator = new Validator();

NumberSchema numberSchema = validator.number();

// Значения валидны, т.к. не добавлен метод required()
// true
numberSchema.isValid(null);
numberSchema.positive().isValid(null);

numberSchema.required();
// Значения невалидны, т.к. был добавлен метод required()
// false
numberSchema.isValid(null);
numberSchema.isValid(null);
numberSchema.isValid("some string");
numberSchema.isValid(0);
numberSchema.isValid(-8);

//true
numberSchema.isValid(2);

numberSchema.range(10, 20);

//false
numberSchema.isValid(23);

//true
numberSchema.isValid(16);
```

#### Валидация объектов типа Map
Используемые методы:

+ required() – требует, чтобы данные были объектом типа Map
+ sizeof() – требует, чтобы количество пар ключ-значений в объекте Map должно быть равно заданному

```java
import hexlet.code.Validator;
import hexlet.code.schemas.MapSchema;

Validator validator = new Validator();

MapSchema mapSchema = validator.map();

// Значения валидны, т.к. не добавлен метод required()
// true
mapSchema.isValid(null);

mapSchema.required();

// Значения невалидны, т.к. был добавлен метод required()
// false
mapSchema.isValid(null);

// true
mapSchema.isValid(new HashMap());

Map<String, String> data = new HashMap<>();
data.put("key1", "value1");

// true
mapSchema.isValid(data);

mapSchema.sizeof(2);

// false
mapSchema.isValid(data);  
data.put("key2", "value2");

// true
mapSchema.isValid(data); 

```

#### Вложенная валидация

Используемые методы:

+ shape() - позволяет описывать валидацию для значений объекта Map по ключам.

```java
import hexlet.code.Validator;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;

Validator validator = new Validator();

MapSchema schema = validator.map();

// Создаём Map по шаблону которой будем проверять данные в других Map
Map<String, BaseSchema> patternMap = new HashMap<>();
patternMap.put("name", v.string().required());
patternMap.put("age", v.number().positive());
schema.shape(patternMap);

// true
Map<String, Object> human1 = new HashMap<>();
human1.put("name", "Kolya");
human1.put("age", 100);
schema.isValid(human1); 

Map<String, Object> human2 = new HashMap<>();
human2.put("name", "Maya");
human2.put("age", null);
schema.isValid(human2);

// false        
Map<String, Object> human3 = new HashMap<>();
human3.put("name", "");
human3.put("age", null);
schema.isValid(human3); 

Map<String, Object> human4 = new HashMap<>();
human4.put("name", "Valya");
human4.put("age", -5);
schema.isValid(human4);
```
