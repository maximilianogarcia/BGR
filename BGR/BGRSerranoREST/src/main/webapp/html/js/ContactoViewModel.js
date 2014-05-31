function ContactoViewModel() {
	var self = this;

	self.formVisible = ko.observable(false);

	self.selected = ko.mapping.fromJS(new Contacto());

	self.contactos = ko.observableArray();

	self.utils = new Utils();

	self.init = function() {
		self.utils.getAll(self.contactos, "/contacto/list");
	}

	self.edit = function(data) {
		self.formVisible(true);
		ko.mapping.fromJS(data, self.selected);
	}

	self.save = function(data) {
		self.formVisible(true);
		self.utils.doPost("/contacto/save", ko.mapping.toJSON(self.selected),
				self.putInGrid, function(a) {
					alert(a)
		});
	}

	self.putInGrid = function(data) {
		self.utils.putInGrid(data, self.contactos);
	}

	self.doSelect = function(data) {
		ko.mapping.fromJS(data, self.selected);
	}

	self.create = function() {
		ko.mapping.fromJS(new Contacto(), self.selected);
		self.formVisible(true);
	}
	self.cancelForm = function() {
		self.formVisible(false);
	}

	ko.bindingHandlers.fadeVisible = {
		init : function(element, valueAccessor) {
			// Initially set the element to be instantly visible/hidden
			// depending on the value
			var value = valueAccessor();
			$(element).toggle(ko.unwrap(value)); // Use "unwrapObservable" so
													// we can handle values that
													// may or may not be
													// observable
		},
		update : function(element, valueAccessor) {
			// Whenever the value subsequently changes, slowly fade the element
			// in or out
			var value = valueAccessor();
			ko.unwrap(value) ? $(element).fadeIn() : $(element).fadeOut();
		}
	};

}