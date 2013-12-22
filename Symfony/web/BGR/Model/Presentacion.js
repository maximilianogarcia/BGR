function Presentacion( ) {
	this.id = ko.observable();
	this.descripcion = ko.observable();
	this.sKU = ko.observable();
	this.active = ko.observable(true);
    this.fraccionable = ko.observable(false);
	this.date_create = ko.observable ();
	this.date_update = ko.observable ();
	this.producto = ko.observable(new Producto());
	this.unidad_de_medida = ko.observable();
    this.lote = ko.observable();
	this.material = ko.observable();
	this.peso_neto = ko.observable();
	this.peso_escurrido = ko.observable();
}