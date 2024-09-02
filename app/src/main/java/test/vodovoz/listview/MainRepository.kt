package test.vodovoz.listview

class MainRepository {

    suspend fun getCategories(): List<Category> {
        return emptyList()
    }

    suspend fun getProductsByCategory(category: Category): List<Product> {
        return emptyList()
    }
}