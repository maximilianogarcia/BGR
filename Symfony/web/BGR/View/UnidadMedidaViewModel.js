function UnidadMedidaViewModel() {
   self = this;
   self.selectedUnmapped = null;
   self.unidadMedidas = ko.mapping.fromJS([new UnidadMedida()]);
   self.selected = ko.mapping.fromJS(new UnidadMedida());
   self.createNew = ko.observable(false);


   self.apply = function(){
     self.getAll(self.unidadMedidas);
 	  ko.applyBindings(self);
   }
   
   self.serialized = function(){
      return ko.mapping.toJSON(self.selected);
   }

   self.save = function(){
    var $myForm = $('#editUnityForm');
    if ($myForm[0].checkValidity()) {
     	  $.ajax("http://localhost/BGR/Symfony/web/app_dev.php/rest/unidadMedida/save", {
                data: {'data': self.serialized() },         
                type: "POST",
                error: function(result){
                  alert("Ocurrio un error al salvar");
                },
                success: function(result) { 
                  $('#editUnity').modal('hide');
                  self.unidadMedidas.push(ko.mapping.fromJS(result)); 
                }
        });
    }else{
      $('#submitOculto').trigger('click');
    }
   }


   self.update = function(){
      var $myForm = $('#editUnityForm');
      if ($myForm[0].checkValidity()) {
          $.ajax("http://localhost/BGR/Symfony/web/app_dev.php/rest/unidadMedida/update", {
                  data: {'data': self.serialized() },         
                  type: "PUT",
                  success: function(result) {
                    self.selectedUnmapped.name(result.name);
                    self.selectedUnmapped.descripcion(result.descripcion);
                    self.selectedUnmapped.equivalencia(result.equivalencia);
                    $('#editUnity').modal('hide');
                  }
            });
      }else{
        $('#submitOculto').trigger('click');
      }
   }

   self.getAll = function(destino){
     $.ajax("http://localhost/BGR/Symfony/web/app_dev.php/rest/unidadMedida/getAll", {
            type: "GET",
            success: function(result) { 
              self.map(result, destino)
            }
      });
   }

   self.map = function(origen, destino){
         ko.mapping.fromJS(origen, destino);
   }


   self.borrar = function(data){
     serializado=ko.mapping.toJSON(self.selected);
     $.ajax("http://localhost/BGR/Symfony/web/app_dev.php/rest/unidadMedida/delete", {
            data: {'data': serializado},         
            type: "DELETE",
            success: function(result) { 
               $('#editUnity').modal('hide');
               self.unidadMedidas.remove(self.selectedUnmapped); 
            }
      });
   }

   self.editar = function(data){
      self.createNew(false);
      self.selectedUnmapped = data;
      ko.mapping.fromJS(data, self.selected);
      $('#editUnity').modal('show');
   }

   self.create = function(data){
      self.createNew(true);
      ko.mapping.fromJS(new UnidadMedida, self.selected);
      self.selectedUnmapped = data;
      $('#editUnity').modal('show');
   }

}

