function EtiquetaViewModel() {
	var self = this;

	self.formVisible = ko.observable(false);
	self.createNew = ko.observable(false);
	self.selected = ko.mapping.fromJS(new Etiqueta());
	self.utils = new Utils();
	self.etiquetas= ko.observableArray();
	

	self.showable = ko.observable(false);

	self.init = function(tipo) {
		var url= BASE_REST_URL+"/etiqueta/list";
		ko.mapping.fromJS(new Etiqueta(), self.selected);
		self.showable(true);
		
		self.formVisible(false);
		self.utils.getAll(self.etiquetas, "/etiqueta/list");
		
	}
	

		
	self.edit = function(data) {
		ko.mapping.fromJS(data, self.selected);
		self.formVisible(true);
		self.createNew(false);
	}

	self.save = function(data) {
		var serializado = ko.myToJSON(self.selected);
		
		$.postJSON(BASE_REST_URL+"/etiqueta/create/", self.selected.name()).done(self.pushInGrid).fail(function(){ alert("Ocurrio un error al salvar"); });
	}

	self.remove = function(){
		
		BootstrapDialog.confirm('Si elimina la etiqueta se eliminara de todos los Clientes y Proveedores', 
			    function(result){
		            if(result) {
		            	self.doRemove();	
		            }		
			   }
		 );
	}
	
	self.doRemove = function() {
		$.deleteJSON(BASE_REST_URL + "/etiqueta/remove",
				JSON.stringify(self.selected.id())).done(self.removeFromGrid)
				.fail(function(error) {
					alert(error.responseText);
				});	}
	
	self.update = function(data) {
		var serializado = ko.myToJSON(self.selected);
		var	url=BASE_REST_URL+"/etiqueta/update"
		$.postJSON(url, serializado).done(self.updateGrid).fail(function(){ alert("Ocurrio un error al salvar"); });			
	}
	
	
	self.updateGrid = function(data) {
		self.utils.updateGrid(data, self.etiquetas);
		alert("Actualizado correctamente");
		self.formVisible(false);
	}

	self.pushInGrid = function(data) {
		self.utils.pushInGrid(data, self.etiquetas);
		alert("Guardado correctamente");
		self.formVisible(false);
	}	
	self.removeFromGrid = function(data) {
		self.utils.removeFromGrid(self.selected, self.etiquetas);
		alert("Eliminado correctamente");
		self.formVisible(false);
	}	
	
	self.doSelect = function(data) {
		ko.mapping.fromJS(data, self.selected);
	}

	self.create = function() {
		var nuevo = new Etiqueta();
		ko.mapping.fromJS( nuevo, self.selected);
		self.formVisible(true);
		self.createNew(true);
	}
	self.cancelForm = function() {
		self.formVisible(false);
	}

	ko.bindingHandlers.fadeVisible =self.utils.fadeVisible;  
	

 

}