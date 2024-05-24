package ru.matthew.MyShop.models;

public enum Category {

    ELECTRONICS("Электроника"),
    APPLIANCES("Бытовая техника"),
    FURNITURE("Мебель"),
    CLOTHING("Одежда"),
    FOOTWEAR("Обувь"),
    BOOKS("Книги"),
    TOYS("Игрушки"),
    SPORTS("Спорттовары"),
    BEAUTY("Косметика"),
    GROCERIES("Продукты"),
    PHARMACY("Аптека"),
    AUTO("Автотовары"),
    HOME_DECOR("Товары для дома"),
    JEWELRY("Ювелирные изделия"),
    PET_SUPPLIES("Зоотовары"),
    OFFICE_SUPPLIES("Канцтовары"),
    GARDEN("Сад и огород"),
    MUSIC("Музыкальные инструменты"),
    TRAVEL("Туризм"),
    BABY_PRODUCTS("Товары для детей"),
    DIY("Ремонт и строительство"),
    HEALTH("Здоровье"),
    GIFTS("Подарки"),
    HOBBIES("Хобби"),
    VIDEO_GAMES("Видеоигры"),
    MOVIES("Фильмы"),
    MUSIC_ALBUMS("Музыкальные альбомы"),
    TOOLS("Инструменты"),
    OFFICE_EQUIPMENT("Офисное оборудование"),
    PARTY_SUPPLIES("Товары для вечеринок"),
    LIGHTING("Освещение"),
    BATHROOM("Ванная"),
    KITCHEN("Кухня"),
    BEDROOM("Спальня"),
    LIVING_ROOM("Гостиная"),
    DINING_ROOM("Столовая"),
    STATIONERY("Канцелярские товары"),
    OUTDOOR("Уличные товары"),
    SECURITY("Охрана и безопасность"),
    CAMERAS("Камеры и фототовары"),
    LUGGAGE("Багаж"),
    WINE("Вино"),
    BEER("Пиво"),
    LIQUOR("Крепкие напитки"),
    TEA("Чай"),
    COFFEE("Кофе"),
    BAKERY("Выпечка"),
    FROZEN_FOODS("Замороженные продукты"),
    SNACKS("Закуски"),
    DAIRY("Молочные продукты"),
    MEAT("Мясо"),
    SEAFOOD("Морепродукты"),
    VEGETABLES("Овощи"),
    FRUITS("Фрукты"),
    CLEANING_SUPPLIES("Чистящие средства");

    Category(String rus) {
        ruValue = rus;
    }

    private final String ruValue;

    public String getRuValue() {
        return ruValue;
    }
}