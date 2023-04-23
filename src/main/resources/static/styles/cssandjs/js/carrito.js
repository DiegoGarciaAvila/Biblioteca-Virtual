 'use strict';

 const carrito= document.querySelector('#liCarrito');
 const listaLibro=document.querySelector('#listaLibros');
 const contenedorCarrito=document.querySelector('#tablaCarrito tbody');
 const vaciarCarrito=document.querySelector('#vaciarCarrito');
 let libros=[];
 let bandera=false;

//window.onresize = function(){
// anchoVentana = window.innerWidth;
//
// if(anchoVentana > 1000){
//    console.log(anchoVentana)
//    eventos();
// }else if(anchoVentana > 750 && anchoVentana < 1000){
//    console.log(anchoVentana)
//    eventos();
// }else{
//    eventos();
//    console.log(anchoVentana)
// }

// }; document.getElementById('prest').click()
eventos();

 //eventos principales del carrito
 function eventos(){

     listaLibro.addEventListener('click', agregarLibro);

     carrito.addEventListener('click',eliminarlibro);
     document.addEventListener('DOMContentLoaded',()=>{
         libros=JSON.parse(localStorage.getItem('libro')) || [];
         carritohtml();
     });
     vaciarCarrito.addEventListener('click',()=>{
         libros=[];
         limpiar();
         carritohtml();
     });
 }

 const vaciarCarritoAlerta = () => {
 Swal.fire('Carrito vacio')
 }

 //agrega el libro
 function agregarLibro(e){
     if(e.target.classList.contains('agregarCarrito')){
         const dato=e.target.parentElement.parentElement;
         datoLibro(dato);
     }
 }
 //eliminar libro del carrito
 function eliminarlibro(e){

     if(e.target.classList.contains('eliminar')){
         const libroId=e.target.getAttribute('data-id');
         libros=libros.filter(libro => libro.id!==libroId);
         carritohtml();
     }
 }
 //construye el objeto
 function datoLibro(dato){
     const infoLibro={
         nombre: dato.querySelector('h4').textContent,
         id: dato.querySelector('button').getAttribute('data-id'),
         cantidad: 1,
         autor:dato.querySelector('span').textContent,
         editorial:dato.querySelector("strong").textContent
     }
     const existe=libros.some(dato=> dato.id===infoLibro.id);
     if(existe==true){
         const librocar=libros.map(dato=>{
             if(dato.id===infoLibro.id){
                 Swal.fire({
                   icon: 'error',
                   title: 'Oops...',
                   text: 'Solo se puede seleccionar una vez!'
                 })
                 return dato;
             }else{
                 return dato;
             }
         });
         libros=[...librocar];
     }else{
         libros=[...libros,infoLibro];
         Swal.fire({
           position: 'top-center',
           icon: 'success',
           title: 'Libro Agregado al carrito',
           showConfirmButton: false,
           timer: 2500
         })
     location.reload();
     }


     carritohtml();
 }

 //agrega y mapea en el carrito-----------------------------------------------CARRITO--------------
 function carritohtml(){

     limpiar();
     if(libros.length>=nlibros){
         Swal.fire({
            icon: 'info',
           title: 'Solo puedes visualizar los libros',
         })
         $(".estado").prop("disabled",true);
         libros.splice(nlibros,10);
         libros.forEach(libro => {
             const { nombre, id } = libro;
             const row = document.createElement('tr');
             row.innerHTML = `
                 <td>${nombre}</td>
                 <td><a href="#" class="btn btn-danger eliminar" data-id="${id}">X</td>
             `;
             contenedorCarrito.appendChild(row);
         })
     }else{
         if(nlibros<=0){
             Swal.fire({
                 icon: 'info',
                 title: 'Solo puedes visualizar los libros!'
             })
             $(".estado").prop("disabled",true);
         }else{
             $(".estado").prop("disabled",false);
         }
         libros.splice(nlibros,10)
         libros.forEach(libro => {
             const { nombre, id } = libro;
             const row = document.createElement('tr');
             row.innerHTML = `
                 <td>${nombre}</td>
                 <td><a href="#" class="btn btn-danger eliminar" onclick="eliminarlibroB(${id})" data-id="${id}">X</a></td>
             `;
             contenedorCarrito.appendChild(row);
         })
     }

     if(Array.isArray(libros)&&libros.length===0){
        let btnpres=document.getElementById("prest");
        btnpres.href='#';

        $("#prest").on("click",function(){

         if(Array.isArray(libros)&&libros.length===0){
                    Swal.fire('Necesita agregar un libro')
                }
        })

     }else{

        if(bandera=true){
         if(nlibros<=0){
            Swal.fire({
                icon: 'warning',
                title: 'No puedes realizar esta acción!',
                text: 'Debes regresar el/los libro(s) para realizar otro préstamo!'
            }).then((result)=>{
                window.localStorage.clear();
                location.reload();
            })
         }else{
            var btnpres=document.getElementById("prest");
            btnpres.href="/Libros/AcervoBibliografico.xhtm/prestamo";
         }
        }else{
         $("#prest").on("click",function(){
            if(nlibros<=0){
                Swal.fire({
                    icon: 'warning',
                    title: 'No puedes realizar esta acción!',
                    text: 'Debes regresar el/los libro(s) para realizar otro préstamo!'
                }).then((result)=>{
                    window.localStorage.clear();
                    location.reload();
                })
            }else{
                var btnpres=document.getElementById("prest");
                btnpres.href="/Libros/AcervoBibliografico.xhtm/prestamo";
            }
            })
        }
     }

     sincStorage();
 }
 //almacena los datos en localstorage
 function sincStorage(){
     localStorage.setItem('libro',JSON.stringify(libros));
 }
 //limpia de el carrito
 function limpiar(){
     while(contenedorCarrito.firstChild){
         contenedorCarrito.removeChild(contenedorCarrito.firstChild);
     }
 }


 function probando(){

    bandera=true;
    if(bandera){
    console.log("aaab")
        if(nlibros<=0){
            alert("c");
            Swal.fire({
                icon: 'warning',
                title: 'No puedes realizar esta acción!',
                text: 'Debes regresar el/los libro(s) para realizar otro préstamo!'
            }).then((result)=>{
                window.localStorage.clear();
            })
            }else{
              alert("d");
            // var btnpres=document.getElementById("prest");
            // document.getElementById("prest").href = "/Libros/AcervoBibliografico.xhtm/prestamo";
              //alert(document.getElementById("prest").href);
              alert("location.href");
              location.href ='/Libros/AcervoBibliografico.xhtm/prestamo';
                            alert("location.href");

             // alert( document.getElementById("prest").href);
              // window.location="/Libros/AcervoBibliografico.xhtm/prestamo";
       }
    }
 }

 function vaciarCarritoAlertaB(){

     Swal.fire('Carrito vacio')
     //alert(libros);
     libros=[];
     //alert(libros);
     limpiar();
     carritohtml();
     location.reload();
 }

 function carritohtmlB(){
    bandera=true;
    if(bandera){
    carritohtml();
//        if(Array.isArray(libros)&&libros.length===0){
//            let btnpres=document.getElementById("prest");
//           // alert("a");
//            btnpres.href='#';
//            if(Array.isArray(libros)&&libros.length===0){
//                Swal.fire('Necesita agregar un libro')
//            }
//        }else{
//         //   alert("b");
//            if(nlibros<=0){
//                Swal.fire({
//                    icon: 'warning',
//                    title: 'No puedes realizar esta acción!',
//                    text: 'Debes regresar el/los libro(s) para realizar otro préstamo!'
//                }).then((result)=>{
//                    window.localStorage.clear();
//
//                })
//                }else{
//                     //alert("location.href");
//
//                     console.log("location.href");
//                     location.href ='/Libros/AcervoBibliografico.xhtm/prestamo';
//                     alert("location.href");
//            }
//        }
    }
 }

   //eliminar libro del carrito
 function eliminarlibroB(e){
    const libroId=e+"";

    libros = libros.filter(function(libros) {
    return libros.id !== libroId;
    });

    location.reload();
    carritohtml();

 }