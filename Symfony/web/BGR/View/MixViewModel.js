function MixViewModel() {
	   var self = this;
	   self.utils = new Utils();
	   self.remanentes= ko.observableArray();
	   self.allProductos = ko.observableArray();
	   self.selectedProductos = ko.observableArray();
	   
	   self.step = ko.observable(1);
	   self.step1 = ko.computed(function(){
	        return self.step() == 1;
	   });
	   self.step2 = ko.computed(function(){
	        return self.step() == 2;
	   });
	   self.remanentesAgregados= ko.observableArray();

	   
	   self.apply = function(){
		   self.utils.getAll(self.allProductos, "/producto/getAll");
		   ko.applyBindings(self);	
	   }
	   
	   self.validateAndNext = function(){
		   
		   $.each( self.selectedProductos(), function(index,producto){
			   text = $("#utilizarPaquete"+producto.id).val();
			   if(self.validarInputPaquete(text)){
				   $("#messageValidar"+producto.id).attr('class','hide');
			   }else{
				   self.validarPaquete(producto.id, text, self.showError, self.hideError);
			   }
		   });
	   }
	   
	   self.canMix = ko.computed(function(){
		   var atLeastOne = false;		   
		   $.each( self.selectedProductos(), function(index,producto){
			   text = $("#utilizarPaquete"+producto.id).val();
			   
			   if(!self.validarInputPaquete(text)){
				  atLeastOne = true;
			   }
		   });
		   return atLeastOne;
	   },self);
	   
	   self.showError = function(response){
		  $("#messageValidar"+response.responseText).attr('class','error');
	   }
	   
	   self.hideError = function(response){
		  $("#messageValidar"+response).attr('class','hide');
	   }

	   self.validarInputPaquete= function(value){
		   if(value == null || value == ""){
			   return true
		   }
		   return false;
	   }
	   
	   
	   self.validarPaquete = function(productoId, paqueteId, errorCallback, succesCallback){
		   	self.utils.doGet(
				"/producto/validarPaquete", 
				{
					'productoId' : productoId,
					'paqueteId' : paqueteId
				}, 
				succesCallback, errorCallback
			);
			   
	   }

	   self.editar= function(data){
	       duplicado = ko.utils.arrayFirst(self.remanentesAgregados(), function(rem) {
	           return data.remanente_id == rem.remanente_id;
	       });
	       if(!duplicado){
	    	   self.remanentesAgregados.push(data);
	       }
	   }
	   
	   self.getRemanentesPorProductos= function(){
		   var productosTmp= [];
		   for ( var i = 0; i< self.selectedProductos().length; i++) {
			   productosTmp.push(self.selectedProductos()[i].id);
		   }	   
		   self.utils.doPost(JSON.stringify(productosTmp), self.doGetRemanentesByProductos,"/producto/getRemanentesByProductos");
	   }

	   self.doGetRemanentesByProductos = function(data){
		   self.remanentes(data);
		   self.remanentesAgregados.removeAll();
		   self.hideModal();
	   }
	   
	   
	   
	   self.hideModal = function(){
		   $('#editMix').modal('hide');
	   }
	   self.showModal = function(){
		   $('#editMix').modal('show');
	   }
	   self.seleccionarProductos = function(){
	      self.showModal();
	   }


}