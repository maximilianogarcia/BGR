function MixViewModel() {
	   var self = this;
	   self.utils = new Utils();
	   self.remanentes= ko.observableArray();
	   self.allProductos = ko.observableArray();
	   self.selected = ko.mapping.fromJS(new Producto());
	   self.allCategorias = ko.observableArray();
	   self.allUnidades = ko.observableArray();
	   
	   self.selectedProductos = ko.observableArray();
	   
	   self.paquetesPorProducto = ko.observableArray();
	   
	   self.paquetesPorProductoSelected = ko.observableArray();
	   
	   self.paquetesListCache = {};
	   
	   self.step = ko.observable(1);
	   self.step1 = ko.computed(function(){
	        return self.step() == 1;
	   });
	   self.step2 = ko.computed(function(){
	        return self.step() == 2;
	   });
	   
	   self.step3 = ko.computed(function(){
	        return self.step() == 3;
	   });
	   
	   self.step4 = ko.computed(function(){
	        return self.step() == 4;
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


	   self.getPaquetesByProducto = function(data){
		   
		   if( data.id in self.paquetesListCache ){
			   self.paquetesPorProducto(self.paquetesListCache[data.id]);
		   }else{
			   self.doGetPaquetesByProducto(data.id,self.alertar, self.addToProductMap);
		   }
	   }	   
	   
	   self.addToProductMap = function(data){
		   self.paquetesListCache[data.producto_id] = data.data;
		   self.paquetesPorProducto(data.data);
	   }
	   
	   self.addSelectedPaquete = function(data){
		   if( self.validarPaqueteProducto($("#producto"+data.id).val(),data.id) ){
			   var aux={};
			   var paquetesArray = new Array();
			   paquetesArray[0] = $("#producto"+data.id).val();
			   aux['id'] =  data.id;
			   aux['data'] = ko.observableArray(paquetesArray);
			   var productoSelected = ko.utils.arrayFirst(self.paquetesPorProductoSelected(), function(item) {
		           return data.id === item.id;
		       });
		       
		       if(!productoSelected){
		    	   self.paquetesPorProductoSelected.push(aux);   
		       }else{
		    	   if( productoSelected.data.indexOf($("#producto"+data.id).val()) < 0){
		    		   productoSelected.data.push($("#producto"+data.id).val());
		    	   }else{
		    		   alert("paquete duplicado");
		    	   }
		       }
		   }else{
			   alert("no existe el paquete");
		   }
	   }

	   self.validarPaqueteProducto = function(paquete_id,producto_id){
		  if ( producto_id in self.paquetesListCache){
			  return ko.utils.arrayFirst(self.paquetesListCache[producto_id], function(item) {
		           return paquete_id === item.id;
		      });
		  }
		  return false;
	   }
	   
	   self.loadForMixProduct = function(){
		   self.utils.getAll(self.allCategorias, "/categoria/getAll");
		   self.utils.getAll(self.allUnidades, "/unidadMedida/getAll");
	   }
	   
	   self.removePaquete = function(producto_id,paquete_id){
		   var paqueteSelected = ko.utils.arrayFirst(self.paquetesPorProductoSelected(), function(item) {
	           return producto_id === item.id;
		   });
		   
		   if(paqueteSelected){
			   paqueteSelected.data.remove(paquete_id);
		   }
	   }
	   
	   self.alertar = function(data){
		   alert(JSON.stringify(data));
	   }
	   self.doGetPaquetesByProducto = function(productoId, errorCallback, succesCallback){
		   	self.utils.doGet(
				"/paquete/getPaquetesDisponiblesPorProducto", 
				{
					'productoId' : productoId
				}, 
				succesCallback, errorCallback
			);
			   	
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

	   self.next = function(){
		      
		   switch(self.step()){
		      case 2:
		         self.loadForMixProduct();
		         self.doNext();
		         break;
		      default:
		    	  self.doNext();
		        break;
		      }   
	   }
	   
	   self.doNext = function(){
		   self.step(self.step()+1);
	   }

}