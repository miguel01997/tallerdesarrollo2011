




/////////////////////
  
      
  
  //TEXTO
      var textoInicio = "SELECT ( ";
      var textoFin = " )";
      var atributosSelec=new Array();
      var listaatributos=new Array();
  
  
      function llenarlista(id,tabla){;
          var elem = document.getElementById(id);
          //alert(tabla);
          var newhtml = "";

          /*
          var m = tabla.toString();
          
          var m2 = m.slice(1, m.length-1)
         
          var aux = m2.split(',');
          
          
          var arr = aux;
          listaatributos=aux;
          */
         alert(tabla);
           var arr = mapA[tabla];
          for (var i in arr){


              var atr = arr[i];
              //newhtml += "<input type='checkbox' name=atr"+i+" value='"+atr+"'>"+atr+"<br>";
              newhtml += "<input type='checkbox' name=checkbox value='"+atr+"'>"+atr+"<br>";
          }
          elem.innerHTML = newhtml;
          
      }
      
      
      
      
      function agregarElemento(idtabla,elem){
          var table = document.getElementById(idtabla);
          var row = table.insertRow(-1);
          var cell1 = row.insertCell(0);
          cell1.innerHTML = elem;
          var cell2 = row.insertCell(1);
          cell2.innerHTML = "<input type='button' value='X'onclick=eliminarCelda(this)>";
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
      
      
      function atributosSeleccionado(ventana1,ventana2){
         cambiarVentana(ventana1,ventana2);
         var checkboxes = document.getElementById("form1").checkbox;
         var cont = 0; 
 
        for (var x=0; x < checkboxes.length; x++) {
            if (checkboxes[x].checked) {
                atributosSelec.push(checkboxes[x].value);
              
            }
        }
         
         
         alert(atributosSelec);
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
              var tabla = document.getElementById("listatablas").value;
              alert("Tabla "+tabla);
              cargarAtributos(tabla, "listaAtt");
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
        
    }
    
    //elimina celda de lista de predicados
    
    function eliminarCeldaPred(t){
        var td = t.parentNode;
        var tr = td.parentNode;
        var table = tr.parentNode;
        table.removeChild(tr);
        //Saca el predicado de la lista de predicados
        var pos = tr.id;
        
        delete predicados[pos];
        //predicados.slice(pos, pos);
        actualizarTexto('textoOCL');
        //alert(predicados.length);
        predicados.length = predicados.length -1;
        
    }
    
    function cargarAtributos(tabla,idselect){
        var select = document.getElementById(idselect);
        var newhtml = "";
        var arr = map[tabla];
        for (var i in arr){


              var atr = arr[i];
              
              newhtml += "<option value="+atr+">"+atr+"</option>";
        }
        select.innerHTML = newhtml;
    }