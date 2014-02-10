function Producto( ) {
	this.id = ko.observable();
	this.name = ko.observable();
	this.categoria = ko.observable();
	this.unidad_de_medidas = ko.observableArray();
	this.proveedores = ko.observableArray();

}