function Stock(){
	var self = this; 
    this.id = ko.observable();
	this.stock = ko.observable();
	this.presentacion = ko.observable ();
	this.producto = ko.observable ();
	this.lote = ko.observable();
	this.elaboracion= ko.observable();
	this.vencimiento= ko.observable();
	this.medida= ko.observable();
	this.peso= ko.observable();
	this.kgTotal= ko.observable();
}