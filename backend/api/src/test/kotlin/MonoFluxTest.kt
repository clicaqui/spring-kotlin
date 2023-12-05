import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import kotlin.test.assertEquals

open class MonoFluxTest {


    @Test
    fun testMono() {
        val myMap = hashMapOf(1 to "one", 7 to "seven", 83 to "eithty-three")
        val s = "wow"
        //val monoString = Mono.just(s).block()

        val hash = Flux.just(myMap).blockLast()
        val list = listOf(2.until(8))
        for ((index, element) in list.withIndex()) {
            println("$index $element")

        }


        println(list.javaClass)
        if (hash != null) {
            assertEquals(1, list.size)
        }


    }


    @Test
    fun testFlux() {
        val monoString = Flux.just("hello", "brow", "whats", "happenig")
            .concatWithValues("cloud")
            .concatWith(Flux.error(Throwable("Error - On Flux")))
            .log()
        monoString.subscribe()

    }

}