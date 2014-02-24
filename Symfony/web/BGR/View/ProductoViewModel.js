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
		     $.ajax(BASE_REST_URL+"/producto/getByCategoria", {
		            type: "POST",
		            data: {'data': self.selectedCategoriaId()},
		            success: function(result) {
		               self.productosByCategoria(result);
		            }
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
		   self.unidadesSelected = ko.mapping.fromJS(ko.toJS(data.unidad_de_medidas));
		   self.unidades = ko.mapping.fromJS(ko.toJS(data.unidad_de_medidas));
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
     	  $.ajax(BASE_REST_URL+"/producto/save", {
                data: {'data': JSON.stringify(serializado) },
                type: "POST",
                error: function(result){
                  alert("Ocurrio un error al salvar");
                },
                success: function(result) { 
                  $('#editProducto').modal('hide');
                  self.productos.push(ko.mapping.fromJS(result)); 
                }
        });
    }else{
      $('#submitOculto').trigger('click');
    }
   }


   self.update = function(){
      var serializado=JSON.parse(ko.mapping.toJSON(self.selected));
      serializado.categoria = self.selectedCategoria();
      serializado.unidad_de_medidas = ko.toJS(self.selected.unidad_de_medidas());
      serializado.proveedores = ko.toJS(self.selected.proveedores());

      
      
      var $myForm = $('#editProductForm');
      if ($myForm[0].checkValidity()) {
          $.ajax(BASE_REST_URL+"/producto/update", {
                  data: {'data': JSON.stringify(serializado) },
                  type: "POST",
                  success: function(result) {
                      self.selectedUnmapped.name(result.name);
                      self.selectedUnmapped.actualizador_precio(result.actualizador_precio);
                  self.selectedUnmapped.categoria.id(result.categoria.id);
                  self.selectedUnmapped.categoria.name(result.categoria.name);
                  self.selectedUnmapped.categoria.descripcion(result.descripcion);

                  self.selectedUnmapped.unidad_de_medidas(result.unidad_de_medidas);	
				  self.selectedUnmapped.proveedores(result.proveedores);	
	  
				  $('#editProduct').modal('hide');
                  },
                  error: function(error){
                      alert(error.responseText);
                  }
            });
      }else{
        $('#submitOculto').trigger('click');
      }
   }

   self.getAll = function(callback){
     $.ajax(BASE_REST_URL+"/producto/getAll", {
            type: "GET",
            success: function(result) {
                  callback(result);
            }
      });
   }

   self.borrar = function(data){
     serializado=ko.mapping.toJSON(self.selected);
     serializado.categoria = self.selectedCategoria();
     $.ajax(BASE_REST_URL+"/producto/delete", {
            data: {'data': serializado},
            type: "POST",
            success: function(result){
               $('#editProduct').modal('hide');
               self.productos.remove(self.selectedUnmapped);
            },
            error: function(error){
					alert(error.responseText);            
            }
      });
   }

   self.editar = function(data){
	   
      self.selectedCategoriaId(data.categoria.id());
      self.createNew(false);
      self.selectedUnmapped = data;
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
		self.selected.unidad_de_medidas.push(data);
   }
   
   self.unSelectUnidad = function(data){
		self.selected.unidad_de_medidas.remove(data);
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
	      viewModelProveedores.getAllProductoRelations(self.selected.id(),self.mapProductosProveedores);
	      $('#editProduct').modal('hide');
	      $('#editPrecioReposicion').modal('show');
  }
   self.savePrecio = function(data){
	      var viewModelProveedores = new ProveedorViewModel();
	      viewModelProveedores.saveRelation(data, self.desactivarBotonSavePrecio);
   }
   
   self.desactivarBotonSavePrecio = function(){
	   
   }
   

}