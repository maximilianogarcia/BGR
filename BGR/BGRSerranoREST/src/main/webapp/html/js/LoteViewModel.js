function LoteViewModel() {
   var self = this;
   self.selectedUnmapped = null;
   self.lotes = ko.mapping.fromJS([new Lote()]);
   self.selected = ko.mapping.fromJS(new Lote());
   self.createNew = ko.observable(false);
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


   self.productos = ko.observableArray();
   self.proveedores = ko.observableArray();

   self.selectedProductoId = ko.observable(null);
   self.selectedProveedorId = ko.observable(null);
 
   self.hayProductoSeleccionado = ko.computed(function(){
     return self.selectedProductoId() != null;
   });
   
   self.hayProveedorSeleccionado = ko.computed(function(){
	     return self.selectedProveedorId() != null;
   });
   
   self.selectedProducto = function() {
       var self = this;
       return ko.utils.arrayFirst(this.productos(), function(producto) {
           return self.selectedProductoId() == producto.id;
       });
   }.bind(this);

   self.selectedProveedor = function() {
       var self = this;
       return ko.utils.arrayFirst(this.proveedores(), function(proveedor) {
           return self.selectedProveedorId() == proveedor.id;
       });
   }.bind(this);


   self.apply = function(){
     ko.applyBindings(self);
     self.getAll();
     var viewModelProducto = new ProductoViewModel();
     viewModelProducto.getAll(self.copiar);
   }
   
   self.serialized = function(){
      return ko.mapping.toJSON(self.selected);
   }

   self.copiar =function(destino){
        self.productos(destino);
   }

   self.copiarProveedor =function(destino){
       self.proveedores(destino);
       self.selectedProveedorId(self.selected.proveedor_id());
  }
   
   self.save = function(){
    var serializado=JSON.parse(ko.mapping.toJSON(self.selected));
    serializado.producto = self.selectedProducto();
    serializado.proveedor_id = self.selectedProveedorId()
    serializado.productoId = self.selectedProductoId();

    var $myForm = $('#editLoteForm');
    if ($myForm[0].checkValidity()) {    	
    	
    	$.postJSON(BASE_REST_URL+"/lote/save",JSON.stringify(serializado)).done(function(result) { 
             $('#editLote').modal('hide');
             self.lotes.push(ko.mapping.fromJS(result)); 
	    }).fail(function(){alert("Ocurrio un error al salvar");});

    }else{
      $('#submitOculto').trigger('click');
    }
   }


   self.update = function(){
      var serializado=JSON.parse(ko.mapping.toJSON(self.selected));
      serializado.producto = self.selectedProducto();
      serializado.proveedor = self.selectedProveedor();
      var $myForm = $('#editLoteForm');
      if ($myForm[0].checkValidity()) {    	  
    	  
    	   $.postJSON(BASE_REST_URL+"/lote/update",JSON.stringify(serializado)).done(function(result) { 
    		     self.selectedUnmapped.precio_compra(result.precio_compra);
                 self.selectedUnmapped.producto.id(result.producto.id);
                 self.selectedUnmapped.proveedor.id(result.proveedor.id);
                 self.selectedUnmapped.producto.name(result.producto.name);
                 self.selectedUnmapped.descripcion(result.descripcion);
                 $('#editLote').modal('hide');
    	   });

      }else{
        $('#submitOculto').trigger('click');
      }
   }

   self.getAll = function(){
	   $.getJSON(BASE_REST_URL+"/lote/getAll").done(function(result) { 
		   ko.mapping.fromJS(result, self.lotes);
	   });
   }

   self.desactivar = function(data){
     
	 $.postJSON(BASE_REST_URL+"/lote/desactivar",JSON.stringify(self.selected.id())).done(function(result) { 
		 $('#editLote').modal('hide');
         self.lotes.remove(self.selectedUnmapped);  
	 }).fail(function(error){ alert(error.responseText);});
   }

   self.editar = function(data){
      self.selectedProductoId(data.productoId());
      self.createNew(false);
      self.step(1);
      self.selectedUnmapped = data;
      self.selected.producto(null);
      ko.mapping.fromJS(data, self.selected);
      $('#editLote').modal('show');
   }


   self.create = function(data){

      self.selectedProductoId(null);
      self.createNew(true);
      self.step(1);

      self.selectedUnmapped = data;
      ko.mapping.fromJS(new Lote, self.selected);
      $('#editLote').modal('show');
   }

       self.doNext = function(callback){
   	    callback();
	    self.step(self.step()+1);
	   }
	   self.next = function(paso,data){
	      
	      switch(paso)
	      {
	      case "proveedor":
	         self.getProveedores();
	         break
	      case "form":
	         self.doNext( self.doNothing );
	        break;
	      default:
	        alert("Error inesperado de inesperanza total");
	      }
	   }
	   self.back = function(data){
		      self.step(self.step()-1)
	   }
	   
   self.getProveedores = function(){
	     var viewModelProveedores = new ProveedorViewModel( );
	     viewModelProveedores 		  
   	      self.doNext(function(){
   		      viewModelProveedores.getProveedoresByProductoId(self.selectedProductoId(),self.copiarProveedor);
   	      });
   }
   self.doNothing = function(){}

}