function ProductoViewModel() {
   var self = this;
   self.selectedUnmapped = null;
   self.productos = ko.mapping.fromJS([new Producto()]);
   self.selected = ko.mapping.fromJS(new Producto());
   self.createNew = ko.observable(false);
   self.categorias = ko.observableArray();

	 self.allUnidadDeMedidas = ko.observableArray();

   self.selectedCategoriaId = ko.observable(1);
   
   self.unidadesNotSelected = ko.observableArray();

   self.selectedCategoria = function() {
       var self = this;
       return ko.utils.arrayFirst(this.categorias(), function(categoria) {
           return self.selectedCategoriaId() == categoria.id;
       });
   }.bind(this);

   self.notSelectedUnidadDeMedidas = ko.observableArray();

   self.apply = function(){
     ko.applyBindings(self);
     self.getAll(self.mapProductos);
     var viewModelCategoria = new CategoriaViewModel();
     var viewModelUnidadMedida = new UnidadMedidaViewModel();

     viewModelCategoria.getAll(self.copiar);
     viewModelUnidadMedida.getAll(self.copiarUnidadMedida);
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
      var $myForm = $('#editProductForm');
      if ($myForm[0].checkValidity()) {
          $.ajax(BASE_REST_URL+"/producto/update", {
                  data: {'data': JSON.stringify(serializado) },
                  type: "PUT",
                  success: function(result) {
                    self.selectedUnmapped.name(result.name);
                    self.selectedUnmapped.precio_venta(result.precio_venta);
                    self.selectedUnmapped.precio_compra(result.precio_compra);
                    self.selectedUnmapped.categoria.id(result.categoria.id);
                    self.selectedUnmapped.categoria.name(result.categoria.name);
                    self.selectedUnmapped.categoria.descripcion(result.descripcion);
						  self.selectedUnmapped.unidad_de_medidas(result.unidad_de_medidas);	
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
            type: "DELETE",
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
      ko.mapping.fromJS(data, self.selected);
      $('#editProduct').modal('show');
   }


   self.create = function(data){
      self.selectedCategoriaId(0);
      self.createNew(true);
      self.selectedUnmapped = data;
      self.notSelectedUnidadDeMedidas(self.chargeUnidades(data));
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

}