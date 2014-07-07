function CalificativoViewModel() {
	var self = this;

	self.formVisible = ko.observable(false);
	self.createNew = ko.observable(false);
	self.selected = ko.mapping.fromJS(new Calificativo());
	self.utils = new Utils();
	self.calificativos= ko.observableArray();
	self.typeSelected = ko.observable();
	
	self.calificativosTypes = ko.observableArray(
			[
			 {"id":"1","name":"CLIENTE"},
			 {"id":"2","name":"PROVEEDOR"}
			]		
	);

	self.showable = ko.observable(false);

	self.init = function(tipo) {
		var nuevo = new Calificativo();
		var url = "";
		if(tipo=="PROVEEDOR"){
			self.typeSelected({"id":"2","name":"PROVEEDOR"});
			url= BASE_REST_URL+"/calificativo/listProveedor/"
		}else{
			self.typeSelected({"id":"1","name":"CLIENTE"})
			url= BASE_REST_URL+"/calificativo/listCliente/"
		}
		nuevo.type(self.typeSelected().name);
		
		ko.mapping.fromJS(nuevo, self.selected);
		
		
		self.formVisible(false);
		$.getJSON(url, function(data){  
			 self.calificativos(data);
			 self.showable(true);
		});
		
	}
	self.change = function(data){

		if(self.typeSelected().id == 1){
			$.getJSON(BASE_REST_URL+"/calificativo/listCliente/", function(data){  
				 self.calificativos(data);
				 self.showable(true);
			});
		}else{
			$.getJSON(BASE_REST_URL+"/calificativo/listProveedor/", function(data){  
				 self.calificativos(data);
				 self.showable(true);
			});
		}
	}

	self.listContactosEoi = function() {
		ko.mapping.fromJS(new Contacto(), self.selected);
		self.formVisible(false);
		$.getJSON(BASE_REST_URL+"/contacto/listOthersByEoi/"+self.sucursal.eoi().id()+"/"+self.sucursal.id(), function(data){  
			self.contactosEoi(data);
			self.showable(true);
			$('#modalContactos').modal('show')
		});
		
	}

	self.addToSucursal = function(data) {
		 BootstrapDialog.confirm('Seguro que quiere agregar: '+data.name, 
			    function(result){
		            if(result) {
		        		var sucursal = ko.mapping.toJS(self.sucursal);
		        		data.eoi = sucursal.eoi;
		        		var serializado = ko.myToJSON(data);
		        		
		        		$.postJSON(BASE_REST_URL+"/sucursal/addContact/"+self.sucursal.id(), serializado).done(self.pushInGrid).fail(function(){ alert("Ocurrio un error al salvar"); });

		            }else {
		    			$('#modalContactos').modal('hide')
		            }		
			   }
		 );
	}
	
	
	self.edit = function(data) {
		ko.mapping.fromJS(data, self.selected);
		self.formVisible(true);
		self.createNew(false);
	}

	self.save = function(data) {
		var serializado = ko.myToJSON(self.selected);
		
		$.postJSON(BASE_REST_URL+"/calificativo/create/", serializado).done(self.pushInGrid).fail(function(){ alert("Ocurrio un error al salvar"); });
	}

	self.remove = function(data) {
		var datos = {};
		datos.contacto = self.selected.id();
		datos.sucursal = self.sucursal.id();
    	$.deleteJSON(BASE_REST_URL+"/contacto/deleteFromSucursal",JSON.stringify(datos)).done(self.removeFromGrid).fail(function(error){ alert(error.responseText);});
	}
	
	self.update = function(data) {
		var serializado = ko.myToJSON(self.selected);
		var url; 
		if(self.typeSelected().id == 1){
			url=BASE_REST_URL+"/calificativo/updateCliente"
		}else{
			url=BASE_REST_URL+"/calificativo/updateProveedor"			
		}
		$.postJSON(url, serializado).done(self.updateGrid).fail(function(){ alert("Ocurrio un error al salvar"); });			
	}
	
	
	self.updateGrid = function(data) {
		self.utils.updateGrid(data, self.calificativos);
		alert("Actualizado correctamente");
		self.formVisible(false);
	}

	self.pushInGrid = function(data) {
		self.utils.pushInGrid(data, self.calificativos);
		alert("Guardado correctamente");
		self.formVisible(false);
	}	
	self.removeFromGrid = function(data) {
		self.utils.removeFromGrid(self.selected, self.calificativos);
		alert("Eliminado correctamente");
		self.formVisible(false);
	}	
	
	self.doSelect = function(data) {
		ko.mapping.fromJS(data, self.selected);
	}

	self.create = function() {
		var nuevo = new Calificativo();
		nuevo.type(self.typeSelected().name);
		ko.mapping.fromJS( nuevo, self.selected);
		self.formVisible(true);
		self.createNew(true);
	}
	self.cancelForm = function() {
		self.formVisible(false);
	}

	ko.bindingHandlers.fadeVisible =self.utils.fadeVisible;  
	
	self.lala = function(data){
		location.hash = self.typeSelected().name + "="+ self.selected.id();
		
	};
    Sammy(function() {
    	this.get('#:type=:calId', function() {
    		alert(this.params.calId);
    	});
        this.get('#:type', function() {
        	self.init(this.params.type)
        });

        this.get('', function() { this.app.runRoute('get', '#CLIENTE') });
    }).run();    
	

}