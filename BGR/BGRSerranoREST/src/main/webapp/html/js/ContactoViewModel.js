function ContactoViewModel() {
	var self = this;

	self.formVisible = ko.observable(false);
	self.createNew = ko.observable(false);
	self.selected = ko.mapping.fromJS(new Contacto());
	self.contactos = ko.observableArray();
	self.utils = new Utils();

	self.init = function() {
		self.utils.getAll(self.contactos, "/contacto/list");
	}

	self.edit = function(data) {
		ko.mapping.fromJS(data, self.selected);
		self.formVisible(true);
		self.createNew(false);
	}

	self.save = function(data) {
		self.formVisible(true);
		self.utils.doPost("/contacto/save", ko.mapping.toJSON(self.selected),
				self.pushInGrid, function(a) {
					alert(a)
		});
	}

	self.remove = function(data) {
		self.utils.doDelete("/contacto/remove/"+self.selected.id(),"",
				self.removeFromGrid, function(a) {
					alert(a)
		});
	}
	
	self.update = function(data) {
		self.formVisible(true);
		self.utils.doPost("/contacto/save", ko.mapping.toJSON(self.selected),
				self.updateGrid, function(a) {
					alert(a)
		});
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
		self.formVisible(true);
		self.createNew(true);
	}
	self.cancelForm = function() {
		self.formVisible(false);
	}

	ko.bindingHandlers.fadeVisible =self.utils.fadeVisible;  

}