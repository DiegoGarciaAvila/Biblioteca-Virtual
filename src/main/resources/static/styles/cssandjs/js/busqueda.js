//lee lo que el usr está escribiendo y lo mapea
$('#searchBox').on('keyup', function(){
    var valor=$(this).val()
    var data=searchBook(valor,libro)
    bldCard(data)
    if(nlibros<=0){
        $(".estado").prop("disabled",true);
    }
})

$('#searchBox1').on('keyup', function(){
    var valor=$(this).val()
    var data=searchBook(valor,libro)
    bldCard(data)
    if(nlibros<=0){
        $(".estado").prop("disabled",true);
    }
})

bldCard(libro);
//verifica si hay coincidencia con algún libro
function  searchBook(valor,data){
 var filtroData=[]
 for(var i=0; i < data.length; i++){
    valor=valor.toLowerCase();
    var libr=data[i].ltitlibro.toLowerCase();
    var laut=data[i].lautor.toLowerCase();
    if(libr.includes(valor) || laut.includes(valor)){
        filtroData.push(data[i])
    }
 }
 return filtroData
}
//modela todas las card del acervo
function bldCard(data){
    var card= document.getElementById("card");
    card.innerHTML="";
    for(var i=0; i < data.length; i++){
        if(data[i].catstslibro.ccvestslibr===1){
            var info_card=`
                <div class="col-md-4 ">
                    <div class="member rounded text-dark p-4">
                        <h4 class="text-dark">${data[i].ltitlibro}</h4><br>
                        <p class="text-justify">${data[i].lresena}</p>
                        <p class="edito">Editorial: <strong>${data[i].leditorial}</strong> <br> Género: ${data[i].genLiterario.gdesgen}</p>
                        <label>Autor: </label><br>
                        <span class="text-dark">${data[i].lautor}</span>
                        <div>
                            <button type="button" data-id="${data[i].lcvelibro}"
                                class="btn btn-info agregarCarrito estado">Agregar al carrito
                            </button> <br>

                        <!--<a class="btn btn-link"
                            href="AcervoBibliografico.xhtm/resena/${data[i].lcvelibro}">Leer reseña Completa</a>-->
                        </div>
                    </div>
                </div>`
            card.innerHTML+=info_card;
        }else{
                    var info_card=`
                        <div class="col-md-4 ">
                            <div class="member rounded text-dark p-4">
                                <h4 class="text-dark">${data[i].ltitlibro}</h4><br>
                                <p class="text-justify">${data[i].lresena}</p>
                                <p class="edito">Editorial: <strong>${data[i].leditorial}</strong> <br> Género: ${data[i].genLiterario.gdesgen}</p>
                                <label>Autor: </label><br>
                                <span class="text-dark">${data[i].lautor}</span>
                                <div>
                                    <div class="alert alert-danger" role="alert">
                                      Libro en prestamo
                                    </div>
                                    <!--<button type="button" data-id="${data[i].lcvelibro}"
                                        class="btn btn-info agregarCarrito estado">Agregar al carrito
                                    </button> <br>-->

                                <!--<a class="btn btn-link"
                                    href="AcervoBibliografico.xhtm/resena/${data[i].lcvelibro}">Leer reseña Completa</a>-->
                                </div>
                            </div>
                        </div>`
                    card.innerHTML+=info_card;
        }

    }
}