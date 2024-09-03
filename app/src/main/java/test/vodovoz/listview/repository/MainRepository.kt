package test.vodovoz.listview.repository

import android.util.Log
import test.vodovoz.listview.model.Category
import test.vodovoz.listview.remote.NetworkService
import test.vodovoz.listview.model.Product

class MainRepository {

    private val networkService = NetworkService.create()
    private lateinit var categories: List<Category>

    suspend fun getCategories(): List<Category> {
        categories = networkService.getProducts().tovary
        return categories
    }

    suspend fun getProductsByCategory(category: Category): List<Product> {
        categories.forEach {
            if(it.name == category.name){
                return it.productsData
            }
        }
        return emptyList()
    }
}