function UnidadMedidaViewModel() {
   var self = this;
   self.selectedUnmapped = null;
   self.unidadMedidas = ko.mapping.fromJS([new UnidadMedida()]);
   self.noDivisibles= ko.observableArray([new UnidadMedida()]);
   self.selected = ko.mapping.fromJS(new UnidadMedida());
   self.createNew = ko.observable(false);

   self.selectedMedidaId = ko.observable(null);
   self.hayMedidaSeleccionado = ko.computed(function(){
	     return self.selectedMedidaId() != null;
	   });
   
   self.selectedMedida = function() {
       return ko.utils.arrayFirst(this.noDivisibles(), function(um) {
           return self.selectedMedidaId() == um.id;
       });
   }.bind(this);


   self.apply = function(){
     ko.applyBindings(self);
     self.getAll(self.mapUnidadMedidas);
     self.getNoDivisibles(self.noDivisibles);    
   }
   
   self.mapUnidadMedidas =function(data){
        ko.mapping.fromJS(data, self.unidadMedidas);
   }
   
   self.serialized = function(){
      return ko.mapping.toJSON(self.selected);
   }

   self.getAll = function(callback){
	  $.getJSON(BASE_REST_URL+"/unidadDeMedida/getAll", function(result){ callback(result);});
   }
   
   self.save = function(){
    var $myForm = $('#editUnityForm');
    self.selected.deriva_de(self.selectedMedida());
    if ($myForm[0].checkValidity()) {
    	
    	$.postJSON(BASE_REST_URL+"/unidadDeMedida/save",self.serialized()).done(function(result) { 
            $('#editUnity').modal('hide');
            self.unidadMedidas.push(ko.mapping.fromJS(result)); 
          }).fail(function(){ alert("Ocurrio un error al salvar"); });
   
    }else{
      $('#submitOculto').trigger('click');
    }
   }


   self.update = function(){
      var $myForm = $('#editUnityForm');
      self.selected.deriva_de(self.selectedMedida());
      if ($myForm[0].checkValidity()) {
    	  
	 	  $.postJSON(BASE_REST_URL+"/unidadDeMedida/save",self.serialized()).done(function(result) { 
              self.selectedUnmapped.name(result.name);
              self.selectedUnmapped.descripcion(result.descripcion);
              self.selectedUnmapped.equivalencia(result.equivalencia);
              $('#editUnity').modal('hide');
          }).fail(function(){ alert("Ocurrio un error al salvar"); });

      }else{
        $('#submitOculto').trigger('click');
      }
   }

   self.getNoDivisibles = function(){
	   $.getJSON(BASE_REST_URL+"/unidadDeMedida/getNoDivisibles", function(result){ self.noDivisibles(result);});
   }

   self.map = function(origen, destino){
         ko.mapping.fromJS(origen, destino);
   }


   self.borrar = function(data){
     serializado=ko.mapping.toJSON(self.selected.id);
     $.deleteJSON(BASE_REST_URL+"/unidadDeMedida/delete",serializado).done(function(result) { 
         $('#editUnity').modal('hide');
         self.unidadMedidas.remove(self.selectedUnmapped);
	  }).fail(function(error){ alert(error.responseText);});
   }

   self.editar = function(data){
      ko.mapping.fromJS(new UnidadMedida(), self.selected);
      self.createNew(false);
      self.selectedUnmapped = data;
      if(data.deriva_de){
         self.selectedMedidaId(data.deriva_de.id());
      }
      ko.mapping.fromJS(data, self.selected);
      $('#editUnity').modal('show');
   }

   self.create = function(data){
      self.createNew(true);
      self.selectedMedidaId(null);
      ko.mapping.fromJS(new UnidadMedida(), self.selected);
      self.selectedUnmapped = data;
      $('#editUnity').modal('show');
   }

}

