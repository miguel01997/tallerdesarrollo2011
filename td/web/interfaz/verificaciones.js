

function confirmar ( mensaje ) {
return confirm( mensaje );
} 

function Mensaje(mensaje){
    if(mensaje!='')
    
    alert(mensaje);
}


//Verifica el precio y el motor en la vista de crearVehiculo 
function veriCrearVehiculo(){
    var precio = ""+document.cv.precio.value;
    var motor = ""+document.cv.motor.value;
    var patPre = /^\d+.?\d+$/;
    var patMotor = /^\d{1,4}$/;
    if(! (patPre.test(precio)) ){
        alert("Precio no es válido.");
        return false;
    }
    if(! (patMotor.test(motor)) ){
        alert("Motor no es válido. Debe contener entre 1 y 4 dígitos.");
        return false;
    }
    return true;
}

function verificarEdad(){
     var edad = ""+document.cu.edad.value;
     var patEdad = /^\d{1,2}$/;
     if( !(patEdad.test(edad)) ){
         alert("Edad no válida.");
         return false
     }
     return true;
}
