function StockViewModel() {
   var self = this;
   
   self.apply = function(){
	     ko.applyBindings(self);
	     var viewModelCategoria = new CategoriaViewModel();
	     viewModelCategoria.getAll(self.poblarCategorias);
   }
   
   
   self.poblarCategorias =function(destino){
	   //ko.mapping.fromJS(destino, self.categorias);
       self.categorias(destino);
   }
   
   self.selectedUnmapped = null;
   self.stocks = ko.mapping.fromJS([new Stock()]);
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
	   //    var self = this;
	       return ko.utils.arrayFirst(this.productos(), function(producto) {
	           return self.selectedProductoId() == producto.id;
	       });
	   }.bind(this);	   
	   
	 self.loadProductos = function(){
		 if(self.hayCategoriaSeleccionada()){
		     $.ajax("http://localhost/BGR/Symfony/web/app_dev.php/rest/producto/getByCategoria", {
		            type: "POST",
		            data: {'data': JSON.stringify(self.selectedCategoria())},
		            success: function(result) {
		               self.productos(result);
		            }
		      });
		 }else{
			 self.productos([]);
		 }
	  }
	 
	 
	 self.loadStocks= function(){
		 if(self.hayProductoSeleccionado()){
		     $.ajax("http://localhost/BGR/Symfony/web/app_dev.php/rest/presentacion/getStocksByProducto", {
		            type: "POST",
		            data: {'data': JSON.stringify(self.selectedProducto())},
		            success: function(result) {
		            	ko.mapping.fromJS( result, self.stocks);
		            }
		      });
		 }else{
			 self.stocks([]);
		 }
	  }	 
	 
   self.detalle = function(){
	   
   }

}