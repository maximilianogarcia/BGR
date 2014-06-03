function SucursalViewModel() {
	var self = this;

	self.formVisible = ko.observable(false);
	self.createNew = ko.observable(false);
	self.selected = ko.mapping.fromJS(new Sucursal());
	self.sucursales = ko.observableArray();
	self.utils = new Utils();

	self.init = function() {
		self.utils.getAll(self.sucursales, "/sucursal/list");
	}

	self.edit = function(data) {
		ko.mapping.fromJS(data, self.selected);
		self.formVisible(true);
		self.createNew(false);
	}

	self.save = function(data) {
    	$.postJSON(BASE_REST_URL+"/sucursal/save", ko.mapping.toJSON(self.selected)).done(self.pushInGrid).fail(function(){ alert("Ocurrio un error al salvar"); });

	}

	self.remove = function(data) {
	/*	self.utils.doDelete("/sucursal/remove/"+self.selected.id(),"",
				self.removeFromGrid, function(a) {
					alert(a)
		});*/
    //	$.deleteJSON(BASE_REST_URL+"/sucursal/delete/", self.selected.id()).done(self.removeFromGrid).fail(function(){ alert("Ocurrio un error al borrar"); });
    	
    	$.deleteJSON(BASE_REST_URL+"/sucursal/delete",self.selected.id()).done(self.removeFromGrid).fail(function(error){ alert(error.responseText);});
    	

	}

	self.update = function(data) {
		self.formVisible(true);
    	$.postJSON(BASE_REST_URL+"/sucursal/save", ko.mapping.toJSON(self.selected)).done(self.updateGrid).fail(function(){ alert("Ocurrio un error al salvar"); });
	}

	self.updateGrid = function(data) {
		self.utils.updateGrid(data, self.sucursales);
		alert("Actualizado correctamente");
		self.formVisible(false);
	}

	self.pushInGrid = function(data) {
		self.utils.pushInGrid(data, self.sucursales);
		alert("Guardado correctamente");
		self.formVisible(false);
	}	

	self.removeFromGrid = function(data) {
		self.utils.removeFromGrid(self.selected, self.sucursales);
		alert("Eliminado correctamente");
		self.formVisible(false);
	}	

	self.doSelect = function(data) {
		ko.mapping.fromJS(data, self.selected);
	}

	self.create = function() {
		ko.mapping.fromJS(new Sucursal(), self.selected);
		self.formVisible(true);
		self.createNew(true);
	}

	self.cancelForm = function() {
		self.formVisible(false);
	}

	ko.bindingHandlers.fadeVisible =self.utils.fadeVisible;  

}