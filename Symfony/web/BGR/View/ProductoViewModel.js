function ProductoViewModel() {
   self = this;
   self.selectedUnmapped = null;
   self.productos = ko.mapping.fromJS([new Producto()]);
   self.selected = ko.mapping.fromJS(new Producto());
   self.createNew = ko.observable(false);


   self.apply = function(){
     self.getAll();
 	   ko.applyBindings(self);
   }
   
   self.serialized = function(){
      return ko.mapping.toJSON(self.selected);
   }

   self.save = function(){
    var $myForm = $('#editProductForm');
    if ($myForm[0].checkValidity()) {
     	  $.ajax("http://localhost/BGR/Symfony/web/app_dev.php/rest/producto/save", {
                data: {'data': self.serialized() },
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
      var $myForm = $('#editProductoForm');
      if ($myForm[0].checkValidity()) {
          $.ajax("http://localhost/BGR/Symfony/web/app_dev.php/rest/producto/update", {
                  data: {'data': self.serialized() },
                  type: "PUT",
                  success: function(result) {
                    self.selectedUnmapped.name(result.name);
                    self.selectedUnmapped.descripcion(result.descripcion);
                    $('#editProducto').modal('hide');
                  }
            });
      }else{
        $('#submitOculto').trigger('click');
      }
   }

   self.getAll = function(){
     $.ajax("http://localhost/BGR/Symfony/web/app_dev.php/rest/producto/getAll", {
            type: "GET",
            success: function(result) {
                  ko.mapping.fromJS(result, self.productos);
            }
      });
   }

   self.borrar = function(data){
     serializado=ko.mapping.toJSON(self.selected);
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
      self.createNew(false);
      self.selectedUnmapped = data;
      ko.mapping.fromJS(data, self.selected);
      $('#editProduct').modal('show');
   }

   self.create = function(data){
      self.createNew(true);
      ko.mapping.fromJS(new Producto, self.selected);
      self.selectedUnmapped = data;
      $('#editProduct').modal('show');
   }

}