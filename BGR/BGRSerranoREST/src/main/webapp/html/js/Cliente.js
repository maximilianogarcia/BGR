function Cliente(){
	var self = this;
	
	self.id = ko.observable();

	self.name= ko.observable();

	self.type= ko.observable("CLIENTE");

	self.razonSocial= ko.observable();
	
	self.cuit= ko.observable();

	self.condicionImpositiva= ko.observable();

	self.ingresosBrutos= ko.observable();

	self.observaciones= ko.observable();

	self.calificativo= ko.observable();

	self.active= ko.observable(true);

	self.etiquetas= ko.observableArray();
	
}