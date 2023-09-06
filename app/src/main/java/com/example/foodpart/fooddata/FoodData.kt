package com.example.foodpart.fooddata

data class FoodData(
    val foodName: String,
    val recipes: String,
    val id: Int,
    val difficulty: Difficulties,
    val cookingTime: String,
    val category: Categories,
    val subCategory: String,
    val meals: List<String>
)

enum class Categories(
    val category: String,
    val subCategories: List<String>?
) {
    MAIN(
        "غذای اصلی", listOf("خورشت", "سنتی", "ملل")
    ),
    SALAD(
        "سالاد", listOf("ایتالیایی", "سنتی", "ملل")
    ),
    SONATI(
        "سنتی", null
    )
}


enum class Difficulties(val difficulty: String) {

    HARD("دشوار"),
    MEDIUM("متوسط"),
    EASY("ساده")
}


val foodList = listOf<FoodData>(
    FoodData(
        "قیمه بادمجان",
        "مواد لازم:... ",
        1,
        Difficulties.MEDIUM,
        "۱ ساعت",
        Categories.MAIN,
        "خورشت",
        listOf("نهار", "صبحانه")
    ),
    FoodData(
        "کباب کوبیده",
        "مواد لازم:...",
        2,
        Difficulties.EASY,
        "۴۵ دقیقه",
        Categories.MAIN,
        "سنتی",
        listOf("صبحانه")
    ),
    FoodData(
        "فسنجان",
        "مواد لازم:...",
        3,
        Difficulties.HARD,
        "۲ ساعت",
        Categories.MAIN,
        "خورشت",
        listOf("نهار", "شام")
    ),
    FoodData(
        "آب‌پزخورشت",
        "مواد لازم:...",
        4,
        Difficulties.EASY,
        "۴۰ دقیقه",
        Categories.MAIN,
        "سنتی",
        listOf("نهار", "شام")
    ),
    FoodData(
        "کوکو سبزی",
        "مواد لازم:...",
        5,
        Difficulties.EASY,
        "۴۵ دقیقه",
        Categories.MAIN,
        "خورشت",
        listOf("صبحانه", "نهار")
    ),
    FoodData(
        "شوید باقالی",
        "مواد لازم:...",
        6,
        Difficulties.MEDIUM,
        "۷۰ دقیقه",
        Categories.MAIN,
        "سنتی",
        listOf("نهار")
    ),
    FoodData(
        "سیب‌زمینی خورشت",
        "مواد لازم:...",
        7,
        Difficulties.HARD,
        "۲ ساعت و ۳۰ دقیقه",
        Categories.SALAD,
        "خورشت",
        listOf("شام", "نهار")
    ),
    FoodData(
        "ماکارونی",
        "مواد لازم:...",
        8,
        Difficulties.EASY,
        "۳۰ دقیقه",
        Categories.SONATI,
        "ملل",
        listOf("شام", "نهار")
    ),
    FoodData(
        "خورشت قیمه",
        "مواد لازم:...",
        9,
        Difficulties.HARD,
        "۳ ساعت",
        Categories.MAIN,
        "خورشت",
        listOf("شام", "نهار")
    ),
    FoodData(
        "لوبیا پلو",
        "مواد لازم:...",
        10,
        Difficulties.MEDIUM,
        "۲ ساعت و ۳۰ دقیقه",
        Categories.MAIN,
        "خورشت",
        listOf("شام", "نهار")
    ),
    FoodData(
        "میرزاقاسمی",
        "مواد لازم:...",
        11,
        Difficulties.HARD,
        "۴ ساعت",
        Categories.MAIN,
        "خورشت",
        listOf("شام", "نهار")
    ),
    FoodData(
        "ته‌چین",
        "مواد لازم:...",
        12,
        Difficulties.EASY,
        "۵۰ دقیقه",
        Categories.SALAD,
        "خورشت",
        listOf("شام", "نهار")
    ),
    FoodData(
        "آش رشته",
        "مواد لازم:...",
        13,
        Difficulties.MEDIUM,
        "۱ ساعت و ۱۵ دقیقه",
        Categories.MAIN,
        "خورشت",
        listOf("شام", "نهار")
    ),
    FoodData(
        "کشک بادمجان",
        "مواد لازم:...",
        14,
        Difficulties.EASY,
        "۴۵ دقیقه",
        Categories.MAIN,
        "خورشت",
        listOf("شام", "نهار")
    ),
    FoodData(
        "سالاد اسفناج",
        "مواد لازم:...",
        15,
        Difficulties.EASY,
        "۲۰ دقیقه",
        Categories.SALAD,
        "خورشت",
        listOf("شام", "نهار")
    )
)
