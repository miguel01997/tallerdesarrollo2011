




/////////////////////
  
      
  
  //TEXTO
      var textoInicio = "SELECT ( ";
      var textoFin = " )";
      var atributosSelec=new Array();
      var listaatributos=new Array();
      var listaCuantificadores = new Array();
      var contExpCu = 0;
  
  
      function llenarlista(id,tabla){
          
          var elem = document.getElementById(id);
       
          var newhtml = "";

          /*
          var m = tabla.toString();
          
          var m2 = m.slice(1, m.length-1)
         
          var aux = m2.split(',');
          
          
          var arr = aux;
          listaatributos=aux;
          */
        
          
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
      function agregarTextoPred(idTexto,elemText,idtabla){alert('agregarTextoPred');
          var names = document.getElementsByName(elemText);
          var text = "";
          var tabla = document.getElementById("listatablas").value;
          var tablaaux = tabla.split('.');
          
          var dummyA=tablaaux[1];
          
          var dummy= dummyA.charAt(0);
          
          var parabre=document.getElementById("form2").abrepred;
          var parcierra=document.getElementById("form2").cierrapred;
        
         
          
           
          
          
          //busca conector si es necesario
          if(predicados.length>0){
              var c =  document.getElementById('conector');
              text = ""+c.value;
          }
          
           if(parabre.checked){
              
              text= text + " (";
          }
          
          
          //se le concatena la tabla
          for(i = 0;i<names.length;i++){
              if(i==0){
                  text=text+" "+dummy+"."+names[i].value;
                  continue;       
              }
              text = text+ " " +names[i].value;
          }
          
           if(parcierra.checked){
              
              text=text+")";
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
         
         var checkboxes = document.getElementById("form1").checkbox;
         var cont = 0; 
 
        for (var x=0; x < checkboxes.length; x++) {
            if (checkboxes[x].checked) {
                atributosSelec.push(checkboxes[x].value);
              
            }}
            
            if(atributosSelec.length<1){
                alert("Debe seleccionar almenos un campo a proyectar");
                
          
            }
            else{
                cambiarVentana(ventana1,ventana2);
                
            }
        
        //Agrega primer text area donde se escribira en el cuantificador 
         listaCuantificadores.push("texCuanti0");
         
         //alert(atributosSelec);
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
      function verificarRadioBotton(r1,r2,v1,v2,v3){
          radio1 = document.getElementById(r1);
          radio2 = document.getElementById(r2);
          var tabla = document.getElementById("listatablas").value;
          if(radio1.checked){
              cambiarVentana(v1, v2);
            //  alert("Tabla "+tabla);
              cargarAtributos(tabla, "listaAtt");
          }//Cuantificador
          else{
               //busca el drop list
               var rList = document.getElementById("listaRelCuan");
               //busca 
               
               //prepara la lista de relaciones
               var listaR = mapR[tabla];
               for ( var x = 0; x < listaR.length; x++ ) {
                    _option = document.createElement('option');
                    //1 para que salga el nombre del atributo
                    var _text = document.createTextNode((listaR[x].split(" "))[1]);
                    _option.appendChild(_text);
                    rList.appendChild(_option); 
                    _option.value = listaR[x];
               } 
               cambiarVentana(v1, v3);
               
              
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
        if (tr.rowIndex == 0 && table.rows.length>1){
            //Quitar el conector de la siguiente fila
            var rows = table.rows;
            var nextrow = rows[1];
            var cell = nextrow.cells[0];
            var oldhtml = cell.innerHTML;
            var newhtml = oldhtml.substr(oldhtml.indexOf(" "));
            cell.innerHTML = newhtml;
        }
        
        table.removeChild(tr);
        //Saca el predicado de la lista de predicados
        var pos = tr.id;
        
        delete predicados[pos];
        //predicados.slice(pos, pos);
        actualizarTexto('textoOCL');
       
        predicados.length = predicados.length -1;
        
    }
    
    function cargarAtributos(tabla,idselect){
        var select = document.getElementById(idselect);
        var newhtml = "";
        var arr = map[tabla];
        //newhtml +="<option value=""></option>";
        
        for (var i in arr){


              var atr = arr[i];
              
              newhtml += "<option value="+atr+">"+atr+"</option>";
        }
        select.innerHTML = newhtml;
    }
    
    
    
    
    /*Verifica si la variable es numerica y activa el aviso*/
    function variableNoNumerica(variable,aviso){
            var avi = document.getElementById(aviso);
            
       if( /^(\d+)([A-Za-z])*|([A-Za-z])*(\d+)([A-Za-z]*)$/.test(variable) ){
            avi.hidden = false;
        }
        else{
            avi.hidden = true;
        }
        
    }
    
    
    function tieneNumero(variable){
         if( /^(\d+)([A-Za-z])*|([A-Za-z])*(\d+)([A-Za-z]*)$/.test(variable) ){
            return true;
        }
        else{
            return false;
        }
        
    }
    
    /*Agrega el texto al cuadro de texto de cuantificador*/
    function cambiaCuanti(nCuanti,nombreLista,variable){
        //buscar la variable dummy externa
          var t = document.getElementById(variable).value;
          var tablaa = t.split('.');
          var duA=tablaa[1];
          var dummy= duA.charAt(0);
        var cuan = document.getElementsByName(nombreLista);
         var relacion = cuan[0].value.split(" ")[1];
         //guarda la tabla axilioar
         var tAux = document.getElementById("classCuan0");
         tAux.value=cuan[0].value.split(" ")[0];
         
         var cuanti = cuan[1].value;
         var variabled = cuan[2].value;
         var expre =  variabled+"."+cuan[3].value;
         if(tieneNumero(variabled)){
            variabled = "";
        }
        textoCuan=dummy+"."+relacion+"->"+cuanti+"("+variabled+"| "+expre+")";
        var textoC =document.getElementById(nCuanti);
        textoC.value= textoCuan;
    }
    
    
/*Agrega la lista lista al div en forma de dropdownlist
 *asociandole el nombre nombre
 **/    
    function AddDopListDiv(div,lista,nombre)
    {   
       
      var container = document.getElementById(div);
      var _select = document.createElement('select');
      for ( var x = 0; x < lista.length; x++ ) {
        _option = document.createElement('option');
        var _text = document.createTextNode(lista[x]);
        _option.appendChild(_text);
        _select.appendChild(_option); 
        _option.value = lista[x];} 
        container.appendChild(_select);
        _select.setAttribute('name', nombre); 
    }


//NO SE USA
    function crearListaRelaciones(div,clase){
       var arrList = mapR[clase];
       
       //elimina el nombre de la clase
       
       for(var i =0 ;i<arrList.length;i++){
          alert(arrList[i].split(" ")[1]);
       }
       AddDopListDiv(div, arrList);
    }
    
    
    /*Crea un mapa de la forma <<clave,valores>,<clave,valores>>*/
    //NO USAR
    function crearMapaDeString(mapa){
        var map = mapa.toString();
        
        map = map.slice(1, map.length);
        map = map.slice(0, map.length-1);
        
        //alert(map);    
        var diccionario =  new Array();
        var arr = map.split("],");
        for(var i=0;i<arr.length;i++){
            var indi = (arr[i]).indexOf("=", 0);
            var k = arr[i].slice(0, indi);
           // alert(k);
            //agrega la clave
            var valor = arr[i].slice(indi+2, arr[i].length);
            
            if(valor.charAt(valor.length-1)=="]"){
                var sAux = valor.slice(0, valor.length-1);
               // alert(sAux);
                var valores =  new Array();
                valores.push(k);
                valores.push(sAux);
                valores.push(sAux);
                diccionario.push(valores);
            }
            else{
                valores =  new Array();
                valores.push(k);
                valores.push(valor);
                valores.push(valor);
                
                diccionario.push(valores);
            }
        }
       //  alert(diccionario);
       for(var op = 0; op<diccionario.length;op++){
    //           alert(diccionario[op]);
       }
       return diccionario;
        
    }
    
    
    function agregarDivEpresiones(){
        var dv = document.createElement("div");
       
        dv.setAttribute('id',"cuantiExpre"+listaCuantificadores.length);       //give id to it
        
        
        //Donde se colocara la plantilla
        var auxx = document.getElementById("recuCuanti");
        //crea raya de separacion
        crearRaya(dv);
        
        //crea El titulo
        crearTitulo(dv, "Insertar");
        
        var p = new Array();
        p.push("Predicado", "Cuantificador");
        
        //crea Radio Button
        crearRadioButton(dv, "radp",p);
        
        //salto
        crearSaltoLinea(dv);
        
        crearBoton(dv, "Atras", "");
        
        crearBoton(dv, "Siguiente", "");
        
        auxx.appendChild(dv);
       
       
      
      //dv.className="top";                // set the style classname 
      
      /* var _select = document.createElement('select');
      for ( var x = 0; x < lista.length; x++ ) {
        _option = document.createElement('option');
        var _text = document.createTextNode(lista[x]);
        _option.appendChild(_text);
        _select.appendChild(_option); 
        _option.value = lista[x];} 
        container.appendChild(_select);
        _select.setAttribute('name', nombre); */
        
    }
    
    
    
    ///////////////FUNCIONES PARA CREAR LAS VENTANAS
    //VENTANA DE EXPRESION PARA CUANTIFICADOR
    
    
    function crearVentanasRec(div){
        agregarDivEpresiones(div);
        ventanaPredicadoRec(div);
        
    }
    
    
    function agregarDivEpresiones(div){
        var ndiv = "divCuan"+contExpCu++;
        var dv = document.createElement("div");
        dv.setAttribute('id',ndiv);       //give id to it
        //Donde se colocara la plantilla
        var auxx = document.getElementById(div);
        //crea raya de separacion
        crearRaya(dv);
        //crea El titulo
        crearTitulo(dv, "Insertar");
        var p = new Array();
        p.push("Predicado", "Cuantificador");
        //crea Radio Button
        var nRad = "rad"+ndiv;
        crearRadioButton(dv, nRad,p);
        
        //salto
        crearSaltoLinea(dv);
        crearBoton(dv, "Atras","atrasRec('"+ndiv+"')");
       //crearBoton(dv, "Atras","p('"+45+"')");
       
       //busca los raio buton
        var r1= document.getElementById(nRad+0);
        var r2 = document.getElementById(nRad+1);
       
        // var accion = "verificarRadioBotton("+r1+","+r2+"', ndiv,ndiv,"ndiv)"
        crearBoton(dv, "Siguiente", "verificarRadioBotton("+r1+","+r2+", v1, v2, v3)");
        auxx.appendChild(dv);
       // alert(ndiv);
       // AQUI
        //listaCuantificadores.push(ndiv);
    }
    
    
    
    function ventanaPredicadoRec(div,tabla){
        var ndiv = "PdivCuan"+contExpCu;
        var dv = document.createElement("div");
        dv.setAttribute('id',ndiv);       //give id to it
        //Donde se colocara la plantilla
        var auxx = document.getElementById(div);
        //crea raya de separacion
        crearRaya(dv);
        //crea El titulo
        crearTitulo(dv, "Predicado");
        
        
        var tr = document.createElement("tr");
        var tr2 = document.createElement("tr");
        //tr.appendChild(_text2);
        
        //td
        var td = document.createElement("td");
        var td2 = document.createElement("td");
        var td3 = document.createElement("td");
        var td4 = document.createElement("td");
        
        
        //crea la tabla 
        var table = document.createElement("table");
        
        table.appendChild(tr);
        table.appendChild(tr2);
        
        tr2.appendChild(td);
        tr2.appendChild(td2);
        tr2.appendChild(td3);
        tr2.appendChild(td4);
        
        var _text2 = document.createTextNode("Atributos");
        var h1 = document.createElement("th");
        h1.appendChild(_text2);
        tr.appendChild(h1);
        var _text3 = document.createTextNode("Comparadores");
        var h2 = document.createElement("th");
        h2.appendChild(_text3);
        tr.appendChild(h2);
        var _text4 = document.createTextNode("Modificadores");
        var h3 = document.createElement("th");
        h3.appendChild(_text4);
        tr.appendChild(h3);

        var _text5 = document.createTextNode("Predicados");
        var h4 = document.createElement("th");
        h4.appendChild(_text5);
        tr.appendChild(h4);   
        
        var name = "Sel"+ndiv;
         //var _text5 = document.createTextNode("Predicados");
        var s1 = document.createElement("select");
        s1.setAttribute("id", "Sel"+ndiv+1);
        s1.setAttribute("name", name);
        td.appendChild(s1);   
        //comparador
         //s1 = document.createElement("select");
          var lc = document.getElementById("listaAttC").cloneNode(true);
          lc.setAttribute("id", "Sel"+ndiv+2);
          lc.setAttribute("name", name);
          td2.appendChild(lc);   
        //busca las listas
        //Modificadores
        var lm =document.getElementById("listaAttM").cloneNode(true);
        lm.setAttribute("id", "Sel"+ndiv+3);
        lm.setAttribute("name", name);
        td3.appendChild(lm);   
        //Predicados
         var lp = document.getElementById("listaAttP").cloneNode(true);
         lp.setAttribute("id", "Sel"+ndiv+4);
         lp.setAttribute("name", name);
         td4.appendChild(lp);   


        dv.appendChild(table);   
        crearSaltoLinea(dv);
        crearSaltoLinea(dv);
        //crea botones
        //busca el area donde se escribira la expresion
        var ta = listaCuantificadores[contExpCu-1];
        //alert(ta);
        crearBoton(dv, "Atras", "");
        crearBoton(dv, "Agregar Predicado", "armarPredicado('"+name+"','"+ta+"')");
        
        auxx.appendChild(dv);
        
        var nombre = "classCuan" +(+contExpCu-1);
        //alert(nombre);
        //busca la clase
        var cN = document.getElementById(nombre);
        //alert(cN.value);
        cargarAtributos(cN.value, "Sel"+ndiv+1);
        
        
        
    }
    
    
    
    //armar predicado
    
    function armarPredicado(name,div){
        var lp = document.getElementsByName(name);
        var texto  ="";
        for(x=0;x<lp.length;x++){
            if(x==0){
                 texto = texto+lp[x].value;
                 continue;
            }
             texto = texto +" "+ lp[x].value;
        }
        
        
        agregarTexto(div,texto);
        var ven =  name.toString().substr(3, name.lenth) ;
        cambiarVentana(ven, "");
    }
    
    
    
   //Atras Recursivo 
   function atrasRec(ndiv){
      // contExpCu--;
      // delete listaCuantificadores[contExpCu];
       cambiarVentana(ndiv,"");
      // listaCuantificadores.length = listaCuantificadores.length -1;
       
   }
    
    
    
    //Funcion para maquetear
    
    //Crea una raya en el div div
    function crearRaya(div){
        //var auxx = document.getElementById(div);
         //crea raya de separacion
        var ray = document.createElement("hr");
        div.appendChild(ray);
    }
    
    
    
    function crearTitulo(div,titulo){
        var t = document.createElement("h2");
         var _text = document.createTextNode(titulo);
         t.appendChild(_text);
        div.appendChild(t);
        
    }
    
    
    function crearRadioButton(div,id,opciones){
        
        for(var i=0;i<opciones.length;i++){
            var t = document.createElement("input");
            t.setAttribute("id", id+i);
            t.setAttribute("name", id);
            t.setAttribute("type", "radio");
            t.setAttribute("value", opciones[i]);
            
            if(i==0){
              t.setAttribute("checked", "true");
              
           }
           var _text = document.createTextNode("  "+opciones[i]);
            div.appendChild(t);
            div.appendChild(_text); 
           
         }
        
   }


   function crearSaltoLinea(div){
       var t = document.createElement("br");
       div.appendChild(t);
   }


   function crearBoton(div,titulo,accion){
         var t = document.createElement("button");
         var _text = document.createTextNode(titulo);
         t.appendChild(_text);
         t.setAttribute("onclick", accion);
        div.appendChild(t);
        
    }

/*
   function agregarTexto(elem,text){alert('Entra');
       var t = document.getElementById(elem);
       t.value = text;
   }
   */
  
  