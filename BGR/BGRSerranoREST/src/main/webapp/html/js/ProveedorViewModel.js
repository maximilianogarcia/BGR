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
	self.formVisible = ko.observable(false);
	self.createNew = ko.observable(false);
	self.selected = ko.mapping.fromJS(new Proveedor());
	self.proveedores = ko.observableArray();
	self.utils = new Utils();
	self.etiquetas = ko.observableArray();

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
	}

	self.initLoad = function(data) {
		self.proveedores(data);

	}

	self.edit = function(data) {
		ko.mapping.fromJS(data, self.selected);
		self.formVisible(true);
		self.createNew(false);
	}

	self.save = function(data) {
		$.postJSON(BASE_REST_URL + "/proveedor/save",
				ko.mapping.toJSON(self.selected)).done(self.pushInGrid).fail(
				function() {
					alert("Ocurrio un error al salvar");
				});

	}

	self.unTag = function(data) {
		/*var match = ko.utils.arrayFirst(self.selected.etiquetas(), function(
				item) {
			return data.id === item.id();
		});*/
		self.selected.etiquetas.remove(data);
	}

/*	self.removeTag = function(data) {
		var datos = {};
		datos.etiqueta = data.id();
		datos.proveedor = self.selected.id();
		$.deleteJSON(BASE_REST_URL + "/etiqueta/untag", JSON.stringify(datos))
				.done(self.unTag).fail(function(error) {
					alert(error.responseText);
				});
	}*/

	self.remove = function(data) {

		$.deleteJSON(BASE_REST_URL + "/proveedor/delete",
				JSON.stringify(self.selected.id())).done(self.removeFromGrid)
				.fail(function(error) {
					alert(error.responseText);
				});

	}

	self.update = function(data) {
		self.formVisible(true);
		$.postJSON(BASE_REST_URL + "/proveedor/save",
				ko.mapping.toJSON(self.selected)).done(self.updateGrid).fail(
				function() {
					alert("Ocurrio un error al salvar");
				});
	}

	self.updateGrid = function(data) {
		self.utils.updateGrid(data, self.proveedores);
		alert("Actualizado correctamente");
		self.formVisible(false);
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

}