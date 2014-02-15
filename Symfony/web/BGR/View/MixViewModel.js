function MixViewModel() {
	   var self = this;
	   self.utils = new Utils();
	   self.remanentes= ko.observableArray();
	   self.allProductos = ko.observableArray();
	   self.selectedProductos = ko.observableArray();

	   self.apply = function(){
		   self.utils.getAll(self.remanentes, "/producto/getAllRemanentes");
		   self.utils.getAll(self.allProductos, "/producto/getAll");
		   
		   ko.applyBindings(self);	
	   }
	   
	   self.getRemanentesPorProductos= function(){
		   var productosTmp= [];
		   for ( var i = 0; i< self.selectedProductos().length; i++) {
			   productosTmp.push(self.selectedProductos()[i].id);
		   }	   
		   self.utils.doPost(self.doGetRemanentesByProductos,"/producto/getRemanenteByProductos");
	   }

	   self.doGetRemanentesByProductos = function(data){
		   self.remanentes(data);
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