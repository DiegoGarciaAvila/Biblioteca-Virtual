
var current_fs, next_fs, previous_fs; //fieldsets
var left, opacity, scale; //fieldset properties which we will animate
var animating; //flag to prevent quick multi-click glitches
const eEdad=document.getElementById("eEdad");
const eCorreo=document.getElementById("eCorreo");
const eDomiP=document.getElementById("eDomiP");
const eTeleP=document.getElementById("eteleP");
const eGenero=document.getElementById("eGenero");
const eDomiU=document.getElementById("eDomcilioU");
const eTelU=document.getElementById("eTelefonoU");
const eFile=document.getElementById("eFile");
const eFiledom=document.getElementById("dFile");

//validacion de los demás campos
var datosServ=[];
$(".next").click(validardatosP)

function validardatosP(){
    const regexCorreo=/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
    const correo=document.getElementById("correoE").value;
    if(correo==''){
        eCorreo.innerHTML='';
        const p=document.createElement('p');
        p.style.color="red";
        p.textContent="Llenar campo correo electronico";
        eCorreo.appendChild(p);
    }else if(!correo.match(regexCorreo)){
        eCorreo.innerHTML="";
        const p=document.createElement("p");
        p.style.color="red";
        p.textContent="El correo no es correcto";
        eCorreo.appendChild(p);
    }else{
        eCorreo.innerHTML="";
        var rCorreo=correo;
    }

    const regexDomi=/^[^$%&|<>#]*$/;
    const domP=document.getElementById("domicilioP").value;
    if(domP==""){
        eDomiP.innerHTML="";
        const p=document.createElement("p");
        p.style.color="red";
        p.textContent="Llenar campo domicilio";
        eDomiP.appendChild(p);
    }else if(!domP.match(regexDomi)){
        eDomiP.innerHTML="";
        const p=document.createElement("p");
        p.style.color="red";
        p.textContent="No coincide";
        eDomiP.appendChild(p);
    }else{
        eDomiP.innerHTML="";
        var rDom=domP;
    }

    const regexTel = /^(\(\+?\d{2,3}\)[\*|\s|\-|\.]?(([\d][\*|\s|\-|\.]?){6})(([\d][\s|\-|\.]?){2})?|(\+?[\d][\s|\-|\.]?){8}(([\d][\s|\-|\.]?){2}(([\d][\s|\-|\.]?){2})?)?)$/;
    const teleP=document.getElementById("telefonoP").value;
    if(teleP==""){
        eTeleP.innerHTML="";
        const p=document.createElement("p");
        p.style.color="red";
        p.textContent="Llenar campo Teléfono";
        eTeleP.appendChild(p);
    }else if(!teleP.match(regexTel)){
        eTeleP.innerHTML="";
        const p=document.createElement("p");
        p.style.color="red";
        p.textContent="Te excediste o faltan números";
        eTeleP.appendChild(p);
    }else{
        eTeleP.innerHTML="";
        var rtelefoP=teleP;
    }

    if(!document.querySelector("input[name='genero']:checked")){
        eGenero.innerHTML="";
        const p=document.createElement("p");
        p.style.color="red";
        p.textContent="Seleccionar un género";
        eGenero.appendChild(p);
    }else{
        eGenero.innerHTML="";
        var rgenero=document.querySelector("input[name='genero']:checked").value;
    }

    var fileine=document.getElementById("fileIne");
    if(fileine.files.length==0){
        eFile.innerHTML="";
        const p=document.createElement("p");
        p.style.color="red";
        p.textContent="Falta subir el archivo";
        eFile.appendChild(p);
    }else{
        eFile.innerHTML="";
    }

    var filedom=document.getElementById("uacomdom");
    if(filedom.files.length==0){
        eFiledom.innerHTML="";
        const p=document.createElement("p");
        p.style.color="red";
        p.textContent="Falta subir el archivo";
        eFiledom.appendChild(p);
    }else{
        eFiledom.innerHTML="";
    }

    if(rCorreo!=null && rDom!=null && rtelefoP!=null && rgenero!=null /*&& (rArcPDF!=null || rArcPDF!="")*/){
        window.localStorage.clear();
        datosServ=[];
        const datosP={

            correoE:rCorreo,
            domicilioP:rDom,
            telefonoP:rtelefoP,
            genero:rgenero,
        };
        datosServ.push(datosP);
        if(animating) return false;
            animating = true;
            current_fs = $(this).parent();
            next_fs = $(this).parent().next();

            //activate next step on progressbar using the index of next_fs
            $("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");

            //show the next fieldset
            next_fs.show();
            //hide the current fieldset with style
            current_fs.animate({opacity: 0}, {
                step: function(now, mx) {
                //as the opacity of current_fs reduces to 0 - stored in "now"
                //1. scale current_fs down to 80%
                scale = 1 - (1 - now) * 0.2;
                //2. bring next_fs from the right(50%)
                left = (now * 50)+"%";
                //3. increase opacity of next_fs to 1 as it moves in
                opacity = 1 - now;
                current_fs.css({
                    'transform': 'scale('+scale+')',
                    'position': 'absolute'
                    });
                next_fs.css({'left': left, 'opacity': opacity});
                },
                duration: 800,
                complete: function(){
                    current_fs.hide();
                    animating = false;
                },
            //this comes from the custom easing plugin
            easing: 'easeInOutBack'
            });
    }
    localStorage.setItem("datosServ",JSON.stringify(datosServ));
}

$(".next1").click(datosUA);

function datosUA(){
    const regexDomiU=/^[^$%&|<>#]*$/;
    const domicilioU=document.getElementById("domcilioU").value;
    if(domicilioU==""){
        eDomiU.innerHTML="";
        const p=document.createElement("p");
        p.style.color="red";
        p.textContent="Llenar campo domicilio";
        eDomiU.appendChild(p);
    }else if(!domicilioU.match(regexDomiU)){
        eDomiU.innerHTML="";
        const p=document.createElement("p");
        p.style.color="red";
        p.textContent="No coincide";
        eDomiU.appendChild(p);
    }else{
        eDomiU.innerHTML="";
        var rDomU=domicilioU;
    }


    const regexTelU = /^(\(\+?\d{2,3}\)[\*|\s|\-|\.]?(([\d][\*|\s|\-|\.]?){6})(([\d][\s|\-|\.]?){2})?|(\+?[\d][\s|\-|\.]?){8}(([\d][\s|\-|\.]?){2}(([\d][\s|\-|\.]?){2})?)?)$/;
    const teleU=document.getElementById("telefonoU").value;
    if(teleU==""){
        eTelU.innerHTML="";
        const p=document.createElement("p");
        p.style.color="red";
        p.textContent="Llenar campo Teléfono";
        eTelU.appendChild(p);
    }else if(!teleU.match(regexTelU)){
        eTelU.innerHTML="";
        const p=document.createElement("p");
        p.style.color="red";
        p.textContent="Te excediste o faltan números";
        eTelU.appendChild(p);
    }else{
        eTelU.innerHTML="";
        var rtelefoU=teleU;
    }

    if(rDomU!=null && rtelefoU!=null){
        const datosU={
            domicilioU:rDomU,
            telefonoU:rtelefoU
        }
    datosServ.push(datosU);

    if(animating) return false;
    animating = true;
    current_fs = $(this).parent();
    next_fs = $(this).parent().next();

    //activate next step on progressbar using the index of next_fs
    $("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");

    //show the next fieldset
    next_fs.show();
    //hide the current fieldset with style
    current_fs.animate({opacity: 0}, {
        step: function(now, mx) {
            //as the opacity of current_fs reduces to 0 - stored in "now"
            //1. scale current_fs down to 80%
            scale = 1 - (1 - now) * 0.2;
            //2. bring next_fs from the right(50%)
            left = (now * 50)+"%";
            //3. increase opacity of next_fs to 1 as it moves in
            opacity = 1 - now;
            current_fs.css({
                'transform': 'scale('+scale+')',
                'position': 'absolute'
            });
            next_fs.css({'left': left, 'opacity': opacity});
        },
        duration: 800,
        complete: function(){
            current_fs.hide();
            animating = false;
        },
        //this comes from the custom easing plugin
        easing: 'easeInOutBack'
    });
    }
    localStorage.setItem("datosServ",JSON.stringify(datosServ));
}


$(".previous").click(function(){
	if(animating) return false;
	animating = true;

	current_fs = $(this).parent();
	previous_fs = $(this).parent().prev();

	//de-activate current step on progressbar
	$("#progressbar li").eq($("fieldset").index(current_fs)).removeClass("active");

	//show the previous fieldset
	previous_fs.show();
	//hide the current fieldset with style
	current_fs.animate({opacity: 0}, {
		step: function(now, mx) {
			//as the opacity of current_fs reduces to 0 - stored in "now"
			//1. scale previous_fs from 80% to 100%
			scale = 0.8 + (1 - now) * 0.2;
			//2. take current_fs to the right(50%) - from 0%
			left = ((1-now) * 50)+"%";
			//3. increase opacity of previous_fs to 1 as it moves in
			opacity = 1 - now;
			current_fs.css({'left': left});
			previous_fs.css({'transform': 'scale('+scale+')', 'opacity': opacity});
		},
		duration: 800,
		complete: function(){
			current_fs.hide();
			animating = false;
		},
		//this comes from the custom easing plugin
		easing: 'easeInOutBack'
	});
});



let check=document.querySelectorAll('[name="disable[]"]');
check.forEach(chk=> chk.addEventListener('change',validarCHK));
//validacion del checkbox
function validarCHK(){
    var generos=[];
    var checked= document.querySelectorAll(':checked');
    check.forEach(chk=>{
        if(chk.checked || checked.length<4){
            chk.disabled=false;
            if(chk.checked){
                var grn={id:chk.value}
                generos.push(grn);
            }
        }else{
            chk.disabled=true;
        }
    });

    localStorage.setItem("generos",JSON.stringify(generos));
}
//enviar datos usuarios
function updUsuario(){
    $.ajax({
        type:"get",
        url:"/regis/updUser",
        data:encodeURIComponent(JSON.stringify(datosServ)),
        async: true,
        success:function(){
            addusergen();
        }
    });
}

function addusergen(){
    const gnero=JSON.parse(window.localStorage.getItem("generos"));
    $.ajax({
        type:"get",
        url:"/regis/addGenuser",
        data:encodeURIComponent(JSON.stringify(gnero)),
        async: true,
        success:function(){
            Swal.fire({
                icon: 'success',
                title: 'INFORMACION ACTUALIZADA!',
                text: '¡Se ha guardado correctamente tú infomación!',
                confirmButtonText:'Aceptar'

            }).then((result)=>{
                window.localStorage.clear();
                document.msform.submit();
               // window.location.replace("/Inicio/BibliotecaGEM.xhtm?");
            });
        }
    });
}


$(".submit").click(function(){
    updUsuario();

	return false;
});