function PresentacionViewModel() {
   var self = this;
   self.selectedUnmapped = null;
   self.presentaciones = ko.mapping.fromJS([new Presentacion()]);
   self.selected = ko.mapping.fromJS(new Presentacion());
   self.createNew = ko.observable(false);
   self.soloActivos = ko.observable(true);
   self.paraFraccionarSelected= ko.observable();
   self.paraFraccionar = ko.observable();
   
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


   self.productos = ko.observableArray();
   self.lotes =  ko.mapping.fromJS([new Lote()]);

   self.materiales = ko.mapping.fromJS([new Material()]);
   self.medidas    = ko.mapping.fromJS([new UnidadMedida()]);

   self.selectedProductoId = ko.observable(null);
   self.selectedMedidaId = ko.observable(null);
   self.selectedLoteId = ko.observable(null);
   self.selectedMaterialId = ko.observable(null);
 
   self.hayProductoSeleccionado = ko.computed(function(){
     return self.selectedProductoId() != null;
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
    serializado.lote = self.selectedLote();
    serializado.lote.fecha_de_elaboracion= moment(serializado.lote.fecha_de_elaboracion).format('YYYY-MM-DD');
    serializado.lote.fecha_de_vencimiento= moment(serializado.lote.fecha_de_vencimiento).format('YYYY-MM-DD');
    serializado.date_create= moment().format('YYYY-MM-DD');
    serializado.date_update= moment().format('YYYY-MM-DD');


    serializado.material = self.selectedMaterial();
    serializado.unidad_de_medida = self.selectedUnidad_de_medida();
  	 	


    var $myForm = $('#editPresentacionForm');
    if ($myForm[0].checkValidity()) {
     	  $.ajax(BASE_REST_URL+"/presentacion/save", {
     		    data: {'data': JSON.stringify(ko.toJS(serializado)) },
                type: "POST",
                error: function(result){
                  alert("Ocurrio un error al salvar");
                },
                success: function(result) { 
                  $('#editPresentacion').modal('hide');
                  self.presentaciones
            .push(ko.mapping.fromJS(result)); 
                }
        });
    }else{
      $('#submitOculto').trigger('click');
    }
   }


   self.update = function(){
      var serializado=JSON.parse(ko.mapping.toJSON(self.selected));
    serializado.producto = self.selectedProducto();
    serializado.lote = self.selectedLote();
    serializado.lote.fecha_de_elaboracion= moment(serializado.lote.fecha_de_elaboracion).format('YYYY-MM-DD');
    serializado.lote.fecha_de_vencimiento= moment(serializado.lote.fecha_de_vencimiento).format('YYYY-MM-DD');
    serializado.date_create= moment().format('YYYY-MM-DD');
    serializado.date_update= moment().format('YYYY-MM-DD');


    serializado.material = self.selectedMaterial();
    serializado.unidad_de_medida = self.selectedUnidad_de_medida();
  
      var $myForm = $('#editPresentacionForm');
      if ($myForm[0].checkValidity()) {
          $.ajax(BASE_REST_URL+"/presentacion/update", {
                  data: {'data': JSON.stringify(ko.toJS(serializado)) },
                  type: "PUT",
                  success: function(result) {
                    //self.selectedUnmapped.name(result.name);
                    self.selectedUnmapped.producto.precio_venta(result.producto.precio_venta);
                    self.selectedUnmapped.producto.precio_compra(result.producto.precio_compra);
                    self.selectedUnmapped.producto.id(result.producto.id);
                    self.selectedUnmapped.producto.name(result.producto.name);
                    self.selectedUnmapped.descripcion(result.descripcion);

                    $('#editPresentacion').modal('hide');
                  }
            });
      }else{
        $('#submitOculto').trigger('click');
      }
   }

   self.getActives = function(){
     $.ajax(BASE_REST_URL+"/presentacion/getActives", {
            type: "GET",
            success: function(result) {
                  ko.mapping.fromJS(result, self.presentaciones
              );
            }
      });
   }
   self.getInactives = function(){
	   $.ajax(BASE_REST_URL+"/presentacion/getInactives", {
		   type: "GET",
		   success: function(result) {
			   ko.mapping.fromJS(result, self.presentaciones
			   );
		   }
	   });
   }


  self.getLotesByProducto = function(producto){
     $.ajax(BASE_REST_URL+"/lote/getLotesByProducto", {
            type: "POST",
            data: {'data': JSON.stringify(producto)},
            success: function(result) {
               self.lotes(result);
               self.doNext(
                  function(){
                    self.selectedLoteId(self.selectedUnmapped.lote.id());
                  }
               );
            }
      });
   }

  self.getStock = function(){
	  $.ajax(BASE_REST_URL+"/paquete/getCantidadDisponibleByPresentacionId", {
		  type: "POST",
		  data: {'data': self.selected.id()},
		  success: function(result) {
			  self.paraFraccionarSelected(result);
			  self.paraFraccionar(result);
		  }
	  });
  }

  self.getMaterialesByProducto = function(producto){

     $.ajax(BASE_REST_URL+"/material/getMaterialesByProducto", {
            type: "POST",
            data: {'data': JSON.stringify(producto)},
            success: function(result) {
               self.materiales(result);
               self.doNext(
                  function(){
                    self.selectedMaterialId(self.selectedUnmapped.material.id());
                  }
                );
            }
      });

  }
  self.getMedidasByProducto = function(producto){
       self.medidas(producto.unidad_de_medidas);

       self.doNext(
         function(){
            self.selectedMedidaId(self.selectedUnmapped.unidad_de_medida.id());
       });
  }



   self.desactivar = function(data){
   //  serializado=ko.mapping.toJSON(self.selected);
     $.ajax(BASE_REST_URL+"/presentacion/desactivar", {
            data: {'data': self.selected.id()},
            type: "PUT",
            success: function(result){
               $('#editPresentacion').modal('hide');
               self.presentaciones
          .remove(self.selectedUnmapped);
            }
      });
   }
   self.activar = function(data){
	   //  serializado=ko.mapping.toJSON(self.selected);
	   $.ajax(BASE_REST_URL+"/presentacion/activar", {
		   data: {'data': self.selected.id()},
		   type: "PUT",
		   success: function(result){
			   $('#editPresentacion').modal('hide');
			   self.presentaciones
			   .remove(self.selectedUnmapped);
		   }
	   });
   }

   self.fraccionar = function(data){
 	   self.getStock();
      $('#alertaFraccionable').modal('hide');      
      self.selectedMedidaId(self.selected.unidad_de_medida().id());      
      self.createNew(false);
      self.medidas(ko.toJS(self.selected.producto().unidad_de_medidas));
      self.selectedMedidaId(self.selected.unidad_de_medida().id());
      self.selectedUnmapped = data;
      ko.mapping.fromJS(data, self.selected);
      self.step(6);      
	  $('#editPresentacion').modal('show');
   
   }

   self.doEditar = function(data){
	   $('#alertaFraccionable').modal('hide');
	   
	   $('#editPresentacion').modal('show');
   }

   self.editar = function(data){   	 
       self.selectedProductoId(data.producto.id());
       self.selectedMedidaId(data.unidad_de_medida.id());
       self.selectedLoteId(data.lote.id());
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
      self.selectedCategoriaId(0);
      self.selectedUnmapped = data;
      self.notSelectedUnidadDeMedidas(self.chargeUnidades(data));
      ko.mapping.fromJS(new Producto, self.selected);
      $('#editProduct').modal('show');
   }

   self.doNext = function(callback){
    self.step(self.step()+1);
    callback();
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
      case "form":
         self.doNext();
        break;
      default:
        alert("Error inesperado de inesperanza total");
      }

   }
   self.back = function(data){
      return data.actuve() && self.soloActivos();
   }

   self.toggleActivos= function(){
	   self.soloActivos(!self.soloActivos());
	   if(self.soloActivos()){
		   self.getActives();
	   }else{
		   self.getInactives();
	   }
   }


}