var canvas = document.getElementById("myCanvas");
var context = canvas.getContext("2d");
var click = [-1,-1];
canvas.onmousedown=function(evt){
    paint=true;
    defineImage(evt);
};
canvas.onmousemove=function(evt){
    if (paint){
        defineImage(evt);
    }
};
canvas.onmouseup=function(evt){
    stopDrawing();
};
canvas.onmouseleave=function(evt){
    stopDrawing();
};
var clearButton = document.getElementById("clear");
clearButton.addEventListener("click",clearBoard,false);
var save = document.getElementById("save");
save.onclick = function(){
    download(canvas, "tablero.png");
}

//Funcion que toma las coordenadas x y y
function getCurrentPos(evt) {
    var rect = canvas.getBoundingClientRect();
    return {
        x: evt.clientX - rect.left,
        y: evt.clientY - rect.top
    };
}

//Funcion para tomar el color y la forma desde el formulario HTML5
function defineImage(evt) {
    var currentPos = getCurrentPos(evt);

    for (i = 0; i < document.inputForm.color.length; i++) {
        if (document.inputForm.color[i].checked) {
            var color = document.inputForm.color[i];
            break;
        }
    }
   
    var json = JSON.stringify({
        //"shape": shape.value,
        "color": color.value,
        "clear": false,
        "coords": {
            "x": currentPos.x,
            "y": currentPos.y
        }

    });
    drawImageText(json);
    sendText(json);
}

function clearBoard(evt) {
    context.beginPath();
    context.clearRect(0,0,canvas.width,canvas.height);
    var color = document.inputForm.color[i];
    var currentPos = getCurrentPos(evt);
    
    var json = JSON.stringify({
        //"shape": shape.value,
        "color": color.value,
        "clear": true,
        "coords": {
            "x": currentPos.x,
            "y": currentPos.y
        }

    });
    sendText(json);
    
}

function drawImageText(image,external) {
    var json = JSON.parse(image);
    if (json.stop===true) {
        click[0]=-1;
        click[1]=-1;
    }
    else{
        context.strokeStyle= json.color;
        context.lineWidth=4;
        context.beginPath();
            if (click[0]===-1&&click[1]===-1) {
                context.moveTo(json.coords.x-2,json.coords.y-2);
                context.lineTo(json.coords.x+2,json.coords.y+2);
            }
            else{
                context.moveTo(click[0],click[1]);
                context.lineTo(json.coords.x,json.coords.y);
            }
            click[0]=json.coords.x;
            click[1]=json.coords.y; 
            if (click[0]===-1&&click[1]===-1) {
                context.moveTo(json.coords.x-2,json.coords.y-2);
                context.lineTo(json.coords.x+2,json.coords.y+2);
            }
            else{
                context.moveTo(click[0],click[1]);
                context.lineTo(json.coords.x,json.coords.y);
            }
            click[0]=json.coords.x;
            click[1]=json.coords.y; 
        context.closePath();
        context.stroke();
        if (json.clear) {
            context.clearRect(0,0,canvas.width,canvas.height);
        }
    }

}

function download(canvas, filename) {
  var lnk = document.createElement('a'), e;

  lnk.download = filename;

  lnk.href = canvas.toDataURL("image/png;base64");

  if (document.createEvent) {
    e = document.createEvent("MouseEvents");
    e.initMouseEvent("click", true, true, window,
                     0, 0, 0, 0, 0, false, false, false,
                     false, 0, null);

    lnk.dispatchEvent(e);
  } else if (lnk.fireEvent) {
    lnk.fireEvent("onclick");
  }
}

function stopDrawing(){
    click[0]=-1;
    click[1]=-1;
    paint=false;
    var json = JSON.stringify({
        "stop":true
    });
    sendText(json);
}