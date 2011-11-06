




/////////////////////
  
      var map = new Array();
      var arr1 = ['atr1','atr2'];
      var arr2 = ['atr3','atr4'];
      map['tabla1'] = arr1;
      map['tabla2'] = arr2;
      
  
  //TEXTO
      var textoInicio = "SELECT ( ";
      var textoFin = " )";
  
  
      function llenarlista(id,tabla){
          var elem = document.getElementById(id);
          //alert(tabla);
          var newhtml = "";
          var m = tabla.toString();
          
          var m2 = m.slice(1, m.length-1)
         // alert(m2);
          //tabla = tabla.pop();
          //alert(tabla);
          var aux = m2.split(',');
          
          //alert(aux);
          var arr = aux;
          for (var i in arr){
              //alert(arr[i]);
              var atr = arr[i];
              newhtml += "<input type='checkbox' name=atr"+i+" value='"+atr+"'>"+atr+"<br>";
          }
          elem.innerHTML = newhtml;
          
      }
      
      function agregarElemento(idtabla,elem){
          var table = document.getElementById(idtabla);
          var row = table.insertRow(-1);
          var cell1 = row.insertCell(0);
          cell1.innerHTML = elem;
          var cell2 = row.insertCell(1);
          cell2.innerHTML = "<input type='button' value='X'>";
      }
      
      
      
//////////////PARA TEXO DE OCL      
      var predicados = new Array();
      //Actualiza texto del div de codigo ocl
      function actualizarTexto(idTexto){
        m =  document.getElementById(idTexto);
        texto = textoInicio+ predicados.join(" ") + textoFin;
        m.value = texto;
      }
      
      
      //Agrega el texto texto a idTexto
      function agregarTexto(idTexto,texto){
          m =  document.getElementById(idTexto);
          predicados.push(texto);
          this.actualizarTexto(idTexto);
          
      }
      
      //Agrega el texto texto a idTexto
      function agregarTextoPred(idTexto,elemText,tabla){
          var names = document.getElementsByName(elemText);
          var text = "";
          //busca conector si es necesario
          if(predicados.length>0){
              var c =  document.getElementById('conector');
              text = text+ " " +c.value
          }
          //se le concatena la tabla
          for(i = 0;i<names.length;i++){
              if(i==0){
                  text=text+" "+tabla+"."+names[i].value;
                  continue;       
              }
              text = text+ " " +names[i].value;
          }
          var m =  document.getElementById(idTexto);
          //agrega el texto a la lista de predicados visual
          cargarPredicados("listaPredicados",text);
          
          //Agrega el texo al cuadro
          agregarTexto(idTexto, text);
          
          
          
          //oculta ventana
          ocultarVentana('v4_2_1');
      }
      
      
      
      //Cambia entre ventanas
      function cambiarVentana(ventana1,ventana2){
          v = document.getElementById(ventana1);
          v.hidden=true;
          
          v = document.getElementById(ventana2);
          v.hidden=false;
      }
      
      //activa ventana
      function activarVentana(ventana){
          v = document.getElementById(ventana);
          v.hidden=false;
          
      }
      
      //ocultar ventana
      function ocultarVentana(ventana){
          v = document.getElementById(ventana);
          v.hidden=true;
          
      }
      
      
      
      
      //Verifica cual radioboton esta seleccionado y prepara para el siguiente div
      function verificarRadioBotton(r1,r2){
          radio1 = document.getElementById(r1);
          radio2 = document.getElementById(r2);
          if(radio1.checked){
              cambiarVentana('v4_1', 'v4_2_1');
          }
      }




/// VERIFICA SI NECESITA UN CONECTOR
//Si no necesita conector envia a ventana1 si lo necesita envia a ventana2
     function necesitaConector(ventanaOrigen,ventana1,ventana2){
         if(predicados.length>0){
             cambiarVentana(ventanaOrigen, ventana2);
         }
         else{
             cambiarVentana(ventanaOrigen, ventana1);
         }
         
     }



//Lista los predicado creados en el cuadro derecho
    function cargarPredicados(idtabla,texto){
        var table = document.getElementById(idtabla);
          var row = table.insertRow(-1);
          row.id=predicados.length;
          var cell1 = row.insertCell(0);
          cell1.innerHTML =texto;
          
          var cell2 = row.insertCell(1);
          cell2.innerHTML = "<input type='button' value='X'   onclick=eliminarCeldaPred(this) >  "; 
         //table.innerHTML ="    <tr>       <td>pruebac</td> </tr>";
        
    }

    //elimina una celda 
    function eliminarCelda(t){
        var td = t.parentNode;
        var tr = td.parentNode;
        var table = tr.parentNode;
        table.removeChild(tr);
        alert(tr.id);
    }
    
    //elimina celda de lista de predicados
    
    function eliminarCeldaPred(t){
        var td = t.parentNode;
        var tr = td.parentNode;
        var table = tr.parentNode;
        table.removeChild(tr);
        //Saca el predicado de la lista de predicados
        var pos = tr.id;
         //predicados.
        
    }