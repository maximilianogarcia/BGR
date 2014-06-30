function ProductoViewModel() {
   var self = this;
   self.selectedUnmapped = null;
   self.productos = ko.mapping.fromJS([new Producto()]);
   self.productosByCategoria = ko.observableArray();
   self.selected = ko.mapping.fromJS(new Producto());
   self.createNew = ko.observable(false);
   self.categorias = ko.observableArray();

   self.allUnidadDeMedidas = ko.observableArray();
   self.allProveedores= ko.observableArray();

   self.selectedCategoriaId = ko.observable(1);
   
   self.unidadesNotSelected = ko.observableArray();
   self.proveedoresNotSelected = ko.observableArray();

   self.productoProveedor= ko.mapping.fromJS([new ProductoProveedor()]);
   
   self.hayCategoriaSeleccionada = ko.computed(function(){
	     return self.selectedCategoriaId() != null;
   });
   self.selectedCategoria = function() {
       var self = this;
       return ko.utils.arrayFirst(this.categorias(), function(categoria) {
           return self.selectedCategoriaId() == categoria.id;
       });
   }.bind(this);

   self.loadProductoByCategoria = function() {
		 if(self.hayCategoriaSeleccionada()){
			 $.postJSON(BASE_REST_URL+"/producto/getByCategoria",JSON.stringify(self.selectedCategoriaId())).done(function(result) { 
				 	self.productosByCategoria(result);
		     });
		 }
   }
   
   self.notSelectedUnidadDeMedidas = ko.observableArray();
   self.notSelectedProveedores= ko.observableArray();
   
   self.apply = function(){
     ko.applyBindings(self);
     self.getAll(self.mapProductos);
     var viewModelCategoria = new CategoriaViewModel();
     var viewModelUnidadMedida = new UnidadMedidaViewModel();
     var viewModelProveedores = new ProveedorViewModel();

     
     viewModelCategoria.getAll(self.copiar);
     viewModelUnidadMedida.getAll(self.copiarUnidadMedida);
     viewModelProveedores.getAll(self.copiarProveedores)
     
   }

	self.containThisId = function(array,id){
		var contains = false;
		$.each(array, function( index, value ) {
			if(value.id() === id){
				contains = value;			
			}  			
		});
		return contains;
	}    
   
	self.chargeUnidades = function(data){
	  
		self.unidadesNotSelected = ko.mapping.fromJS(ko.toJS(self.allUnidadDeMedidas));
		try{
		   self.unidadesSelected = ko.mapping.fromJS(ko.toJS(data.unidadesDeMedida));
		   self.unidades = ko.mapping.fromJS(ko.toJS(data.unidadesDeMedida));
		   $.each(self.unidades(), function( index, value ) {

			   self.unidadesNotSelected.remove(self.containThisId(self.unidadesNotSelected(),value.id()));	
		   });
		}catch(err){
			return self.unidadesNotSelected();
		}
		return self.unidadesNotSelected();
	}   

	
	
	
	self.chargeProveedores = function(data){
		  
		self.proveedoresNotSelected = ko.mapping.fromJS(ko.toJS(self.allProveedores));
		try{
		   self.proveedoresSelected = ko.mapping.fromJS(ko.toJS(data.proveedores));
		   self.proveedores = ko.mapping.fromJS(ko.toJS(data.proveedores));
		   $.each(self.proveedores(), function( index, value ) {

			   self.proveedoresNotSelected.remove(self.containThisId(self.proveedoresNotSelected(),value.id()));	
		   });
		}catch(err){
			return self.proveedoresNotSelected();
		}
		return self.proveedoresNotSelected();
	}   
	
	
	
	
   self.serialized = function(){
      return ko.mapping.toJSON(self.selected);
   }

   self.copiar =function(destino){
        self.categorias(destino);
   }

   self.mapProductos =function(data){
        ko.mapping.fromJS(data, self.productos);
   }
   
   self.copiarUnidadMedida =function(destino){
        self.allUnidadDeMedidas(destino);
   }

   self.copiarProveedores=function(destino){
       self.allProveedores(destino);
  }
 
   self.mapProductosProveedores=function(data){
	   ko.mapping.fromJS(data, self.productoProveedor);
  }   

   self.save = function(){
    var serializado=JSON.parse(ko.mapping.toJSON(self.selected));
    serializado.categoria = self.selectedCategoria();
    var $myForm = $('#editProductForm');
    if ($myForm[0].checkValidity()) {
    	
    	$.postJSON(BASE_REST_URL+"/producto/update",JSON.stringify(serializado)).done(function(result) { 
    		 $('#editProduct').modal('hide');
             self.productos.push(ko.mapping.fromJS(result)); 
          }).fail(function(){ alert("Ocurrio un error al salvar"); });
    	
    }else{
      $('#submitOculto').trigger('click');
    }
   }


   self.update = function(){
      var serializado=JSON.parse(ko.mapping.toJSON(self.selected));
      serializado.categoria = self.selectedCategoria();
      serializado.unidadesDeMedida = ko.toJS(self.selected.unidadesDeMedida());
      serializado.proveedores = ko.toJS(self.selected.proveedores());
      
      serializado.productoProveedor = new Array();
      for ( j = 0; j < self.selected.proveedores().length; j++){
    	  var pp = new ProductoProveedor();    	  
    	  pp.producto_id(self.selected.id());
    	  pp.proveedor(self.selected.proveedores()[j]);
    	  pp.producto(JSON.parse(ko.mapping.toJSON(self.selected)));
    	  pp.precio_reposicion(self.selected.actualizador_precio());
     	  serializado.productoProveedor.push(pp);
      }
      serializado.productoProveedor = JSON.parse(ko.mapping.toJSON(serializado.productoProveedor));
      
      var $myForm = $('#editProductForm');
      if ($myForm[0].checkValidity()) {    	  
    	  
      	$.postJSON(BASE_REST_URL+"/producto/save",JSON.stringify(serializado)).done(function(result) {       		
      	  self.reloadData(result);	
		  $('#editProduct').modal('hide');
         }).fail(function(error){ alert(error.responseText); });
    	  
      }else{
        $('#submitOculto').trigger('click');
      }
   }

   self.getAll = function(callback){
	     $.getJSON(BASE_REST_URL+"/producto/getAll", function(result){ callback(result);});
   }

   self.borrar = function(data){
	  $.deleteJSON(BASE_REST_URL+"/producto/delete",JSON.stringify(self.selected.id())).done(function(result) { 
		  $('#editProduct').modal('hide');
          self.productos.remove(self.selectedUnmapped); 
	  }).fail(function(error){ alert(error.responseText);});
   }

   self.editar = function(data){
	   
      self.selectedCategoriaId(data.categoria.id());
      self.createNew(false);
      self.selectedUnmapped = data;
      self.selectedUnmapped.proveedores = ko.observableArray();
      
      for(var j = 0; j< data.productoProveedor().length; j++){
    	  self.selectedUnmapped.proveedores.push(data.productoProveedor()[j].proveedor);
      }

      self.selected.categoria(null);
      self.notSelectedUnidadDeMedidas(self.chargeUnidades(data));
      self.notSelectedProveedores(self.chargeProveedores(data));

      ko.mapping.fromJS(data, self.selected);
      $('#editProduct').modal('show');
   }


   self.create = function(data){
      self.selectedCategoriaId(0);
      self.createNew(true);
      self.selectedUnmapped = data;
      self.notSelectedUnidadDeMedidas(self.chargeUnidades(data));
      self.notSelectedProveedores(self.chargeProveedores(data));
      ko.mapping.fromJS(new Producto, self.selected);
      $('#editProduct').modal('show');
   }
   
   self.selectUnidad = function(data){   	
		self.notSelectedUnidadDeMedidas.remove(data);
		self.selected.unidadesDeMedida.push(data);
   }
   
   self.unSelectUnidad = function(data){
		self.selected.unidadesDeMedida.remove(data);
		self.notSelectedUnidadDeMedidas.push(data);  
   }

   self.selectProveedor = function(data){   	
		self.notSelectedProveedores.remove(data);
		self.selected.proveedores.push(data);
  }
   self.unSelectProveedor = function(data){
		self.selected.proveedores.remove(data);
		self.notSelectedProveedores.push(data);  
  }

   self.editPrecios = function(data){
	      var viewModelProveedores = new ProveedorViewModel();
	      self.getAllProductoRelations(self.selected.id(),self.showEditPrecios);
  }
   
   self.showEditPrecios = function(data){
	  self.mapProductosProveedores(data);
      $('#editProduct').modal('hide');
      $('#editPrecioReposicion').modal('show');
   }
   
   self.savePrecio = function(data){
	      var viewModelProveedores = new ProveedorViewModel();
	      data.producto = self.selected;
	      viewModelProveedores.saveRelation(data, self.desactivarBotonSavePrecio);
   }
   
   self.desactivarBotonSavePrecio = function(){
	   
   }
   

   self.getAllProductoRelations = function(producto_id, destino){
       $.postJSON(BASE_REST_URL+"/producto/getAllProductoRelations",JSON.stringify(producto_id)).
          done(function(result) { destino(result);});
   }
   
   self.reloadData = function(result){
	  self.selectedUnmapped.name(result.name);
      self.selectedUnmapped.actualizador_precio(result.actualizador_precio);
      self.selectedUnmapped.categoria.id(result.categoria.id);
      self.selectedUnmapped.categoria.name(result.categoria.name);
      self.selectedUnmapped.categoria.descripcion(result.descripcion);
      self.selectedUnmapped.unidadesDeMedida(result.unidadesDeMedida);
      
      self.selectedUnmapped.proveedores = ko.observableArray();
      for(var j = 0; j< result.productoProveedor.length; j++){
    	  self.selectedUnmapped.proveedores.push(result.productoProveedor[j].proveedor);
      }
      for(var j = 0; j < self.productos().length; j++){
    	  if (self.productos()[j].id() == self.selectedUnmapped.id() ){
    		  self.productos()[j] = self.selectedUnmapped;
    	  }
      }
      
      
   }
   

}