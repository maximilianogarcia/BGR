function ProveedorViewModel() {
	
   var self = this;
   
   self.getAll = function(destino){
	   $.getJSON(BASE_REST_URL+"/proveedorOld/getAll", function(result){ destino(result);});
   }

   self.getProveedoresByProductoId = function(producto_id, destino){
       $.postJSON(BASE_REST_URL+"/proveedorOld/getProveedoresByProductoId",JSON.stringify(producto_id)).
          done(function(result) { destino(result);});
   }
   
   self.saveRelation = function(proveedor,destino){
   	   $.postJSON(BASE_REST_URL+"/proveedorOld/saveRelation",JSON.stringify(JSON.parse(ko.mapping.toJSON(proveedor)))).
   	      done(function(result) { destino(result); alert("Guardado correctamente");}).
   	      fail(function(){ alert("Ocurrio un error al salvar"); });
   }

	self.formVisible = ko.observable(false);
	self.createNew = ko.observable(false);
	self.selected = ko.mapping.fromJS(new Proveedor());
	self.proveedores = ko.observableArray();
	self.utils = new Utils();
	self.etiquetas = ko.observableArray();
	self.calificativos = ko.observableArray();

	self.selectedCalificativoId = ko.observable();
	
	self.selectedCalificativo = function() {
       var self = this;
       return ko.utils.arrayFirst(self.calificativos(), function(item) {
           return self.selectedCalificativoId() == item.id;
       });
	}.bind(this);
	   
	self.showable = ko.observable(true);

	self.addTag = function(data) {

		if (event.keyCode == 13) {
			var valor = $("#tagInput").val();
			var match = ko.utils.arrayFirst(self.etiquetas(), function(item) {
				return valor === item.name;
			});

			if (match) {
				var toadd = ko.utils.arrayFirst(self.selected.etiquetas(), function(item) {
					return match.id === ko.toJS(item).id;
				});
				if(!toadd){
					$("#tagInput").val('')
					self.selected.etiquetas.push(match);
				}
				
			} else {
				 
				BootstrapDialog.confirm('No existe el tag: '+valor+' desea crearlo?', 
						    function(result){
					            if(result) {
					        		$.postJSON(BASE_REST_URL+"/etiqueta/create/", valor).done(self.pushTag).fail(function(){ alert("Ocurrio un error al salvar"); });
					            }		
						   }
					 );
			}
		}
		return true;
	}
	
	self.pushTag = function(data) {
		self.etiquetas.push(data);
	}

	self.init = function() {
		self.utils.getAll(self.initLoad, "/proveedor/list");
		self.utils.getAll(self.etiquetas, "/etiqueta/list");
		
		$.getJSON(BASE_REST_URL+"/calificativo/listProveedor/", function(data){  
			 self.calificativos(data);
		});
	}

	self.initLoad = function(data) {
		
		self.proveedores(data);

	}

	self.edit = function(data) {
		ko.mapping.fromJS(data, self.selected);

		self.selectedCalificativoId(self.selected.calificativo().id());
		self.formVisible(true);
		self.createNew(false);
	}

	self.save = function(data) {
	    var serializado=JSON.parse(ko.mapping.toJSON(self.selected));
        serializado.calificativo = self.selectedCalificativo();

		$.postJSON(BASE_REST_URL + "/proveedor/save",JSON.stringify(serializado))
		        .done(self.pushInGrid).fail(
				function() {
					alert("Ocurrio un error al salvar");
				});

	}

	self.unTag = function(data) {
		self.selected.etiquetas.remove(data);
	}


	self.remove = function(data) {

		$.deleteJSON(BASE_REST_URL + "/proveedor/delete",
				JSON.stringify(self.selected.id())).done(self.removeFromGrid)
				.fail(function(error) {
					alert(error.responseText);
				});

	}

	self.update = function(data) {
	    var serializado=JSON.parse(ko.mapping.toJSON(self.selected));
        serializado.calificativo = self.selectedCalificativo();
		$.postJSON(BASE_REST_URL + "/proveedor/save",
				JSON.stringify(serializado)).done(self.updateGrid).fail(
				function() {
					alert("Ocurrio un error al salvar");
				});
	}

	self.updateGrid = function(data) {
		self.utils.updateGrid(ko.myToJSON(data), self.proveedores);
		alert("Actualizado correctamente");
		self.formVisible(false);
	}
	
	self.replaceProveedores = function(origen, destino){
		
	}
	

	self.pushInGrid = function(data) {
		self.utils.pushInGrid(data, self.proveedores);
		alert("Guardado correctamente");
		self.formVisible(false);
	}

	self.removeFromGrid = function(data) {
		self.utils.removeFromGrid(self.selected, self.proveedores);
		alert("Eliminado correctamente");
		self.formVisible(false);
	}

	self.doSelect = function(data) {
		ko.mapping.fromJS(data, self.selected);
	}

	self.create = function() {
		ko.mapping.fromJS(new Proveedor(), self.selected);
		self.formVisible(true);
		self.createNew(true);
	}

	self.cancelForm = function() {
		self.formVisible(false);
		ko.mapping.fromJS(new Proveedor(), self.selected);
	}

	ko.bindingHandlers.fadeVisible = self.utils.fadeVisible;

	self.editSucursales = function(data) {
		showSucursal(self.selected);
	}

	self.hide = function() {
		self.showable(false);
	}

	self.display = function() {
		self.showable(true);
	}
	self.displayOpen = function(proveedor) {
		self.showable(true);
	}

	ko.observableArray.fn.find = function(prop, data) {
		var valueToMatch = data[prop]; 
		return ko.utils.arrayFirst(this(), function(item) {
			return item[prop] === valueToMatch; 
		});
	};
}

/**
 * Busqueda en un array observable
 * @author maxi
 */
