function MixViewModel() {
	   var self = this;
	   self.utils = new Utils();
	   self.remanentes= ko.observableArray();
	   self.allProductos = ko.observableArray();
	   self.selected = ko.mapping.fromJS(new Mix());
	   self.allCategorias = ko.observableArray();
	   self.allUnidades = ko.observableArray();
	   self.allUnidadesTotalizadas = ko.observableArray();
	   
	   self.mixes = ko.observableArray();
	   
	   self.selectedProductos = ko.observableArray();
	   
	   self.paquetesPorProducto = ko.observableArray();

	   self.selectedCategoriaId = ko.observable(1);
	   
	   self.selectedUnidadId = ko.observable(1);

	   self.totalesByProducto = ko.observableArray();
	   
	   self.paquetesPorProductoSelected = ko.observableArray();
	   
	   self.selectedCategoria = ko.observable();
	   
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
	   
	   
	   self.showBackButton = ko.computed(function(){
	        return self.step() > 1 && self.step() <= 5 ;
	   });
	   
	   self.remanentesAgregados= ko.observableArray();

	   
	   self.apply = function(){
		   self.utils.getAll(self.allProductos, "/producto/getAll");
		   
		   self.utils.getAll(self.mixes, "/mix/getAll");
		   
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
	   
	   self.getPaquetesSelectedByProducto = function(producto_id){
		   return ko.utils.arrayFirst(self.paquetesPorProductoSelected(), function(item) {
	           return producto_id === item.id;
	       });
	   }
	   
	   self.getPaqueteFromCache = function(numeroPaquete, producto_id){
		   var todosLosPaquetes = self.paquetesListCache[producto_id];
		   
		   return ko.utils.arrayFirst(todosLosPaquetes, function(item) {
	           return numeroPaquete === item.id;
	       });
	   }
	   
	   self.addSelectedPaquete = function(data){
		   var  numeroPaquete= $("#producto"+data.id).val();
		    
		   
		   if( self.validarPaqueteProducto(numeroPaquete,data.id) ){
			   
			   var productoSelected = self.getPaquetesSelectedByProducto(data.id)		       
		   
			   var paquete = self.getPaqueteFromCache(numeroPaquete,data.id);
			   
			   if(!productoSelected){
		    	   var paquetesArray = new Array();
		    	   var aux={};
				   paquetesArray[0] = paquete;
				   aux['id'] =  data.id;
				   aux['data'] = ko.observableArray(paquetesArray);
		    	   self.paquetesPorProductoSelected.push(aux);   
		       }else{
		    	   if( productoSelected.data.indexOf(paquete) < 0){
		    		   productoSelected.data.push(paquete);
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
	   
	   
	   self.cantidadTotalByProducto = function(producto){
		   self.totalesByProducto.removeAll();
		   
		   for(var i = 0; i < self.paquetesPorProductoSelected().length; i++){
			 //  if( self.paquetesPorProductoSelected()[i].id == producto.id){
			   	   var id = 0;	
			   	   var sum = 0;
				   for(var j = 0; j < self.paquetesPorProductoSelected()[i].data().length; j++){
					   sum = sum + parseInt(self.paquetesPorProductoSelected()[i].data()[j].cantidad);
					   product_id = self.paquetesPorProductoSelected()[i].id;
				   }
//				   var productoFounded= ko.utils.arrayFirst(self.totalesByProducto(), function(item) {
//			           return producto.id === item.id;
//				   });
//				   
				   
				   if(product_id != 0){					   
					   self.totalesByProducto.push({id:product_id, cantidad:sum});
				   }
			//   }
			   
		   }
	   };
	   
	   self.loadForMixProduct = function(){
		   self.utils.getAll(self.allCategorias, "/categoria/getAll");
		   self.utils.getAll(self.allUnidades, "/unidadMedida/getAll");
		   
		   var paquetes = new Array();
		   for(var i = 0 ;i < self.paquetesPorProductoSelected().length; i++){		
			   var paq = self.getPaqueteSelectedObject(self.paquetesPorProductoSelected()[i].id);
			   paquetes.push({
				   "paquete_id":paq.id,
				   "cantidad":paq.cantidad,
				   "unidad":paq.unidad				   
			   });
		   }
		   
		   self.utils.doPost(self.allUnidadesTotalizadas, paquetes ,"/unidadMedida/getTotalByPaquetes");
		   
	   }
	   
	   self.getPaqueteSelectedObject = function(producto_id){
		   paquetes = self.getPaquetesSelectedByProducto(producto_id);
		   return paquetes.data();	
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
		      case 3:
			         self.cantidadTotalByProducto();
			         self.doNext();
			         break;
		      case 4:
			         self.guardar();
			         break;
		      default:
		    	  self.doNext();
		        break;
		      }   
	   }
	   self.back = function(){
		   self.step(self.step()-1);
		 
	   }  
	   self.doNext = function(){
		   self.step(self.step()+1);
	   }

	   
	   self.joinPaquetesCantidades= function(paquetes, cantidades){
		   
		   var result = [];
		   var indexCant = 0;
		   for (var i = 0; i < paquetes().length; i++) {
			   for (var j = 0; j < cantidades().length; j++) {
				   if(paquetes()[i].id == cantidades()[j].id){
					   
					   var thiscant =  $("#cant_elegida"+indexCant).val();
						   
					   result.push({
						   'id': paquetes()[i].id,
						   'paquetes': paquetes()[i].data(),
						   'cantidad': cantidades()[j].cantidad,
						   'cant_elegida': thiscant
					   });
					   indexCant++;
				   }
			   }			   
		   }
		   
		   return result;
	   }
	   
	   self.guardar = function(){
		   var infoPaquetes = self.joinPaquetesCantidades(
				   self.paquetesPorProductoSelected, 
				   self.totalesByProducto);
		   var mix = {
		     'nombre'   : self.selected.name(),
		     'vencimiento'  : self.selected.vencimiento,
		     'unidad_de_medida'  : ko.mapping.toJSON( self.selectedUnidadId),
		     'categoria'  : ko.mapping.toJSON( self.selectedCategoriaId),
		     'paquetes_mix'  : infoPaquetes,
		   }

		   $.ajax(BASE_REST_URL+"/mix/saveMix", {
    		   data: {'data': JSON.stringify(mix) },
               type: "POST",
               error: function(result){
                 alert(result.responseText);
               },
               success: function(result) { 
            	   alert("se ha guardado el mix exitosamente");
            	   location.href = "mixGrid.html";
               }
		   });
	   }

	   
}