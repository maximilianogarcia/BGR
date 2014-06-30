function SucursalViewModel() {
	var self = this;

	self.formVisible = ko.observable(false);
	self.createNew = ko.observable(false);
	self.selected = ko.mapping.fromJS(new Sucursal());
	self.sucursales = ko.observableArray();
	self.eoi = ko.mapping.fromJS(new Proveedor());
	self.utils = new Utils();

	self.showable = ko.observable(false);
	
	self.init = function() {
		ko.mapping.fromJS(new Sucursal(), self.selected);
		$.getJSON(BASE_REST_URL+"/sucursal/listByProveedor/"+self.eoi.id(), function(data){  
			 self.sucursales(data);
			 self.showable(true);
		});
	}

	self.edit = function(data) {
		ko.mapping.fromJS(data, self.selected);
		self.formVisible(true);
		self.createNew(false);
	}

	self.save = function(data) {
		var eoi = ko.mapping.toJS(self.eoi);
		self.selected.eoi = eoi;
		var serializado = ko.myToJSON(self.selected);
		
    	$.postJSON(BASE_REST_URL+"/sucursal/save",serializado).done(self.pushInGrid).fail(function(){ alert("Ocurrio un error al salvar"); });

	}

	self.remove = function(data) {
	
    	$.deleteJSON(BASE_REST_URL+"/sucursal/delete",JSON.stringify(self.selected.id())).done(self.removeFromGrid).fail(function(error){ alert(error.responseText);});
    	

	}

	self.update = function(data) {
		var eoi = ko.mapping.toJS(self.eoi);
		self.selected.eoi = eoi;
		var serializado = ko.myToJSON(self.selected);
		
    	$.postJSON(BASE_REST_URL+"/sucursal/save",serializado).done(self.updateGrid).fail(function(){ alert("Ocurrio un error al salvar"); });
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
		self.selected.eoi = self.eoi;
		self.formVisible(true);
		self.createNew(true);
	}

	self.cancelForm = function() {
		self.formVisible(false);
	}

	ko.bindingHandlers.fadeVisible =self.utils.fadeVisible;  


	
	self.editContacts =function(data){
		showContact(self.selected);
	}
	
	self.hide = function(){
		self.showable(false);
	}
	
	self.display= function(prov){
		ko.mapping.fromJS(prov, self.eoi);
		self.init();
		self.showable(true);
	}
	
	self.displayOpen= function(sucursal){
		ko.mapping.fromJS(sucursal, self.selected);
		self.showable(true);
	}
	
	self.backToProveedor= function(){
		self.formVisible(false);
		showProveedorOpen(self.eoi);
	}
	
}