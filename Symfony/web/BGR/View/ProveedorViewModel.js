function ProveedorViewModel() {
	   var self = this;
	   self.getAll = function(destino){
		     $.ajax(BASE_REST_URL+"/proveedor/getAll", {
		            type: "GET",
		            success: function(result) { 
		              destino(result);
		            }
		      });
		   }

	   self.getAllProductoRelations = function(producto_id, destino){
		   
		     $.ajax(BASE_REST_URL+"/proveedor/getAllProductoRelations", {
		            data: {'data': producto_id},
		            type: "POST",

		            success: function(result){
			              destino(result);

		            }
		      });
		   
	   }
	   
	   self.getProveedoresByProductoId = function(producto_id, destino){
		   
		     $.ajax(BASE_REST_URL+"/proveedor/getProveedoresByProductoId", {
		            data:  {'data':producto_id},
		            type: "POST",
		            success: function(result){
			              destino(result);
		            }
		      });
		   
	   }
	   
	   self.saveRelation = function(proveedor,destino){
		   
		     $.ajax(BASE_REST_URL+"/proveedor/saveRelation", {
		            data: {'data':JSON.stringify(JSON.parse(ko.mapping.toJSON(proveedor)))},
		            type: "POST",
		            error: function(result){
		                  alert("Ocurrio un error al salvar");
		            },
		            success: function(result){
			              destino(result);
			              alert("Guardado correctamente");
		            }
		      });
		   
	   }


}