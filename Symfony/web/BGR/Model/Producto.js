function Producto( ) {
	this.id = ko.observable();
	this.name = ko.observable();
	this.precio_venta = ko.observable ();
	this.precio_compra = ko.observable ();
	this.categoria = ko.observable();
	this.unidad_de_medidas = ko.observableArray();

}