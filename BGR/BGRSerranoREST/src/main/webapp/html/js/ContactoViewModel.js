function ContactoViewModel() {
	var self = this;

	self.formVisible = ko.observable(false);
	self.createNew = ko.observable(false);
	self.selected = ko.mapping.fromJS(new Contacto());
	self.contactos= ko.observableArray();
	self.utils = new Utils();

	self.sucursal = ko.mapping.fromJS(new Sucursal());

	
	self.showable = ko.observable(false);

	self.init = function() {
		ko.mapping.fromJS(new Contacto(), self.selected);
		self.formVisible = ko.observable(false);
		$.getJSON(BASE_REST_URL+"/contacto/listBySucursal/"+self.sucursal.id(), function(data){  
			 self.contactos(data);
			 self.showable(true);
		});
		
	}

	self.edit = function(data) {
		ko.mapping.fromJS(data, self.selected);
		self.formVisible(true);
		self.createNew(false);
	}

	self.save = function(data) {
		var sucursal = ko.mapping.toJS(self.sucursal);
		self.selected.sucursal = sucursal;
		self.selected.eoi = sucursal.proveedor;
		var serializado = ko.myToJSON(self.selected);
		
		$.postJSON(BASE_REST_URL+"/contacto/save", serializado).done(self.pushInGrid).fail(function(){ alert("Ocurrio un error al salvar"); });
	}

	self.remove = function(data) {
    	$.deleteJSON(BASE_REST_URL+"/contacto/delete",JSON.stringify(self.selected.id())).done(self.removeFromGrid).fail(function(error){ alert(error.responseText);});
	}
	
	self.update = function(data) {
		var sucursal = ko.mapping.toJS(self.sucursal);
		self.selected.sucursal = sucursal;
		self.selected.eoi = sucursal.proveedor;
		var serializado = ko.myToJSON(self.selected);

		$.postJSON(BASE_REST_URL+"/contacto/save", serializado).done(self.updateGrid).fail(function(){ alert("Ocurrio un error al salvar"); });

	}
	
	
	self.updateGrid = function(data) {
		self.utils.updateGrid(data, self.contactos);
		alert("Actualizado correctamente");
		self.formVisible(false);
	}

	self.pushInGrid = function(data) {
		self.utils.pushInGrid(data, self.contactos);
		alert("Guardado correctamente");
		self.formVisible(false);
	}	
	self.removeFromGrid = function(data) {
		self.utils.removeFromGrid(self.selected, self.contactos);
		alert("Eliminado correctamente");
		self.formVisible(false);
	}	
	
	self.doSelect = function(data) {
		ko.mapping.fromJS(data, self.selected);
	}

	self.create = function() {
		ko.mapping.fromJS(new Contacto(), self.selected);
		self.selected.sucursal = self.sucursal;
		self.formVisible(true);
		self.createNew(true);
	}
	self.cancelForm = function() {
		self.formVisible(false);
	}

	ko.bindingHandlers.fadeVisible =self.utils.fadeVisible;  

	self.backToSucursal =function(data){
		showSucursalOpen(self.sucursal);
	}
	
	self.hide = function(){
		self.showable(false);
	}
	self.display= function(sucur){
		ko.mapping.fromJS(sucur, self.sucursal);
		self.init();
	}	
	
}