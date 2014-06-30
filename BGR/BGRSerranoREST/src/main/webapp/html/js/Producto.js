function Producto( ) {
	this.id = ko.observable();
	this.name = ko.observable();
	this.categoria = ko.observable();
	this.unidadesDeMedida = ko.observableArray();
	this.proveedores = ko.observableArray();
	this.actualizador_precio = ko.observableArray();

}