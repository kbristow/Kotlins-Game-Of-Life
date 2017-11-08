package bristowk.kgol.models

class Grid(private val width: Int, private val height: Int) {

    private var grid: Array<Array<Int>> = Array(height, { _ ->
        Array(width, { _ -> 0 })
    })

    fun loadState(state: Map<Location, Int>) {
        for ((location, cellState) in state) {
            grid[location.x][location.y] = cellState
        }
    }

    fun nextGeneration(): Array<Array<Int>> {
        val newGrid: Array<Array<Int>> = Array(height, { _ ->
            Array(width, { _ -> 0 })
        })
        for (y in grid.indices) {
            for (x in grid[y].indices) {
                val neighbourCount = this.countNeighbours(Location(x,y))
                val currentValue = grid[y][x]

                val newValue = if(neighbourCount > 3){
                    0
                } else if(currentValue == 1 && neighbourCount >= 2){
                    1
                } else if(currentValue == 0 && neighbourCount == 3){
                    1
                } else{
                    0
                }

                newGrid[y][x] = newValue
            }
        }

        this.grid = newGrid

        return grid
    }

    private fun countNeighbours(location: Location): Int {
        var neighbourCount = 0
        for (x in location.x - 1..location.x+1 )
        {
            for(y in location.y-1..location.y+1){
                val isLocation: Boolean = x==location.x && y==location.y
                val xInBounds: Boolean = x in 0..(width - 1)
                val yInBounds: Boolean = y in 0..(height - 1)
                if (!isLocation && yInBounds && xInBounds){
                    if (this.grid[y][x] == 1){
                        neighbourCount ++
                    }
                }
            }
        }
        return neighbourCount
    }
}