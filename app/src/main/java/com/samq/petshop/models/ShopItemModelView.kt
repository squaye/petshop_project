import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.samq.petshop.models.Order
import java.util.*

class ShopItemViewModel: ViewModel() {
    private val _items = itemList.toMutableStateList()
    private val _order by mutableStateOf(Order())
    val order: Order
        get() {
           return _order
        }
    val items: List<ShopItem>
        get() = _items

    val selectedItems: List<ShopItem> get() = _items.filter { it.selected }

    fun remove(item: ShopItem) {
//        _items.remove(item)
        item.selected=false
    }

    fun selectItem(item: ShopItem, checked: Boolean) =
        items.find { it.id == item.id }?.let { shopItem ->
            shopItem.selected = checked
        }

    fun clear(){
        order.id=0;
        order.name=""
        order.cardNumber=""
        order.email=""
        order.phone=""
        order.date= Date()
        for(item: ShopItem in items){
            item.selected=false
        }
    }
}
