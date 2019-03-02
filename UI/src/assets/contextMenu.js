var rgtClickContextMenu = document.getElementById("context-menu");

/** close the right click context menu on click */
document.onclick = function(e){
    rgtClickContextMenu.style.display = 'none';
}


document.oncontextmenu = function(e){
    //alert(e.target.id)
    var elmnt = e.target;
    if ( elmnt.className.startsWith ( "context-menu")) {
       e.preventDefault();

       rgtClickContextMenu.style.left = e.pageX + 'px';
       rgtClickContextMenu.style.top = e.pageY + 'px';
       rgtClickContextMenu.style.display = 'block';
    }
 }
 