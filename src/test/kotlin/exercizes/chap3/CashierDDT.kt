package exercizes.chap3

import com.ubertob.pesticide.core.DDT
import com.ubertob.pesticide.core.DdtActions
import com.ubertob.pesticide.core.DdtProtocol
import com.ubertob.pesticide.core.DomainDrivenTest

enum class Item { carrot, milk }

interface SelfCheckoutActions : DdtActions<DdtProtocol> {
    fun setupPrices(prices: Map<Item, Double>)
    fun setup3x2(item: Item)
    fun addItem(actorName: String, qty: Int, item: Item)
    fun totalFor(actorName: String): Double
}

//class SelfCheckoutDDT : DomainDrivenTest<SelfCheckoutActions>(allActions) {
//    val customer by NamedActor(::Customer)

//    @DDT
//    fun `customer can benefit from 3x2 offer`() = ddtScenario {
//        val prices = mapOf(Item.carrot to 2.0, Item.milk to 5.0)
//
//        setUp {
//            setupPrices(prices)
//            setup3x2(Item.milk)  // 우유에 대해 3x2 할인 적용
//        }.thenPlay(
//            customer.`adds #qty #item`(3, Item.carrot),
//            customer.`adds #qty #item`(3, Item.milk),
//            customer.`checks total is #total`(16.0)  // (2 * 3) + (5 * 2) = 16.0
//        )
//    }
//}