package test.vodovoz.listview

/*
    В реепозитории каждый раз делаю запрос в сеть, имтируя нормальную работу с сервером и JSON
 */
class MainRepository {

    private val networkService = NetworkService.create()

    suspend fun getCategories(): List<Category> {
        return networkService.getProducts().tovary
    }

    suspend fun getProductsByCategory(category: Category): List<Product> {
        networkService.getProducts().tovary.forEach {
            if(it.name == category.name){
                return it.productsData
            }
        }
        return emptyList()
    }
}