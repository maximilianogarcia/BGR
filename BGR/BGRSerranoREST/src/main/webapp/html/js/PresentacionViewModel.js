function PresentacionViewModel() {
   var self = this;
   self.selectedUnmapped = null;
   self.presentaciones = ko.mapping.fromJS([new Presentacion()]);
   self.selected = ko.mapping.fromJS(new Presentacion());
   self.createNew = ko.observable(false);
   self.soloActivos = ko.observable(true);
   self.paraFraccionarSelected= ko.observable();
   self.paraFraccionar = ko.observable();
	self.messageDesactivar = ko.observable();   

	self.messageDesactivarOn = ko.computed(function(){
	     return (self.messageDesactivar() != null && self.messageDesactivar().length > 0 );
	});
   
   
   self.stock = ko.observable();
   self.cantidad_fraccionada = ko.observable();
   
   //////////////////////////////////////////////   
   self.stock_max = ko.computed(function(){
     return self.stock();
   });   
   //////////////////////////////////////////////   
   
   self.step = ko.observable(1);
   self.step1 = ko.computed(function(){
        return self.step() == 1;
   });
   self.step2 = ko.computed(function(){
        return self.step() == 2;
   });
   self.step3 = ko.computed(function(){
        return self.step() == 3;
   });
   self.step4 = ko.computed(function(){
        return self.step() == 4;
   });
   self.step5 = ko.computed(function(){
       return self.step() == 5;
  });   
   self.step6 = ko.computed(function(){
      return self.step() == 6;
  });

   self.backeable = ko.computed(function(){
        return self.step() != 1  ;
   });
   
	self.createStep = ko.observable(1);
   self.createStep1 = ko.computed(function(){
        return self.createStep() == 1;
   });
   self.createStep2 = ko.computed(function(){
        return self.createStep() == 2;
   });

   self.productos = ko.observableArray();
   self.lotes =  ko.mapping.fromJS([new Lote()]);

   self.materiales = ko.mapping.fromJS([new Material()]);
   self.medidas    = ko.mapping.fromJS([new UnidadMedida()]);

   self.proveedores    = ko.mapping.fromJS([new Proveedor()]);
  
   self.placeHolderDesactivar = ko.computed(function(){
	   if(self.selected.active()){
		   return "Razon para desactivar";
	   }
	   return "Razon para activar";
    });
   
   self.selectedProductoId = ko.observable(null);
   self.selectedMedidaId = ko.observable(null);
   self.selectedLoteId = ko.observable(null);
   self.selectedMaterialId = ko.observable(null);
   self.selectedProveedorId = ko.observable(null);

   self.selectedPaqueteId = ko.observable(null);   

   self.hayPaqueteParaFraccionar = ko.computed(function(){
	     return (self.selectedPaqueteId() != null && self.selectedPaqueteId().length > 0 );
	   });
   
   
   self.hayProductoSeleccionado = ko.computed(function(){
	     return self.selectedProductoId() != null;
   });
   self.hayProveedorSeleccionado = ko.computed(function(){
	     return self.selectedProveedorId() != null;
   });
   self.hayLoteSeleccionado = ko.computed(function(){
     return self.selectedLoteId() != null;
   });
   self.hayMaterialSeleccionado = ko.computed(function(){
     return self.selectedMaterialId() != null;
   });
   self.hayMedidaSeleccionado = ko.computed(function(){
     return self.selectedMedidaId() != null;
   });

   self.selectedProducto = function() {
   //    var self = this;
       return ko.utils.arrayFirst(this.productos(), function(producto) {
           return self.selectedProductoId() == producto.id;
       });
   }.bind(this);
   self.selectedProveedor = function() {
	   //    var self = this;
	       return ko.utils.arrayFirst(this.proveedores(), function(proveedor) {
	           return self.selectedProveedorId() == proveedor.id;
	       });
   }.bind(this);
   self.selectedLote = function() {
  //    var self = this;
       return ko.utils.arrayFirst(this.lotes(), function(lote) {
           return self.selectedLoteId() == lote.id;
       });
   }.bind(this);
   self.selectedMaterial = function() {
   //    var self = this;
       return ko.utils.arrayFirst(this.materiales(), function(material) {
           return self.selectedMaterialId() == material.id;
       });
   };

   self.selectedUnidad_de_medida = function() {
      // var self = this;
       return ko.utils.arrayFirst(this.medidas(), function(um) {
           return self.selectedMedidaId() == um.id;
       });
   }.bind(this);
   
  self.medidaName = ko.computed(
	function(){
		
		var result = ko.utils.arrayFirst(self.medidas(), function(um) {
	           return self.selectedMedidaId() == um.id;
	       });
		var name= null
		if(result){
			name = result.name
		};
		return 'Cantidad de '+ name;
	}
  
  )

   self.apply = function(){
     ko.applyBindings(self);
     self.getActives();
     var viewModelProducto = new ProductoViewModel();
     viewModelProducto.getAll(self.copiar);
   }
   
   self.serialized = function(){
      return ko.mapping.toJSON(self.selected);
   }

   self.copiar =function(destino){
        self.productos(destino);
   }


   self.save = function(){
    var serializado=JSON.parse(ko.mapping.toJSON(self.selected));
    serializado.producto = self.selectedProducto();
    serializado.proveedor = self.selectedProveedor();
    serializado.lote = self.selectedLote();
    if(serializado.lote){
	    serializado.lote.fechaDeElaboracion= moment(serializado.lote.fechaDeElaboracion).format('YYYY-MM-DD');
	    serializado.lote.fechaDeVencimiento= moment(serializado.lote.fechaDeVencimiento).format('YYYY-MM-DD');
    }
    serializado.date_create= moment().format('YYYY-MM-DD');
    serializado.date_update= moment().format('YYYY-MM-DD');


    serializado.material = self.selectedMaterial();
    serializado.proveedor = self.selectedProveedor();

    serializado.unidad_de_medida = self.selectedUnidad_de_medida();
  	 	


    var $myForm = $('#editPresentacionForm');
    if ($myForm[0].checkValidity()) {
    	
  	  $.postJSON(BASE_REST_URL+"/presentacion/save",JSON.stringify(ko.toJS(serializado))).done(function(result) { 
  		 $('#editPresentacion').modal('hide');
         self.presentaciones.push(ko.mapping.fromJS(result)); 
	  }).fail(function(){alert("Ocurrio un error al salvar");});

    }else{
      $('#submitOculto').trigger('click');
    }
   }

   self.saveFraccion = function(){
       
    var serializado=JSON.parse(ko.mapping.toJSON(self.selected));
      serializado.producto = self.selectedProducto();

    serializado.unidad_de_medida = self.selectedUnidad_de_medida();
    serializado.cantidad = self.cantidad_fraccionada();

    var $myForm = $('#createPresentacionForm');
    if ($myForm[0].checkValidity()) {
    
     	  $.ajax(BASE_REST_URL+"/presentacion/saveFraccion", {
     		    data: {'data': JSON.stringify(ko.toJS(serializado)), 'cantidad_original': self.selected.cantidad(), 'paquete': self.selectedPaqueteId() },
                type: "POST",
                error: function(result){
                  alert("Ocurrio un error al salvar");
                },
                success: function(result) { 
                  $('#fraccionarPresentacion').modal('hide');
                  self.presentaciones.push(ko.mapping.fromJS(result)); 
                }
        });
    }else{
      $('#submitFraccion').trigger('click');
    }
   }   
   
   
   


   self.update = function(){
      var serializado=JSON.parse(ko.mapping.toJSON(self.selected));
    serializado.producto = self.selectedProducto();
    serializado.lote = self.selectedLote();
    serializado.proveedor = self.selectedProveedor();
    serializado.lote.fechaDeElaboracion= moment(serializado.lote.fechaDeElaboracion).format('YYYY-MM-DD');
    serializado.lote.fechaDeVencimiento= moment(serializado.lote.fechaDeVencimiento).format('YYYY-MM-DD');
    serializado.date_create= moment().format('YYYY-MM-DD');
    serializado.date_update= moment().format('YYYY-MM-DD');


    serializado.material = self.selectedMaterial();
    serializado.proveedor = self.selectedProveedor();
    serializado.unidad_de_medida = self.selectedUnidad_de_medida();
  
      var $myForm = $('#editPresentacionForm');
      if ($myForm[0].checkValidity()) {
    	  
      	  $.postJSON(BASE_REST_URL+"/presentacion/save",JSON.stringify(ko.toJS(serializado))).done(function(result) { 
              self.selectedUnmapped.producto.id(result.producto.id);
              self.selectedUnmapped.producto.name(result.producto.name);
              self.selectedUnmapped.descripcion(result.descripcion);
              $('#editPresentacion').modal('hide');
     	  }).fail(function(){alert("Ocurrio un error al salvar");});

      }else{
        $('#submitOculto').trigger('click');
      }
   }

   self.getActives = function(){	   
	   $.getJSON(BASE_REST_URL+"/presentacion/getActives").done(function(result) { 
		   ko.mapping.fromJS(result, self.presentaciones);
	   });
   }
   
   self.getInactives = function(){
	   $.getJSON(BASE_REST_URL+"/presentacion/getInactives").done(function(result) { 
		   ko.mapping.fromJS(result, self.presentaciones);
	   });
   }

   self.getLotesByProducto = function(producto){	  
	 $.getJSON(BASE_REST_URL+"/lote/getLotesByProducto",JSON.stringify(producto)).done(function(result) { 
		 self.lotes(result);
         self.doNext(function(){self.selectedLoteId(self.selectedUnmapped.lote.id());});
     });
   }

  self.getStock = function(){
	  $.postJSON(BASE_REST_URL+"/paquete/getCantidadDisponibleByPresentacionId",JSON.stringify(self.selected.id())).done(function(result) { 
		    self.paraFraccionarSelected(result);
		    self.paraFraccionar(result);
	  });
  }

  self.getMaterialesByProducto = function(producto){
	  $.postJSON(BASE_REST_URL+"/material/getMaterialesByProducto",JSON.stringify(producto)).done(function(result) { 
		  self.materiales(result);
          self.doNext(function(){ self.selectedMaterialId(self.selectedUnmapped.material.id());});
	  });
  }
  self.getProveedoresByProductoId = function(producto_id,destino){
	  $.postJSON(BASE_REST_URL+"/proveedor/getProveedoresByProductoId",JSON.stringify(producto_id)).done(function(result) { 
          self.proveedores(result);
          self.doNext(function(){self.selectedProveedorId(self.selectedUnmapped.proveedor.id());});
	  });
  }
  
  self.getMedidasByProducto = function(producto){
       self.medidas(producto.unidadesDeMedida);

       self.doNext(
         function(){
            self.selectedMedidaId(self.selectedUnmapped.unidad_de_medida.id());
       });
  }

  self.desactivar = function(data){
	  $.postJSON(BASE_REST_URL+"/presentacion/desactivar",JSON.stringify({'data': self.selected.id(), 'message': data.messageDesactivar()})).done(function(result) { 
           $('#desactivarPresentacion').modal('hide');
           self.presentaciones.remove(self.selectedUnmapped);
	  });
  }
  
   self.activar = function(data){
	  $.postJSON(BASE_REST_URL+"/presentacion/activar",JSON.stringify({'data': self.selected.id(), 'message': data.messageDesactivar()})).done(function(result) { 
		   $('#desactivarPresentacion').modal('hide');
		   self.presentaciones.remove(self.selectedUnmapped);
	  });
   }

   self.fraccionar = function(data){
	 $('#alertaFraccionable').modal('hide');  
     self.createStep(1);      
     self.selectedPaqueteId(null);
	 $('#fraccionarPresentacion').modal('show');
   }

   self.doEditar = function(data){
	   $('#alertaFraccionable').modal('hide');
	   
	   $('#editPresentacion').modal('show');
   }

   self.editar = function(data){   	 
       self.selectedProductoId(data.producto.id());
       self.selectedMedidaId(data.unidad_de_medida.id());
       try {
           self.selectedLoteId(data.lote.id());

		} catch (e) {
			// TODO: handle exception
		}
       self.selectedMaterialId(data.material.id());
       
       self.createNew(false);
       self.step(1);
       self.selectedUnmapped = data;
       self.selected.producto(null);
       ko.mapping.fromJS(data, self.selected);
      if(data.fraccionable()){

    	  $('#alertaFraccionable').modal('show');
      }else{
    	  self.doEditar(data);
      }
   }
   
   self.create = function(data){

      self.selectedProductoId(null);
      self.selectedMedidaId(null);
      self.selectedLoteId(null);
      self.selectedMaterialId(null);
      self.selectedProveedorId(null);

      self.createNew(true);
      self.step(1) ;

      self.selectedUnmapped = data;
      ko.mapping.fromJS(new Presentacion, self.selected);
      $('#editPresentacion').modal('show');
   }

   self.doNext = function(callback){
    self.step(self.step()+1);
    callback();
   }
   
   self.doNextFraccionar = function(callback){
    self.createStep(self.createStep()+1);
    callback();
   }
   
	self.validarPaquete = function(paqueteId){
		
//	  $.ajax(BASE_REST_URL+"/presentacion/getPaqueteById", {
//            type: "POST",
//            data: {'presentacion': JSON.stringify(self.selected.id()), 'paquete': paqueteId },
//            success: function(result) {
//                 self.setFraccionamiento(self.selectedProducto());
//            },
//            error: function(error){
//            	 alert(error.responseText);
//            }
//      });
//	  
		var	result = ko.utils.arrayFirst(self.selected.paquetes(), function(pq) {
		    return paqueteId == pq.id();
		});
	
		if(result){
			self.setFraccionamiento(self.selectedProducto());
		}else{
			alert("El paquete no pertenece a la presentacion");
		}	
		
	}
	
	self.setFraccionamiento = function(data){
		
       self.selectedLoteId(self.selected.lote().id());
       self.selectedMaterialId(self.selected.material().id());
       self.selectedProveedorId(self.selected.proveedor().id());

  		self.selectedMedidaId(self.selected.unidad_de_medida().id());
  		self.medidas(ko.toJS(self.selected.producto().unidadesDeMedida));
  		self.createNew(false);
  		self.selectedMedidaId(self.selected.unidad_de_medida().id());
  		self.selectedUnmapped = data;
  		ko.mapping.fromJS(data, self.selected);
  		self.createStep(2);
	}   
   
   
	self.nextFraccionar = function(paso,data){
		 switch(paso)
      {
      case "form":
         self.validarPaquete(self.selectedPaqueteId());
        break;
      default:
        alert("Error inesperado de inesperanza total");
      }
	}   
   
   self.next = function(paso,data){
      
      switch(paso)
      {
      case "lote":

         self.getLotesByProducto(self.selectedProducto());
         break;
      case "material":
         self.getMaterialesByProducto(self.selectedProducto());
        break;
      case "medida":
          self.getMedidasByProducto(self.selectedProducto());
         break;      
      case "proveedor":
         self.getProveedoresByProductoId(self.selectedProducto().id);
         break;
      case "form":
         self.doNext();
        break;
      default:
        alert("Error inesperado de inesperanza total");
      }

   }
   self.back = function(data){
   	if (self.backeable())
   	self.step(self.step()-1);
      //return self.backeable() && self.soloActivos();
   }

   self.toggleActivos= function(){
	   self.soloActivos(!self.soloActivos());
	   if(self.soloActivos()){
		   self.getActives();
	   }else{
		   self.getInactives();
	   }
   }

   self.saltarLote= function(data){
	   self.getMaterialesByProducto(self.selectedProducto());
   }   
   self.doNothing= function(){
   }
   
   self.showdesactivar = function(){
   	self.messageDesactivar(null);
   	$('#editPresentacion').modal('hide');
   	$('#desactivarPresentacion').modal('show');
   }
   
}