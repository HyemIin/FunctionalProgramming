import org.eclipse.jetty.http.HttpTester.parseResponse
import org.http4k.client.JettyClient
import org.http4k.core.*
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes
import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class SeeATodoListAT {

//@Test
//fun 'List owners can see their lists'() {
//        val user = "frank"
//        val listname = "shopping"
//        val foodToBuy = listOf("carrots","apples","milk")
//        startTheApplication(user,listname,foodToBuy)
//        val list = getToDoList(user,listname)
//        expectThat(list.listName.name).isEqualTo(listname)
//        expectThat(list.items.map{it.description}).isEqualTo(foodToBuy)
//    }
//}
//
//fun getToDoList(user: String, listname: String): ToDoList {
//    val client = JettyClient()
//    val request = Request(Method.GET,
//        "http://localhost:9090/todo/$user/$listname")
//    val response = client(request)
//    return if (response.status == Status.OK) {
//        parseResponse(response)
//    } else {
//        fail(response.toMessage())
//    }
//}
//
//fun parseResponse(html: String): ToDoList = TODO("parse the response")
//fun startTheApplication(user: String, listname: String, items: List<String>) {
//    val server = Zettai().asServer(Jetty(9090)).start()
}
