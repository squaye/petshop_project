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
    ShopItem(1, 500.0, "Dog", "Labrador Retriever", "Pet", "https://images.dog.ceo/breeds/frise-bichon/3.jpg", false),
    ShopItem(2, 400.0, "Dog", "Golden Retriever", "Pet", "https://images.dog.ceo/breeds/terrier-wheaten/n02098105_2297.jpg", false),
    ShopItem(3, 700.0, "Dog", "Poodle", "Pet", "https://images.dog.ceo/breeds/coonhound/n02089078_3355.jpg", false),
    ShopItem(4, 400.0, "Dog", "Bulldog", "Pet", "https://images.dog.ceo/breeds/mastiff-english/1.jpg", false),
    ShopItem(5, 300.0, "Cat", "Siamese", "Pet", "https://cataas.com/cat", false),
    ShopItem(6, 300.0, "Cat", "Persian", "Pet", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/68/Orange_tabby_cat_sitting_on_fallen_leaves-Hisashi-01A.jpg/224px-Orange_tabby_cat_sitting_on_fallen_leaves-Hisashi-01A.jpg", false),
    ShopItem(7, 400.0, "Food", "Dog Food", "Accessory", "https://www.hillspet.com/content/dam/cp-sites/hills/hills-pet/global/science-diet-redesign/dog/sd-pack-dog-age.png.rendition.818.500.png", false),
    ShopItem(8, 200.0, "Food", "Cat Food", "Accessory", "https://s30379.pcdn.co/wp-content/uploads/2019/09/p1cg009h6sh001bi41kfkftt1prr6.jpg", false),
    ShopItem(9, 400.0, "Device", "Dog Chain", "Accessory", "https://m.media-amazon.com/images/I/71kKPEt4BLL._SY450_.jpg", false),
    ShopItem(10, 400.0, "Cat", "Maine Coon", "Pet", "https://cataas.com/cat", false),
)