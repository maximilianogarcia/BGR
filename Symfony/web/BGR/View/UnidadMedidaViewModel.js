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
	      // var self = this;
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


   
   self.save = function(){
    var $myForm = $('#editUnityForm');
    self.selected.deriva_de(self.selectedMedida());
    if ($myForm[0].checkValidity()) {
     	  $.ajax(BASE_REST_URL+"/unidadMedida/save", {
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
      self.selected.deriva_de(self.selectedMedida());
      if ($myForm[0].checkValidity()) {
          $.ajax(BASE_REST_URL+"/unidadMedida/update", {
                  data: {'data': self.serialized() },         
                  type: "POST",
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

   self.getNoDivisibles = function(){
	     $.ajax(BASE_REST_URL+"/unidadMedida/getNoDivisibles", {
	            type: "GET",
	            success: function(result) { 
	            	self.noDivisibles(result);
	            }
	      });
    }
   
   
   self.getAll = function(destino){
     $.ajax(BASE_REST_URL+"/unidadMedida/getAll", {
            type: "GET",
            success: function(result) { 
            //  self.map(result, destino)
              destino(result);
            }
      });
   }

   self.map = function(origen, destino){
         ko.mapping.fromJS(origen, destino);
   }


   self.borrar = function(data){
     serializado=ko.mapping.toJSON(self.selected);
     $.ajax(BASE_REST_URL+"/unidadMedida/delete", {
            data: {'data': serializado},         
            type: "POST",
            success: function(result) { 
               $('#editUnity').modal('hide');
               self.unidadMedidas.remove(self.selectedUnmapped); 
            }
      });
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

