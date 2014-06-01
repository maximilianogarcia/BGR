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

   self.getAll = function(callback){
	     $.getJSON(BASE_REST_URL+"/material/getAll", function(result){ callback(result);});
   }
   
   self.save = function(){
    var $myForm = $('#editMaterialForm');
    if ($myForm[0].checkValidity()) {
     	
    	$.postJSON(BASE_REST_URL+"/material/save",self.serialized()).done(function(result) { 
            $('#editMaterial').modal('hide');
            self.materiales.push(ko.mapping.fromJS(result));
          }).fail(function(){ alert("Ocurrio un error al salvar"); });
    	
    }else{
      $('#submitOculto').trigger('click');
    }
   }


   self.update = function(){
      var $myForm = $('#editMaterialForm');
      if ($myForm[0].checkValidity()) {
    	  
      	$.postJSON(BASE_REST_URL+"/material/save",self.serialized()).done(function(result) { 
      	     self.selectedUnmapped.name(result.name);
             self.selectedUnmapped.descripcion(result.descripcion);
             self.selectedUnmapped.peso(result.peso);
             self.selectedUnmapped.alto(result.alto);
             self.selectedUnmapped.largo(result.largo);
             self.selectedUnmapped.ancho(result.ancho);
             $('#editMaterial').modal('hide');
          }).fail(function(){ alert("Ocurrio un error al salvar"); });
      	
      }else{
        $('#submitOculto').trigger('click');
      }
   }

   self.borrar = function(data){
      serializado=ko.mapping.toJSON(self.selected.id);
     
	  $.deleteJSON(BASE_REST_URL+"/material/delete",serializado).done(function(result) { 
          $('#editMaterial').modal('hide');
          self.materiales.remove(self.selectedUnmapped); 
	  }).fail(function(error){ alert(error.responseText);});
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

