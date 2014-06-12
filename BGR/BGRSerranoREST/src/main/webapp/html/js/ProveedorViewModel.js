function ProveedorViewModel() {
	var self = this;

	self.formVisible = ko.observable(false);
	self.createNew = ko.observable(false);
	self.selected = ko.mapping.fromJS(new Proveedor());
	self.proveedores = ko.observableArray();
	self.utils = new Utils();

	self.showable = ko.observable(true);
	
	self.init = function() {
		self.utils.getAll(self.proveedores, "/proveedor/list");
	}

	self.edit = function(data) {
		ko.mapping.fromJS(data, self.selected);
		self.formVisible(true);
		self.createNew(false);
	}

	self.save = function(data) {
    	$.postJSON(BASE_REST_URL+"/proveedor/save", ko.mapping.toJSON(self.selected)).done(self.pushInGrid).fail(function(){ alert("Ocurrio un error al salvar"); });

	}

	self.remove = function(data) {
	
    	$.deleteJSON(BASE_REST_URL+"/proveedor/delete",JSON.stringify(self.selected.id())).done(self.removeFromGrid).fail(function(error){ alert(error.responseText);});
    	

	}

	self.update = function(data) {
		self.formVisible(true);
    	$.postJSON(BASE_REST_URL+"/proveedor/save", ko.mapping.toJSON(self.selected)).done(self.updateGrid).fail(function(){ alert("Ocurrio un error al salvar"); });
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

	ko.bindingHandlers.fadeVisible =self.utils.fadeVisible;  


	
	self.editSucursales =function(data){
		showSucursal(self.selected);
	}
	
	self.hide = function(){
		self.showable(false);
	}
	
	self.display= function(){
		self.showable(true);
	}
	self.displayOpen= function(proveedor){
		self.showable(true);
	}

	
}