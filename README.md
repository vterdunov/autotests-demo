# Демо автотестов

Проект автоматизированного тестирования для Wikipedia Web и Wikipedia Android App с использованием Selenium, Appium и TestNG.

## Структура проекта

```
autotests-demo/
├── pom.xml                          # Конфигурация Maven
├── testng-web.xml                   # Конфигурация TestNG для веб-тестов
├── testng-mobile.xml                # Конфигурация TestNG для мобильных тестов
├── app/
│   └── wikipedia.apk                # APK Wikipedia (добавить вручную)
└── src/test/
    ├── java/com/autotests/
    │   ├── core/                    # Менеджеры драйверов и конфигурация
    │   ├── pages/
    │   │   ├── web/                 # Page Object для веб-страниц
    │   │   └── mobile/              # Page Object для мобильных экранов
    │   └── tests/
    │       ├── web/                 # Веб-тесты
    │       └── mobile/              # Мобильные тесты
    └── resources/
        └── config.properties        # Конфигурация тестов
```

## Требования

### Общие
- Java JDK 11 или выше
- Maven 3.6+
- Git

### Веб-тесты
- Браузер Chrome (последняя версия)
- ChromeDriver (управляется автоматически через WebDriverManager)

### Мобильные тесты
- Android SDK
- Android эмулятор или физическое устройство
- Appium Server 2.x
- UiAutomator2 драйвер для Appium
- APK приложения Wikipedia

## Установка

### 1. Клонирование репозитория

```bash
git clone https://github.com/vterdunov/autotests-demo.git
cd autotests-demo
```

### 2. Установка зависимостей

```bash
mvn clean install -DskipTests
```

### 3. Настройка мобильного тестирования

#### Установка Appium

```bash
npm install -g appium
```

#### Установка драйвера UiAutomator2

```bash
appium driver install uiautomator2
```

#### Загрузка APK Wikipedia

Скачайте APK Wikipedia с одного из источников:
- [F-Droid](https://f-droid.org/packages/org.wikipedia/)
- [APKPure](https://apkpure.com/wikipedia/org.wikipedia)

Поместите файл APK в директорию `app/` и переименуйте в `wikipedia.apk`.

#### Настройка Android эмулятора

1. Откройте Android Studio
2. Перейдите в Tools → Device Manager
3. Создайте новое виртуальное устройство (рекомендуется: Pixel 4 с API 30+)
4. Запустите эмулятор

## Запуск тестов

### Веб-тесты

Запуск всех веб-тестов:

```bash
mvn test -Pweb
```

Или через TestNG XML напрямую:

```bash
mvn test -DsuiteXmlFile=testng-web.xml
```

### Мобильные тесты

1. Запустите Appium сервер:

```bash
appium
```

2. Запустите Android эмулятор

3. Запустите мобильные тесты:

```bash
mvn test -Pmobile
```

Или через TestNG XML напрямую:

```bash
mvn test -DsuiteXmlFile=testng-mobile.xml
```

### Запуск всех тестов

```bash
mvn test
```

## Конфигурация

Отредактируйте файл `src/test/resources/config.properties` для настройки параметров тестов:

```properties
# Конфигурация веб-тестов
web.base.url=https://www.wikipedia.org
web.browser=chrome
web.timeout=10

# Конфигурация мобильных тестов
mobile.platform.name=Android
mobile.device.name=Android Emulator
mobile.automation.name=UiAutomator2
mobile.app.package=org.wikipedia
mobile.app.activity=org.wikipedia.main.MainActivity
mobile.apk.path=app/wikipedia.apk
mobile.appium.url=http://127.0.0.1:4723
mobile.timeout=15

# Общие настройки
implicit.wait=10
explicit.wait=15
```

## Тестовые сценарии

### Веб-тесты (Wikipedia Web)

| № | Тест | Описание |
|---|------|----------|
| 1 | testLogoIsDisplayed | Проверка отображения логотипа Wikipedia |
| 2 | testCentralFeaturedSectionDisplayed | Проверка центральной секции |
| 3 | testSearchInputPlaceholder | Проверка placeholder в поле поиска |
| 4 | testSearchReturnsResults | Проверка возврата результатов поиска |
| 5 | testArticlePageOpens | Проверка открытия страницы статьи |
| 6 | testArticleTitleMatchesSearch | Проверка соответствия заголовка статьи запросу |
| 7 | testArticleContentDisplayed | Проверка отображения контента статьи |
| 8 | testSearchInputClear | Проверка очистки поля поиска |
| 9 | testSearchWithDifferentQueries | Параметризованный тест поиска |
| 10 | testPageTitleContainsWikipedia | Проверка заголовка страницы |

### Мобильные тесты (Wikipedia Android)

| № | Тест | Описание |
|---|------|----------|
| 1 | testMainScreenElements | Проверка элементов главного экрана |
| 2 | testSearchFunctionality | Проверка работы поиска |
| 3 | testOpenArticleFromSearch | Проверка открытия статьи |
| 4 | testArticleScroll | Проверка прокрутки статьи |
| 5 | testSearchResultsCount | Проверка количества результатов |
| 6 | testFirstResultTitle | Проверка заголовка первого результата |
| 7 | testSearchWithDifferentQueries | Параметризованный поиск |
| 8 | testNavigationBackFromArticle | Проверка навигации назад |

## Используемые технологии

- **Java 11** — язык программирования
- **Maven** — сборка и управление зависимостями
- **Selenium WebDriver 4.x** — автоматизация браузера
- **Appium 9.x** — автоматизация мобильных приложений
- **TestNG 7.x** — фреймворк тестирования
- **WebDriverManager** — автоматическое управление драйверами

## Решение проблем

### Веб-тесты

**Проблема**: Несовместимость версии ChromeDriver  
**Решение**: WebDriverManager решает это автоматически. Если проблема сохраняется, обновите браузер Chrome.

**Проблема**: Элемент не найден  
**Решение**: Увеличьте значения таймаутов в `config.properties`

### Мобильные тесты

**Проблема**: Appium сервер не запущен  
**Решение**: Запустите Appium сервер командой `appium`

**Проблема**: Устройство не найдено  
**Решение**: 
- Проверьте, что эмулятор запущен: `adb devices`
- Перезапустите ADB: `adb kill-server && adb start-server`

**Проблема**: Приложение не установлено  
**Решение**: Проверьте путь к APK в `config.properties` и убедитесь, что APK существует в директории `app/`

## Соответствие требованиям проекта

### 1. Автотесты для сайта

#### Покрытие тестами основных сценариев
- [x] Реализовано **10 тестовых сценариев** (требуется минимум 4)
- [x] Тесты покрывают: поиск, навигация, отображение элементов, работа форм
- [x] Используется DataProvider для параметризации тестов
- [x] Тесты стабильные и повторяемые

**Реализованные сценарии:**
1. `testLogoIsDisplayed` — проверка отображения логотипа
2. `testCentralFeaturedSectionDisplayed` — проверка центральной секции
3. `testSearchInputPlaceholder` — проверка placeholder поля поиска
4. `testSearchReturnsResults` — проверка возврата результатов поиска
5. `testArticlePageOpens` — проверка открытия страницы статьи
6. `testArticleTitleMatchesSearch` — проверка соответствия заголовка статьи
7. `testArticleContentDisplayed` — проверка отображения контента
8. `testSearchInputClear` — проверка очистки поля поиска
9. `testSearchWithDifferentQueries` — параметризованный тест (3 набора данных)
10. `testPageTitleContainsWikipedia` — проверка заголовка страницы

#### Качество реализации тестов
- [x] Используется **Page Object Model** для всех страниц
- [x] Реализованы классы: `BasePage`, `HomePage`, `SearchResultsPage`, `ArticlePage`
- [x] Используется **WebDriverWait** для явных ожиданий
- [x] Локаторы вынесены в Page Object классы через `@FindBy`
- [x] Логика взаимодействия с элементами вынесена из тестов в Page Object

#### Настройка окружения и запуск
- [x] README содержит инструкции по запуску веб-тестов
- [x] Указаны команды `mvn test -Pweb` и `mvn test -DsuiteXmlFile=testng-web.xml`
- [x] Конфигурация через `config.properties`

---

### 2. Автотесты для мобильного приложения Wikipedia

#### Покрытие мобильных сценариев
- [x] Реализовано **8 тестовых сценариев** (требуется минимум 3)
- [x] Тесты покрывают: поиск статьи, открытие статьи, проверка заголовка, прокрутка
- [x] Используется DataProvider для параметризации тестов

**Реализованные сценарии:**
1. `testMainScreenElements` — проверка элементов главного экрана
2. `testSearchFunctionality` — проверка работы поиска
3. `testOpenArticleFromSearch` — проверка открытия статьи из поиска
4. `testArticleScroll` — проверка прокрутки статьи
5. `testSearchResultsCount` — проверка количества результатов
6. `testFirstResultTitle` — проверка заголовка первого результата
7. `testSearchWithDifferentQueries` — параметризованный тест (3 набора данных)
8. `testNavigationBackFromArticle` — проверка навигации назад

#### Корректность настройки Appium
- [x] Настроены **capabilities** в `MobileDriverManager.java`
- [x] Указаны: `platformName`, `deviceName`, `automationName`, `appPackage`, `appActivity`
- [x] README содержит пошаговые инструкции по настройке Appium
- [x] Конфигурация вынесена в `config.properties`

#### Качество кода мобильных тестов
- [x] Логичная структура: `BaseScreen`, `MainScreen`, `SearchScreen`, `ArticleScreen`
- [x] Правильная работа с локаторами через `@FindBy`
- [x] Минимальное дублирование кода
- [x] Реализованы методы scroll через PointerInput

---

### 3. Качество проекта, структура, архитектура

- [x] **Логичное разделение пакетов:**
  - `core/` — менеджеры драйверов и конфигурация
  - `pages/web/` — Page Object для веб-страниц
  - `pages/mobile/` — Page Object для мобильных экранов
  - `tests/web/` — веб-тесты
  - `tests/mobile/` — мобильные тесты
- [x] Реализован **Page Object Model** для веб и мобильных тестов
- [x] Единый стиль кода и именования
- [x] Базовые классы (`BasePage`, `BaseScreen`) с общей логикой

---

### 4. Документация (README + инструкции)

- [x] README содержит описание проекта
- [x] Пошаговое описание запуска веб-тестов
- [x] Пошаговое описание запуска мобильных тестов
- [x] Указаны все зависимости (Java 11+, Maven, Appium, Android SDK)
- [x] Примеры команд запуска
- [x] Раздел Troubleshooting с решением типичных проблем
- [x] Описание используемых технологий
