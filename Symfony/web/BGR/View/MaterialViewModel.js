function MaterialViewModel() {
   var self = this;
   self.selectedUnmapped = null;
   self.materiales = ko.mapping.fromJS([new Material()]);
   self.selected = ko.mapping.fromJS(new Material());
   self.createNew = ko.observable(false);


   self.apply = function(){
     self.getAll(self.materiales);
 	   ko.applyBindings(self);
   }

   self.serialized = function(){
      return ko.mapping.toJSON(self.selected);
   }

   self.map = function(destino){
         ko.mapping.fromJS(self.selected, destino);
   }

   self.save = function(){
    var $myForm = $('#editMaterialForm');
    if ($myForm[0].checkValidity()) {
     	  $.ajax("http://localhost/BGR/Symfony/web/app_dev.php/rest/material/save", {
                data: {'data': self.serialized() },         
                type: "POST",
                error: function(result){
                  alert("Ocurrio un error al salvar");
                },
                success: function(result) { 
                  $('#editMaterial').modal('hide');
                  self.materiales.push(ko.mapping.fromJS(result)); 
                }
        });
    }else{
      $('#submitOculto').trigger('click');
    }
   }


   self.update = function(){
      var $myForm = $('#editMaterialForm');
      if ($myForm[0].checkValidity()) {
          $.ajax("http://localhost/BGR/Symfony/web/app_dev.php/rest/material/update", {
                  data: {'data': self.serialized() },         
                  type: "PUT",
                  success: function(result) {
                    self.selectedUnmapped.name(result.name);
                    self.selectedUnmapped.descripcion(result.descripcion);
                    self.selectedUnmapped.peso(result.peso);
                    self.selectedUnmapped.alto(result.alto);
                    self.selectedUnmapped.largo(result.largo);
                    self.selectedUnmapped.ancho(result.ancho);

                    $('#editMaterial').modal('hide');
                  }
            });
      }else{
        $('#submitOculto').trigger('click');
      }
   }

   self.getAll = function(callback){
     $.ajax("http://localhost/BGR/Symfony/web/app_dev.php/rest/material/getAll", {
            type: "GET",
            success: function(result) { 
                  callback(result);
            }
      });
   }

   self.borrar = function(data){
     serializado=ko.mapping.toJSON(self.selected);
     $.ajax("http://localhost/BGR/Symfony/web/app_dev.php/rest/material/delete", {
            data: {'data': serializado},         
            type: "DELETE",
            success: function(result) { 
               $('#editMaterial').modal('hide');
               self.materiales.remove(self.selectedUnmapped); 
            }
      });
   }

   self.editar = function(data){
      self.createNew(false);
      self.selectedUnmapped = data;
      ko.mapping.fromJS(data, self.selected);
      $('#editMaterial').modal('show');
   }

   self.create = function(data){
      self.createNew(true);
      ko.mapping.fromJS(new Material, self.selected);
      self.selectedUnmapped = data;
      $('#editMaterial').modal('show');
   }

}

