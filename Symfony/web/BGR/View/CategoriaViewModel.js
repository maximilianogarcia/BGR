function CategoriaViewModel() {
   self = this;
   self.selectedUnmapped = null;
   self.categorias = ko.mapping.fromJS([new Categoria()]);
   self.selected = ko.mapping.fromJS(new Categoria());
   self.createNew = ko.observable(false);


   self.apply = function(){
     self.getAll();
 	  ko.applyBindings(self);
   }
   
   self.serialized = function(){
      return ko.mapping.toJSON(self.selected);
   }

   self.save = function(){
    var $myForm = $('#editCategoryForm');
    if ($myForm[0].checkValidity()) {
     	  $.ajax("http://localhost/BGR/Symfony/web/app_dev.php/rest/categoria/save", {
                data: {'data': self.serialized() },         
                type: "POST",
                error: function(result){
                  alert("Ocurrio un error al salvar");
                },
                success: function(result) { 
                  $('#editCategory').modal('hide');
                  self.categorias.push(ko.mapping.fromJS(result)); 
                }
        });
    }else{
      $('#submitOculto').trigger('click');
    }
   }


   self.update = function(){
      var $myForm = $('#editCategoryForm');
      if ($myForm[0].checkValidity()) {
          $.ajax("http://localhost/BGR/Symfony/web/app_dev.php/rest/categoria/update", {
                  data: {'data': self.serialized() },         
                  type: "PUT",
                  success: function(result) {
                    self.selectedUnmapped.name(result.name);
                    self.selectedUnmapped.descripcion(result.descripcion);
                    $('#editCategory').modal('hide');
                  }
            });
      }else{
        $('#submitOculto').trigger('click');
      }
   }

   self.getAll = function(){
     $.ajax("http://localhost/BGR/Symfony/web/app_dev.php/rest/categoria/getAll", {
            type: "GET",
            success: function(result) { 
                  ko.mapping.fromJS(result, self.categorias);
            }
      });
   }

   self.borrar = function(data){
     serializado=ko.mapping.toJSON(self.selected);
     $.ajax("http://localhost/BGR/Symfony/web/app_dev.php/rest/categoria/delete", {
            data: {'data': serializado},         
            type: "DELETE",
            success: function(result) { 
               $('#editCategory').modal('hide');
               self.categorias.remove(self.selectedUnmapped); 
            }
      });
   }

   self.editar = function(data){
      self.createNew(false);
      self.selectedUnmapped = data;
      ko.mapping.fromJS(data, self.selected);
      $('#editCategory').modal('show');
   }

   self.create = function(data){
      self.createNew(true);
      ko.mapping.fromJS(new Categoria, self.selected);
      self.selectedUnmapped = data;
      $('#editCategory').modal('show');
   }

}

