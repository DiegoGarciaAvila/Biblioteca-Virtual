var btnGenLite = document.getElementById("genlite");
var btnLibro = document.getElementById("libros");
var btnUads = document.getElementById("uads");
var btnDia = document.getElementById("dia");
var btnMes = document.getElementById("mes");
var btnAnio = document.getElementById("anio");
let mychart = null;

// scatter line and bar
// U N I D A D  A D M I N I S T R A T I V A
btnUads.addEventListener("click",()=>{
    var uad_x=uads.map( x => x[1] );
    var uad_y=uads.map( x => x[0] );
    function genNum(num){
        return (Math.random()*num).toFixed(0);
    }
    var colour="rgba";
    colour+="(" + genNum(255) + "," + genNum(255) + "," + genNum(255) + ",0.8)";
    const labels = uad_x;
    const data = {
        labels: labels,
        datasets: [
        {
            label: "Libros",
            backgroundColor:colour,
            borderColor: colour,
            data: uad_y
        }],
    };
    const config = {
        type: "bar",
        data: data,
        options: {
            plugins:{
                title:{
                    display: true,
                    text:"No. de libros prestados por Unidad Administrativa"
                }
            },
            scales:{
                y:{
                    beginAtZero:true
                }
            }
        }
    };
    if(mychart!=null){
        mychart.destroy();
    }
    mychart = new Chart(document.getElementById("myBar").getContext('2d'), config);
});

// P R E S T A M O  L I B R O
btnLibro.addEventListener("click",()=>{
    var libro_x=libro.map( x => x[1]);
    var libro_y=libro.map( x => x[0]);
    function genNum(num){
        return (Math.random()*num).toFixed(0);
    }
    var colour="rgba";
    colour+="(" + genNum(255) + "," + genNum(255) + "," + genNum(255) + ",0.8)";
    const labels = libro_x;
    const data = {
        labels: labels,
        datasets: [
        {
            label: "Libros",
            backgroundColor:colour,
            borderColor: colour,
            data: libro_y
        }],
    };
    const config = {
        type: "bar",
        data: data,
        options: {
            plugins:{
                title:{
                    display: true,
                    text:"No. de libros prestados por titulo"
                }
            },
            scales:{
                y:{
                    beginAtZero:true
                }
            }
        }
    };
    if(mychart!=null){
        mychart.destroy();
    }
    mychart = new Chart(document.getElementById("myBar").getContext('2d'), config);
});

//G E N E R O  L I T E R A R I O
btnGenLite.addEventListener("click",()=>{
    function genNum(num){
        return (Math.random()*num).toFixed(0);
    }
    var colour="rgba";
    colour+="(" + genNum(255) + "," + genNum(255) + "," + genNum(255) + ",0.8)";
    var genlite_y=genlitera.map( gen => gen[0]);
    var genlite_x=genlitera.map( gen => gen[1]);
    const labels = genlite_x;
    const data = {
      labels: labels,
      datasets: [
      {
        label: "Libros",
        backgroundColor:colour,
        borderColor: colour,
        data: genlite_y
      }],
    };
    const config = {
      type: "bar",
      data: data,
      options: {
        plugins:{
            title:{
                display: true,
                text:"No. de libros prestados por genero literario"
            }
        },
        scales:{
            y:{
                beginAtZero:true
            }
        }
      }
    };
    if(mychart!=null){
        mychart.destroy();
    }
    mychart = new Chart(document.getElementById("myBar").getContext('2d'), config);
});
function primFechFiltro(date){
    const startDate=new Date(date.value);
    mychart.config.options.scales.x.min = startDate.setHours(0,0,0,0);
    mychart.update();
}
function ultFechFiltro(date){
    const lastDate=new Date(date.value);
    mychart.config.options.scales.x.max=lastDate.setHours(0,0,0,0);
    mychart.update();
}

// P O R  D I A
btnDia.addEventListener("click",()=>{
    var dia_y=gpdia.map(x => x[1] )
    var dia_x=gpdia.map(x => new Date(x[0]));
    console.log(dia_x)
    function genNum(num){
        return (Math.random()*num).toFixed(0);
    }
    var colour="rgba";
    colour+="(" + genNum(255) + "," + genNum(255) + "," + genNum(255) + ",0.8)";
    const labels = dia_x;
    const data = {
        labels: labels,
        datasets: [
        {
            label: "Libros",
            backgroundColor:colour,
            borderColor: colour,
             data: dia_y
        }],
    };
    const config = {
        type: "bar",
        data: data,
        options: {
            plugins:{
                tooltip:{
                    callbacks:{
                        title: context =>{

                            const d =new Date(context[0].parsed.x);
                            const formatDate=d.toLocaleDateString('es-mx',{
                                day:'numeric',
                                month:'short',
                                year:'numeric'
                            });
                            return formatDate;
                        }
                    }
                },
                title:{
                    display: true,
                    text:"No. de libros prestados por Día"
                }
            },
            scales:{
                x:{
                    adapters:{
                        date:{
                            locale: 'es-MX'
                        }
                    },
                    min: minDate,
                    max: maxDate,
                    type:'time',
                    time:{
                        unit: 'day',
                        displayFormats:{
                            'day': {day:'numeric',month:'long'}
                        }
                    }
                },
                y:{
                    beginAtZero:true
                }
            }
        }
    };
    if(mychart!=null){
        mychart.destroy();
    }
    mychart = new Chart(document.getElementById("myBar").getContext('2d'), config);
});

// P O R  M E S
btnMes.addEventListener("click",()=>{
    var mes_x=gpmes.map( x => new Date(x[0]));
    var mes_y=gpmes.map( x => x[1]);
    function genNum(num){
        return (Math.random()*num).toFixed(0);
    }
    var colour="rgba";
    colour+="(" + genNum(255) + "," + genNum(255) + "," + genNum(255) + ",0.8)";
    const labels = mes_x;
    const data = {
        labels: labels,
        datasets: [
        {
            label: "Libros",
            backgroundColor:colour,
            borderColor: colour,
            data: mes_y
        }],
    };
    const config = {
        type: "bar",
        data: data,
        options: {
            plugins:{
                tooltip:{
                    callbacks:{
                        title:contxt=>{
                            const m=new Date(contxt[0].parsed.x);
                            const formatDate=m.toLocaleDateString('es-MX',{
                                month:'long',
                                year:'numeric'
                            });
                            return formatDate;
                        }
                    }
                },
                title:{
                    display: true,
                    text:"No. de libros prestados por Mes"
                }
            },
            scales:{
                x:{
                    adapters:{
                        date:{
                            locale: 'es-MX'
                        }
                    },
                    type:'time',
                    time:{
                        unit: 'month',
                        displayFormats:{
                            'month': {year:'numeric',month:'long'}
                        }
                    }
                },
                y:{
                    beginAtZero:true
                }
            }
        }
    };
    if(mychart!=null){
        mychart.destroy();
    }
    mychart = new Chart(document.getElementById("myBar").getContext('2d'), config);
});

// P O R  A Ñ O
btnAnio.addEventListener("click",()=>{
    var anio_x=gpanio.map( x => new Date(x[0]).toLocaleDateString("es-MX",{year:'numeric'}));
    var anio_y=gpanio.map( x => x[1]);
    function genNum(num){
        return (Math.random()*num).toFixed(0);
    }
    var colour="rgba";
    colour+="(" + genNum(255) + "," + genNum(255) + "," + genNum(255) + ",0.8)";
    const labels = anio_x;
    const data = {
        labels: labels,
        datasets: [
        {
            label: "Libros",
            backgroundColor:colour,
            borderColor: colour,
            data: anio_y
        }],
    };
    const config = {
        type: "bar",
        data: data,
        options: {
            plugins:{
                title:{
                    display: true,
                    text:"No. de libros prestados por Año"
                }
            },
            scales:{
                y:{
                    beginAtZero:true
                }
            }
        }
    };
    if(mychart!=null){
        mychart.destroy();
    }
    mychart = new Chart(document.getElementById("myBar").getContext('2d'), config);
});