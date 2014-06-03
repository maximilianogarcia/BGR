function Sucursal(){
	var self = this;
	
	self.id = ko.observable();

	self.name= ko.observable();

	self.descripcion= ko.observable();

	self.observacion= ko.observable();

	self.calle= ko.observable();

	self.numero= ko.observable();

	self.piso= ko.observable();

	self.pais= ko.observable();

	self.provincia= ko.observable();

	self.localidad= ko.observable();
	
	self.zona= ko.observable();

	self.codigoPostal= ko.observable();
	
	self.observacionDireccion= ko.observable();
	
}