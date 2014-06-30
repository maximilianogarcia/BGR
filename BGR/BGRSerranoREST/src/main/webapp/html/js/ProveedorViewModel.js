function ProveedorViewModel() {
	
   var self = this;
   
   self.getAll = function(destino){
	   $.getJSON(BASE_REST_URL+"/proveedor/getAll", function(result){ destino(result);});
   }

   self.getProveedoresByProductoId = function(producto_id, destino){
       $.postJSON(BASE_REST_URL+"/proveedor/getProveedoresByProductoId",JSON.stringify(producto_id)).
          done(function(result) { destino(result);});
   }
   
   self.saveRelation = function(proveedor,destino){
   	   $.postJSON(BASE_REST_URL+"/proveedor/saveRelation",JSON.stringify(JSON.parse(ko.mapping.toJSON(proveedor)))).
   	      done(function(result) { destino(result); alert("Guardado correctamente");}).
   	      fail(function(){ alert("Ocurrio un error al salvar"); });
   }
}