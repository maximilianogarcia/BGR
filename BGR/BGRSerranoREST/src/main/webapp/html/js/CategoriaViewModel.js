function CategoriaViewModel() {
   var self = this;
   self.selectedUnmapped = null;
   self.categorias = ko.mapping.fromJS([new Categoria()]);
   self.selected = ko.mapping.fromJS(new Categoria());
   self.createNew = ko.observable(false);


   self.apply = function(){
     ko.applyBindings(self);
     self.getAll(self.mapCategorias); 	   
   }
   
   self.mapCategorias =function(data){
        ko.mapping.fromJS(data, self.categorias);
   }
   
   self.serialized = function(){
      return ko.mapping.toJSON(self.selected);
   }

   self.map = function(origen){
         ko.mapping.fromJS(self.selected, origen);
   }

   self.getAll = function(callback){
	     $.getJSON(BASE_REST_URL+"/categoria/getAll", function(result){ callback(result);});
   }
   
   self.save = function(){
    var $myForm = $('#editCategoryForm');
    if ($myForm[0].checkValidity()) {
    	
    	$.postJSON(BASE_REST_URL+"/categoria/save",self.serialized()).done(function(result) { 
            $('#editCategory').modal('hide');
            self.categorias.push(ko.mapping.fromJS(result)); 
          }).fail(function(){ alert("Ocurrio un error al salvar"); });
    }else{
      $('#submitOculto').trigger('click');
    }
   }

   self.update = function(){
      var $myForm = $('#editCategoryForm');
      if ($myForm[0].checkValidity()) {    	  
    	  
      	$.postJSON(BASE_REST_URL+"/categoria/save",self.serialized()).done(function(result) { 
      		 self.selectedUnmapped.name(result.name);
             self.selectedUnmapped.descripcion(result.descripcion);
             $('#editCategory').modal('hide');
          }).fail(function(){ alert("Ocurrio un error al salvar"); });

      }else{
        $('#submitOculto').trigger('click');
      }
   }

   self.borrar = function(data){
	 serializado=ko.mapping.toJSON(self.selected.id);
	 
	 $.deleteJSON(BASE_REST_URL+"/categoria/delete",serializado).done(function(result) { 
		 $('#editCategory').modal('hide');
	     self.categorias.remove(self.selectedUnmapped); 
	  }).fail(function(error){ alert(error.responseText);});
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

