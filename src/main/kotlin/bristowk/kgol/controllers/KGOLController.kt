package bristowk.kgol.controllers

import bristowk.kgol.models.Grid
import bristowk.kgol.models.Location
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Random
import kotlin.collections.HashMap

@RestController
class KGOLController {

    var currentGame = Grid(20,20)

    @GetMapping("/current")
    fun currentGame():Array<Array<Int>>{
        return currentGame.nextGeneration()
    }

    @GetMapping("/new")
    fun newGame(){
        currentGame = Grid(20,20)
        val initialValues: HashMap<Location, Int> = HashMap()
        val random = Random()
        for (x in 0..19){
            for(y in 0..19){
                initialValues.put(Location(x = x, y = y), random.nextInt(2))
            }
        }
        currentGame.loadState(initialValues)
    }

}