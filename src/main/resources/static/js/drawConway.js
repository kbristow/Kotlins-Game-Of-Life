var grid = [];
var path;

function createGrid(){
    for (var i = 0; i < 20; i ++){
        grid[i] = [];
        for (var j = 0; j < 20; j ++){
            var rectangle = new paper.Rectangle(new paper.Point(5*i, 5*j), new paper.Size(5, 5));
            var path = new paper.Path.Rectangle(rectangle);

            path.fillColor = 'white';

            grid[i][j] = path;
        }
    }
}

function refreshGrid(){
    $.get( "/current", function(data) {
        for (var i = 0; i < 20; i ++){
            for (var j = 0; j < 20; j ++){
                if(data[i][j] === 1){
                    grid[i][j].fillColor = 'black';
                }
                else{
                    grid[i][j].fillColor = 'white';
                }
            }
        }
        paper.view.draw();
        setTimeout(function(){ refreshGrid(); }, 50);
    });
}

$(document).ready ( function(){

    var canvas = document.getElementById('myCanvas');
    // Create an empty project and a view for the canvas:
    paper.setup(canvas);

    createGrid();

    paper.view.draw();

    $.get( "/new", function() {
        refreshGrid();
    });
})