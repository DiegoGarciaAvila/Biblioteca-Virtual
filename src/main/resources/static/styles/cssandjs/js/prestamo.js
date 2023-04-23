const tblPres= document.querySelector("#tablaPrs tbody");
const jsonCont=JSON.parse(window.localStorage.getItem("libro"));
let libros=[];
maqueTabla();
eventos();
function eventos(){
    tblPres.addEventListener('click',eliminarlibro);
    document.addEventListener('DOMContentLoaded',()=>{
         libros=JSON.parse(localStorage.getItem('libro')) || [];
     });
}

//crea la tabla con los datos del libro
function maqueTabla(){
jsonCont.forEach((libro,index)=>{
    const row=document.createElement("tr");
    row.innerHTML=`
        <td>${index+1}</td>
        <td>${libro.nombre}</td>
        <td>${libro.autor}</td>
        <td>${libro.editorial}</td>
        <td><a href="#" class="btn btn-danger eliminar" data-id="${libro.id}">X</td>
    `
    tblPres.appendChild(row);
});

}

function eliminarlibro(e){
    console.log("Aqui mero");
     if(e.target.classList.contains('eliminar')){
         const libroId=e.target.getAttribute('data-id');
         console.log(libroId+"-------------------------------");
         libros=libros.filter(libro => libro.id!==libroId);
         console.log(libros);
          sincStorage();
          location.reload();

         //limpiar(); Falta actualizar la pantalla

     }
}
//envía la info si se clickea el botón
$("#btnresp").on("click",function(){

 ajaxPro();

});

//envia la info al back  /regis/grdrTodo
function ajaxPro(){

    if(Array.isArray(libros)&&libros.length===0){
    console.log("Entro en if");
        Swal.fire('No tiene ningun libro asignado')
    }else{


    $.ajax({
        type:"GET",
        beforeSend: function(  ) {
                   let timerInterval
                   Swal.fire({
                     title: 'Cargando peticion',
                     html: ' ',
                     timer: 1000,
                     timerProgressBar: true,
                     didOpen: () => {
                       Swal.showLoading()
                       const b = Swal.getHtmlContainer().querySelector('b')
                       timerInterval = setInterval(() => {
                         b.textContent = Swal.getTimerLeft()
                       }, 1)
                     },
                     willClose: () => {
                       clearInterval(timerInterval)
                     }
                   }).then((result) => {
                     if (result.dismiss === Swal.DismissReason.timer) {
                       console.log('Cargando')
                     }
                   })
        },
        url:"/regis/grdrTodo",
        data:encodeURIComponent(JSON.stringify(jsonCont)),
        success:function(){
            Swal.fire({
                icon: 'success',
                title: 'Prestamo realizado!',
                html: '<h5>Acude a Integracion Familiar por tus libros.<br>Tienes dos dias para acudir por ellos de lo contrario tendras que hacer nuevamente la solicitud.</h5>',
            }).then((result)=>{
                window.localStorage.clear();
                window.location.replace("/Historial/Prestamos.xhtm");
            })
        }
    });
    }
}
 //limpia de el carrito
 function limpiar(){
     while(tblPres.firstChild){
         tblPres.removeChild(tblPres.firstChild);
     }
 }

  function sincStorage(){
      localStorage.setItem('libro',JSON.stringify(libros));
  }