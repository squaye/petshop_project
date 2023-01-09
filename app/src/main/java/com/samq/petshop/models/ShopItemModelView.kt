import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class ShopItemViewModel: ViewModel() {
    private val _items = itemList.toMutableStateList()
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
}
