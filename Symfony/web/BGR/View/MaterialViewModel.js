function MaterialViewModel() {
   var self = this;
   self.selectedUnmapped = null;
   self.materiales = ko.mapping.fromJS([new Material()]);
   self.selected = ko.mapping.fromJS(new Material());
   self.createNew = ko.observable(false);


   self.apply = function(){
 	   ko.applyBindings(self);
 	   self.getAll(self.mapMateriales); 	  
   }

   self.mapMateriales =function(data){
        ko.mapping.fromJS(data, self.materiales);
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
     	  $.ajax(BASE_REST_URL+"/material/save", {
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
          $.ajax(BASE_REST_URL+"/material/update", {
                  data: {'data': self.serialized() },         
                  type: "POST",
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
     $.ajax(BASE_REST_URL+"/material/getAll", {
            type: "GET",
            success: function(result) { 
                  callback(result);
            }
      });
   }

   self.borrar = function(data){
     serializado=ko.mapping.toJSON(self.selected);
     $.ajax(BASE_REST_URL+"/material/delete", {
            data: {'data': serializado},         
            type: "POST",
            success: function(result) { 
               $('#editMaterial').modal('hide');
               self.materiales.remove(self.selectedUnmapped); 
            },
			   error: function(error){
				  alert(error.responseText);			   
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

