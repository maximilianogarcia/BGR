function Proveedor(){
	var self = this;
	
	self.id = ko.observable();

	self.name= ko.observable();

	self.type= ko.observable("PROVEEDOR");

	self.razonSocial= ko.observable();
	
	self.cuit= ko.observable();

	self.condicionImpositiva= ko.observable();

	self.ingresosBrutos= ko.observable();

	self.observaciones= ko.observable();


	
}