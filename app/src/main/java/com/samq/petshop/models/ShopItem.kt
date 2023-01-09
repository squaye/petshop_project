import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ShopItem(
    val id: Int,
    val price: Double,
    val type: String,
    val name: String,
    val itemType: String,
    val imageUrl: String,
    var initialSelected: Boolean){
    var selected by mutableStateOf(initialSelected)
}

val itemList = listOf(
    ShopItem(1, 400.0, "Dog", "Labrador Retriever", "Pet", "https://images.dog.ceo/breeds/frise-bichon/3.jpg", false),
    ShopItem(2, 400.0, "Dog", "Golden Retriever", "Pet", "https://images.dog.ceo/breeds/terrier-wheaten/n02098105_2297.jpg", false),
    ShopItem(3, 400.0, "Dog", "Poodle", "Pet", "https://images.dog.ceo/breeds/coonhound/n02089078_3355.jpg", false),
    ShopItem(4, 400.0, "Dog", "Bulldog", "Pet", "https://images.dog.ceo/breeds/mastiff-english/1.jpg", false),
    ShopItem(5, 400.0, "Cat", "Siamese", "Pet", "https://images.dog.ceo/breeds/terrier-irish/n02093991_4052.jpg", false),
    ShopItem(6, 400.0, "Cat", "Persian", "Pet", "https://images.dog.ceo/breeds/labrador/n02099712_6644.jpg", false),
    ShopItem(7, 400.0, "Cat", "Maine Coon", "Pet", "https://images.dog.ceo/breeds/papillon/n02086910_2994.jpg", false),
)