function ContactoViewModel() {
	var self = this;
    
	self.formVisible = ko.observable(false);

	self.contactos = ko.observableArray();

	self.utils = new Utils();

    self.init = function(){
	   self.utils.getAll(self.mixes, "/contacto/list");
    }

	self.showForm = function(){
		self.formVisible(true);
	}
	self.cancelForm = function(){
		self.formVisible(false);
	}


}