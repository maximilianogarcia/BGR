function StockViewModel() {
   var self = this;
   
   self.apply = function(){
	     ko.applyBindings(self);
	     var viewModelCategoria = new CategoriaViewModel();
	     viewModelCategoria.getAll(self.poblarCategorias);
	     self.getAllStocks();
   }
   
   
   self.poblarCategorias =function(destino){
       self.categorias(destino);
   }
	
   self.getAllStocks = function(){
	   $.getJSON(BASE_REST_URL+"/presentacion/getStocks").done(function(result) { 
		  ko.mapping.fromJS( result, self.allStocks);
	      ko.mapping.fromJS( result, self.stocks);
	   });
   }   
   
   self.selectedUnmapped = null;
   self.stocks = ko.mapping.fromJS([new Stock()]);
   self.allStocks = ko.mapping.fromJS([new Stock()]);
   self.selected = ko.mapping.fromJS(new Stock());

   self.categorias = ko.mapping.fromJS([new Stock()]);
   self.selectedCategoriaId = ko.observable();
  
   self.hayCategoriaSeleccionada = ko.computed(function(){
	     return self.selectedCategoriaId() != null;
   });
   self.selectedCategoria = function() {
	       return ko.utils.arrayFirst(this.categorias(), function(categoria) {
	           return self.selectedCategoriaId() == categoria.id;
	       });
   }.bind(this);
   
   self.productos = ko.observableArray();
   self.selectedProductoId = ko.observable(null);
   self.hayProductoSeleccionado = ko.computed(function(){
	     return self.selectedProductoId() != null;
   });
   self.selectedProducto = function() {
	       return ko.utils.arrayFirst(this.productos(), function(producto) {
	           return self.selectedProductoId() == producto.id;
	       });
	   }.bind(this);	   
	   
	 self.loadProductos = function(){
		 if(self.hayCategoriaSeleccionada()){
	
		   $.postJSON(BASE_REST_URL+"/producto/getByCategoria", JSON.stringify(self.selectedCategoriaId()) ).
		   done(function(result) { 
			   self.productos(result);
		   });
		   
		   $.getJSON(BASE_REST_URL+"/presentacion/getStocksByCategoria/"+ self.selectedCategoria().id).
		   done(function(result) { 
			   ko.mapping.fromJS( result, self.stocks);
		   });

		 }else{
			 self.productos([]);
			 self.stocks(self.allStocks());
		 }
	  }
	 
	 
	 self.loadStocks= function(){
		 if(self.hayProductoSeleccionado()){
			   $.postJSON(BASE_REST_URL+"/presentacion/getStocksByProducto", JSON.stringify(self.selectedProducto().id) ).
			   done(function(result) { 
				   ko.mapping.fromJS( result, self.stocks);
			   });

		 }else{
			 self.stocks(self.allStocks());
		 }
	  }	 
	 
   self.detalle = function(){
	   
   }

}