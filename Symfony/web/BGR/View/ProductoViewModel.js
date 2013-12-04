function ProductoViewModel() {
   var self = this;
   self.selectedUnmapped = null;
   self.productos = ko.mapping.fromJS([new Producto()]);
   self.selected = ko.mapping.fromJS(new Producto());
   self.createNew = ko.observable(false);
   self.categorias = ko.observableArray();

   self.selectedCategoriaId = ko.observable(1);


   self.selectedCategoria = function() {
       var self = this;
       return ko.utils.arrayFirst(this.categorias(), function(categoria) {
           return self.selectedCategoriaId() == categoria.id;
       });
   }.bind(this);


   self.apply = function(){
     ko.applyBindings(self);
     self.getAll(self.mapProductos);
     var viewModelCategoria = new CategoriaViewModel();
     viewModelCategoria.getAll(self.copiar);
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

   self.save = function(){
    var serializado=JSON.parse(ko.mapping.toJSON(self.selected));
    serializado.categoria = self.selectedCategoria();
  


    var $myForm = $('#editProductForm');
    if ($myForm[0].checkValidity()) {
     	  $.ajax("http://localhost/BGR/Symfony/web/app_dev.php/rest/producto/save", {
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
      var $myForm = $('#editProductForm');
      if ($myForm[0].checkValidity()) {
          $.ajax("http://localhost/BGR/Symfony/web/app_dev.php/rest/producto/update", {
                  data: {'data': JSON.stringify(serializado) },
                  type: "PUT",
                  success: function(result) {
                    self.selectedUnmapped.name(result.name);
                    self.selectedUnmapped.precio_venta(result.precio_venta);
                    self.selectedUnmapped.precio_compra(result.precio_compra);
                    self.selectedUnmapped.categoria.id(result.categoria.id);
                    self.selectedUnmapped.categoria.name(result.categoria.name);
                    self.selectedUnmapped.categoria.descripcion(result.descripcion);

                    $('#editProduct').modal('hide');
                  }
            });
      }else{
        $('#submitOculto').trigger('click');
      }
   }

   self.getAll = function(callback){
     $.ajax("http://localhost/BGR/Symfony/web/app_dev.php/rest/producto/getAll", {
            type: "GET",
            success: function(result) {
                  callback(result);
            }
      });
   }

   self.borrar = function(data){
     serializado=ko.mapping.toJSON(self.selected);
     serializado.categoria(self.selectedCategoria);
     $.ajax("http://localhost/BGR/Symfony/web/app_dev.php/rest/producto/delete", {
            data: {'data': serializado},
            type: "DELETE",
            success: function(result){
               $('#editProduct').modal('hide');
               self.productos.remove(self.selectedUnmapped);
            }
      });
   }

   self.editar = function(data){
      self.selectedCategoriaId(data.categoria.id());
      self.createNew(false);
      self.selectedUnmapped = data;
      self.selected.categoria(null);
      ko.mapping.fromJS(data, self.selected);
      $('#editProduct').modal('show');
   }


   self.create = function(data){
      self.selectedCategoriaId(0);
      self.createNew(true);
      self.selectedUnmapped = data;
      ko.mapping.fromJS(new Producto, self.selected);
      $('#editProduct').modal('show');
   }

}