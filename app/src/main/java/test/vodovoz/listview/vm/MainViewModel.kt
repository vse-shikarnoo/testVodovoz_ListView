package test.vodovoz.listview.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import test.vodovoz.listview.model.Category
import test.vodovoz.listview.model.Product
import test.vodovoz.listview.repository.MainRepository

class MainViewModel : ViewModel() {

    private val repository = MainRepository()

    private val categoriesLD = MutableLiveData<List<Category>>()
    private val currentCategoryLD = MutableLiveData<Category>()
    private val productsLD = MutableLiveData<List<Product>>()
    private val errorLD = MutableLiveData<Unit>()

    val categories: LiveData<List<Category>>
        get() = categoriesLD
    val currentCategory: LiveData<Category>
        get() = currentCategoryLD
    val products: LiveData<List<Product>>
        get() = productsLD
    val error: LiveData<Unit>
        get() = errorLD


    fun getCategories() {
        viewModelScope.launch {
            try {
                val result = repository.getCategories()
                categoriesLD.postValue(result)
            } catch (t: Throwable) {
                errorLD.postValue(Unit)
            }
        }
    }

    fun getProductsByCategory() {
        viewModelScope.launch {
            try {
                productsLD.postValue(repository.getProductsByCategory(currentCategory.value!!))
            } catch (t: Throwable) {
                errorLD.postValue(Unit)
            }
        }
    }

    fun setCurrentCategory(category: Category){
        categories.value?.forEach {
            if (it.name == category.name){
                currentCategoryLD.postValue(it)
                it.isCurrent = true
            }else{
                it.isCurrent = false
            }
        }
    }
}